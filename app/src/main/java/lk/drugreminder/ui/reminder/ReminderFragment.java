package lk.drugreminder.ui.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lk.drugreminder.NotificationReceiver;
import lk.drugreminder.R;
import lk.drugreminder.adapter.ReminderAdapter;
import lk.drugreminder.calculations.Calculations;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Medication;
import lk.drugreminder.model.MedicationDTO;

public class ReminderFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ReminderAdapter reminderAdapter;
    private DatabaseReference dbMedication;
    private View view;
    static MedicationDTO nextMedication;

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
                Collections.sort(medications);
                reminderAdapter.setReminders(medications);
                recyclerView.setAdapter(reminderAdapter);
                if (ReminderAcceptFragment.tookPill) {
                    ReminderAcceptFragment.tookPill = false;
                    scheduleNotification(medications.get(0).getMedication(), "It's time to take your medication");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void scheduleNotification(Medication nextMedication, String text) {
        Intent intent = new Intent(getContext(), NotificationReceiver.class);
        intent.putExtra("title", nextMedication.getMedicationName() + " Pill");
        intent.putExtra("text", text);
        PendingIntent pending = PendingIntent.getBroadcast(getContext(), 42, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Schdedule notification
        AlarmManager manager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        manager.set(
                AlarmManager.RTC_WAKEUP,
                LocalDateTime.of(LocalDate.now().plusDays(nextMedication.getNextDueDay()), LocalTime.of(nextMedication.getNextDueTimeH(), nextMedication.getNextDueTimeM())).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                pending);
    }
}