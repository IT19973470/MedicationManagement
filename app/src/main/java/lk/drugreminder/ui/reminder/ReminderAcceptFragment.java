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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.adapter.ReminderAdapter;
import lk.drugreminder.adapter.ReminderHistoryAdapter;
import lk.drugreminder.model.ReminderDTO;

public class ReminderAcceptFragment extends Fragment {

    private TextView lblHeaderMedicine, txtMedicine, txtDose, txtDue, txtNext, txtRemaining, txtEnd;
    private Button btnTakeMedicine;
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

        ReminderDTO reminderDTO = ReminderAdapter.getReminderStaticDTO();
        lblHeaderMedicine.setText(reminderDTO.getMedicineHeader());
        txtMedicine.setText(reminderDTO.getMedicineHeader());
        txtDose.setText(reminderDTO.getDose());
        txtDue.setText(reminderDTO.getDueTime());
        txtNext.setText(reminderDTO.getNextTime());
        txtRemaining.setText(reminderDTO.getRemaining());
        txtEnd.setText(reminderDTO.getEndAt());

        List<ReminderDTO> reminderDTOS = new ArrayList<>();
        reminderDTOS.add(new ReminderDTO("Took Pill", "Donpiri", "2 pills", "01:30 PM", "02:30 PM", "Next day at 05:30 PM", "20 pills", "2020-03-02 AT 06:30 PM", false));
        reminderDTOS.add(new ReminderDTO("Missed Pill", "Lumex", "3 pills", "02:30 PM", "03:30 PM", "06:30 PM", "30 pills", "2020-03-02 AT 06:30 PM", true));
        reminderDTOS.add(new ReminderDTO("Missed Pill", "Lumex", "3 pills", "02:30 PM", "03:30 PM", "06:30 PM", "30 pills", "2020-03-02 AT 06:30 PM", true));
        reminderDTOS.add(new ReminderDTO("Missed Syrup", "Amexo", "10ml", "04:30 PM", "05:30 PM", "Next day at 11:30 PM", "200ml", "2020-04-08 AT 07:30 PM", true));
        reminderDTOS.add(new ReminderDTO("Took Syrup", "Amexo", "10ml", "04:30 PM", "05:30 PM", "Next day at 11:30 PM", "200ml", "2020-04-08 AT 07:30 PM", false));
        reminderDTOS.add(new ReminderDTO("Missed Syrup", "Amexo", "10ml", "04:30 PM", "05:30 PM", "Next day at 11:30 PM", "200ml", "2020-04-08 AT 07:30 PM", true));

        recyclerView = view.findViewById(R.id.recycler_reminder_history);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        reminderHistoryAdapter = new ReminderHistoryAdapter(this);
        reminderHistoryAdapter.setContext(getActivity());
        reminderHistoryAdapter.setReminderDTOS(reminderDTOS);
        reminderHistoryAdapter.setContext(getContext());
        recyclerView.setAdapter(reminderHistoryAdapter);

        return view;
    }

    //    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.fragment_reminder_accept);
//
//        lblHeaderMedicine = findViewById(R.id.lbl_header_medicine);
//        txtMedicine = findViewById(R.id.txt_medicine);
//        txtDose = findViewById(R.id.txt_dose);
//        txtDue = findViewById(R.id.txt_due);
//        txtNext = findViewById(R.id.txt_next);
//        txtRemaining = findViewById(R.id.txt_remian);
//        txtEnd = findViewById(R.id.txt_end);
//        btnTakeMedicine = findViewById(R.id.btn_took_medicine);
//    }
}
