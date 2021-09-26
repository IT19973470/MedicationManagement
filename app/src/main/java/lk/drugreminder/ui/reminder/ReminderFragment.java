package lk.drugreminder.ui.reminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.adapter.ReminderAdapter;
import lk.drugreminder.calculations.Calculations;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Medication;
import lk.drugreminder.model.MedicationDTO;
import lk.drugreminder.model.Reminder;
import lk.drugreminder.model.Sickness;

public class ReminderFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ReminderAdapter reminderAdapter;
    private DatabaseReference dbMedication;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reminder_main, container, false);

        dbMedication = FirebaseDB.getDBMedication();

        loadReminders();

        return view;
    }

    //view all
    private void loadReminders() {
        List<MedicationDTO> medications = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_reminder);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        reminderAdapter = new ReminderAdapter();
        reminderAdapter.setContext(getContext());

        dbMedication.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                medications.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Medication medication = dataSnapshot.getValue(Medication.class);
                    if (medication.getTotalPills() > 0) {
                        medications.add(
                                new MedicationDTO(
                                        medication.getMedicationName() + " Pill",
                                        medication.getSicknessName(),
                                        medication.getDose() + " Pills",
                                        LocalTime.of(medication.getNextDueTimeH(), medication.getNextDueTimeM()).format(DateTimeFormatter.ofPattern("hh:mm a")),
                                        medication.getTotalPills() + "",
                                        Calculations.pillsEndOn(medication.getTotalPills(), medication.getDose(), medication.getLastMedicationH(), medication.getLastMedicationM(), medication.getIntervalH(), medication.getIntervalM()),
                                        medication
                                )
                        );
                    }
                }
                reminderAdapter.setReminders(medications);
                recyclerView.setAdapter(reminderAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}