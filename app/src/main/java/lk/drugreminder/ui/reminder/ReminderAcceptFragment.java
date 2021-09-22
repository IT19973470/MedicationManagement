package lk.drugreminder.ui.reminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.adapter.ReminderAdapter;
import lk.drugreminder.adapter.ReminderHistoryAdapter;
import lk.drugreminder.model.Reminder;

public class ReminderAcceptFragment extends Fragment {

    private TextView lblHeaderMedicine, txtMedicine, txtDose, txtDue, txtNext, txtRemaining, txtEnd;
    private Button btnTakeMedicine, btnSkipMedicine;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ReminderHistoryAdapter reminderHistoryAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reminder_accept, container, false);
        lblHeaderMedicine = view.findViewById(R.id.lbl_header_medicine);
        txtMedicine = view.findViewById(R.id.txt_medicine);
        txtDose = view.findViewById(R.id.txt_dose);
        txtDue = view.findViewById(R.id.txt_due);
        txtNext = view.findViewById(R.id.txt_next);
        txtRemaining = view.findViewById(R.id.txt_remian);
        txtEnd = view.findViewById(R.id.txt_end);
        btnTakeMedicine = view.findViewById(R.id.btn_took_medicine);
        btnSkipMedicine = view.findViewById(R.id.btn_skip_medicine);

        btnTakeMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_fragment_medicine_take);
            }
        });

        btnSkipMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_fragment_add_reason);
            }
        });

        Reminder reminder = ReminderAdapter.getReminderStatic();
        lblHeaderMedicine.setText(reminder.getMedicineHeader());
        txtMedicine.setText(reminder.getMedicineHeader());
        txtDose.setText(reminder.getDose());
        txtDue.setText(reminder.getDueTime());
        txtNext.setText(reminder.getNextTime());
        txtRemaining.setText(reminder.getRemaining());
        txtEnd.setText(reminder.getEndAt());

        List<Reminder> reminders = new ArrayList<>();
        reminders.add(new Reminder("Took Pill", "Donpiri", "2 pills", "01:30 PM", "02:30 PM", "Next day at 05:30 PM", "20 pills", "2020-03-02 AT 06:30 PM", false));
        reminders.add(new Reminder("Missed Pill", "Lumex", "3 pills", "02:30 PM", "03:30 PM", "06:30 PM", "30 pills", "2020-03-02 AT 06:30 PM", true, "Forgot"));
        reminders.add(new Reminder("Missed Pill", "Lumex", "3 pills", "02:30 PM", "03:30 PM", "06:30 PM", "30 pills", "2020-03-02 AT 06:30 PM", true, "Forgot"));
        reminders.add(new Reminder("Missed Pill", "Amexo", "2 pills", "04:30 PM", "05:30 PM", "Next day at 11:30 PM", "50 pills", "2020-04-08 AT 07:30 PM", true, "Forgot"));
        reminders.add(new Reminder("Took Pill", "Amexo", "3 pills", "04:30 PM", "05:30 PM", "Next day at 11:30 PM", "30 pills", "2020-04-08 AT 07:30 PM", false));
        reminders.add(new Reminder("Missed Pill", "Amexo", "4 pills", "04:30 PM", "05:30 PM", "Next day at 11:30 PM", "20 pills", "2020-04-08 AT 07:30 PM", true, "Forgot"));

        recyclerView = view.findViewById(R.id.recycler_reminder_history);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        reminderHistoryAdapter = new ReminderHistoryAdapter(this);
        reminderHistoryAdapter.setContext(getActivity());
        reminderHistoryAdapter.setReminders(reminders);
        reminderHistoryAdapter.setContext(getContext());
        recyclerView.setAdapter(reminderHistoryAdapter);

        return view;
    }

}
