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
import lk.drugreminder.model.MedicationDTO;
import lk.drugreminder.model.Reminder;

public class ReminderAcceptFragment extends Fragment {

    private TextView lblHeaderMedication, txtMedication, txtDose, txtDue, txtNext, txtRemaining, txtEnd;
    private Button btnTakeMedication, btnSkipMedication;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ReminderHistoryAdapter reminderHistoryAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reminder_accept, container, false);
        lblHeaderMedication = view.findViewById(R.id.lbl_header_medication);
        txtMedication = view.findViewById(R.id.txt_medication);
        txtDose = view.findViewById(R.id.txt_dose);
        txtDue = view.findViewById(R.id.txt_due);
        txtNext = view.findViewById(R.id.txt_next);
        txtRemaining = view.findViewById(R.id.txt_remian);
        txtEnd = view.findViewById(R.id.txt_end);
        btnTakeMedication = view.findViewById(R.id.btn_took_medication);
        btnSkipMedication = view.findViewById(R.id.btn_skip_medication);

        btnTakeMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_fragment_medication_take);
            }
        });

        btnSkipMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_fragment_add_reason);
            }
        });

        MedicationDTO reminder = ReminderAdapter.getReminderStatic();
        lblHeaderMedication.setText(reminder.getMedicationHeader());
        txtMedication.setText(reminder.getMedicationHeader());
        txtDose.setText(reminder.getDose());
        txtDue.setText(reminder.getNextDueTime());
        txtNext.setText(reminder.getSecondNextDueTime());
        txtRemaining.setText(reminder.getRemaining() + " Pills");
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
