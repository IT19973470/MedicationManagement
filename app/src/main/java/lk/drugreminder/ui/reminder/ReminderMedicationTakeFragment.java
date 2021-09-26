package lk.drugreminder.ui.reminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import lk.drugreminder.R;
import lk.drugreminder.adapter.ReminderAdapter;
import lk.drugreminder.calculations.Calculations;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Medication;
import lk.drugreminder.model.MedicationDTO;
import lk.drugreminder.model.Reminder;

public class ReminderMedicationTakeFragment extends Fragment {

    private TextView lblHeaderMedication, txtMedication, txtDose, txtDue, txtNext, txtRemaining, txtEnd;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reminder_medication_take, container, false);
        System.out.println(11);
        lblHeaderMedication = view.findViewById(R.id.lbl_header_medication);
        txtMedication = view.findViewById(R.id.txt_medication);
        txtDose = view.findViewById(R.id.txt_dose);
        txtNext = view.findViewById(R.id.txt_next);
        txtRemaining = view.findViewById(R.id.txt_remian);
        txtEnd = view.findViewById(R.id.txt_end);

        loadMedication();

        return view;
    }

    //view
    private void loadMedication() {
        Query medications = FirebaseDB.getDBMedication().orderByChild("medicationId").equalTo(ReminderAdapter.getReminderStatic().getMedication().getMedicationId());
        medications.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(ReminderAdapter.getReminderStatic().getMedication().getMedicationId())) {
                    Medication medication = snapshot.child(ReminderAdapter.getReminderStatic().getMedication().getMedicationId()).getValue(Medication.class);
                    txtNext.setText(LocalTime.of(medication.getNextDueTimeH(), medication.getNextDueTimeM()).format(DateTimeFormatter.ofPattern("hh:mm a")));
                    txtRemaining.setText(medication.getTotalPills() + " Pills");
                    txtEnd.setText(Calculations.pillsEndOn(medication.getTotalPills(), medication.getDose(), medication.getLastMedicationH(), medication.getLastMedicationM(), medication.getIntervalH(), medication.getIntervalM()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        MedicationDTO reminder = ReminderAdapter.getReminderStatic();
        lblHeaderMedication.setText(reminder.getMedicationHeader());
        txtMedication.setText(reminder.getMedicationHeader());
        txtDose.setText(reminder.getDose());
    }

}
