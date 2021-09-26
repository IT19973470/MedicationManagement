package lk.drugreminder.ui.reminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import lk.drugreminder.R;
import lk.drugreminder.adapter.ReminderAdapter;
import lk.drugreminder.calculations.Calculations;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Medication;
import lk.drugreminder.model.PillsLog;

public class ReminderAddReasonFragment extends Fragment {

    private View view;
    private Button btnAddReason;
    private DatabaseReference dbReason;
    private EditText txtReason;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reminder_new_reason, container, false);

        dbReason = FirebaseDB.getDBReason();

        txtReason = view.findViewById(R.id.txt_reason);
        btnAddReason = view.findViewById(R.id.btn_add);

        btnAddReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtReason.getText().toString().equals("")) {
                    addReason();
                } else {
                    Toast.makeText(getContext(), "Please enter a reason", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    //add
    private void addReason() {
        DatabaseReference dbPillsLog = FirebaseDB.getDBPillsLog();
        String id = dbPillsLog.push().getKey();
        LocalTime now = LocalTime.now();
        PillsLog pillsLog = new PillsLog(id, ReminderAdapter.getStaticMedication(), false, txtReason.getText().toString(), now.getHour(), now.getMinute(), LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dbPillsLog.child(id).setValue(pillsLog);

        DatabaseReference updateMedication = FirebaseDB.getDBMedication();
        updateMedication.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Medication medication = ReminderAdapter.getStaticMedication();
                if (snapshot.hasChild(medication.getMedicationId())) {
                    medication.setLastMedicationH(medication.getNextDueTimeH());
                    medication.setLastMedicationM(medication.getNextDueTimeM());
                    int[] nextDueTime = Calculations.calcNextDueTime(medication.getLastMedicationH(), medication.getLastMedicationM(), medication.getIntervalH(), medication.getIntervalM());
                    medication.setNextDueTimeH(nextDueTime[0]);
                    medication.setNextDueTimeM(nextDueTime[1]);
                    updateMedication.child(medication.getMedicationId()).setValue(medication);
                    Navigation.findNavController(view).navigate(R.id.nav_fragment_reminder_accept);
                    Toast.makeText(getContext(), "Reason added successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
