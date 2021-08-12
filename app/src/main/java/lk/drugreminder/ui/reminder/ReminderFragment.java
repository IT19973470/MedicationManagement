package lk.drugreminder.ui.reminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.adapter.ReminderAdapter;
import lk.drugreminder.model.ReminderDTO;

public class ReminderFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ReminderAdapter reminderAdapter;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reminder_main, container, false);

        List<ReminderDTO>  reminderDTOS = new ArrayList<>();
        reminderDTOS.add(new ReminderDTO("Diabetes Pill", "Donpiri", "2 pills", "01:30 PM", "Next day at 05:30 PM", "20 pills", "2020-03-02 AT 06:30 PM"));
        reminderDTOS.add(new ReminderDTO("Diabetes Pill", "Lumex", "3 pills", "02:30 PM", "06:30 PM", "30 pills", "2020-03-02 AT 06:30 PM"));
        reminderDTOS.add(new ReminderDTO("Cough Syrup", "Amexo", "10ml", "04:30 PM", "Next day at 11:30 PM", "200ml", "2020-04-08 AT 07:30 PM"));
        recyclerView = view.findViewById(R.id.recycler_reminder);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        reminderAdapter = new ReminderAdapter(this);
        reminderAdapter.setReminderDTOS(reminderDTOS);
        reminderAdapter.setContext(getContext());
        recyclerView.setAdapter(reminderAdapter);
        return view;
    }
}