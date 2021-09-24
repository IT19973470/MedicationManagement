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

import java.util.ArrayList;
import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.adapter.ReminderAdapter;
import lk.drugreminder.db.FirebaseDB;
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

        List<Reminder> reminders = new ArrayList<>();
        reminders.add(new Reminder("Diabetes Pill", "Donpiri", "2 pills", "01:30 PM", "Next day at 05:30 PM", "20 pills", "2020-03-02 AT 06:30 PM"));
        reminders.add(new Reminder("Diabetes Pill", "Lumex", "3 pills", "02:30 PM", "06:30 PM", "30 pills", "2020-03-02 AT 06:30 PM"));
        reminders.add(new Reminder("Blood Pressure Pill", "Amexo", "2 pills", "04:30 PM", "Next day at 11:30 PM", "50 pills", "2020-04-08 AT 07:30 PM"));
        reminders.add(new Reminder("Blood Pressure Pill", "Pfizer", "3 pills", "04:30 PM", "Next day at 11:30 PM", "50 pills", "2020-04-08 AT 07:30 PM"));
        recyclerView = view.findViewById(R.id.recycler_reminder);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        reminderAdapter = new ReminderAdapter(this);
        reminderAdapter.setReminders(reminders);
        reminderAdapter.setContext(getContext());
        recyclerView.setAdapter(reminderAdapter);
        return view;
    }

    private void loadReminders() {
        List<Reminder> reminderList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_sickness);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        reminderAdapter = new ReminderAdapter(this);

        dbMedication.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reminderList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    reminderList.add(
                            dataSnapshot.getValue(Reminder.class)
                    );
                }
                reminderAdapter.setReminders(reminderList);
                reminderAdapter.setContext(getContext());
                recyclerView.setAdapter(reminderAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}