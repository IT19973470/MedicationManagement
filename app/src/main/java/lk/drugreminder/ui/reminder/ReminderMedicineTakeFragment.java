package lk.drugreminder.ui.reminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import lk.drugreminder.R;
import lk.drugreminder.adapter.ReminderAdapter;
import lk.drugreminder.model.ReminderDTO;

public class ReminderMedicineTakeFragment extends Fragment {

    private TextView lblHeaderMedicine, txtMedicine, txtDose, txtDue, txtNext, txtRemaining, txtEnd;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reminder_medicine_take, container, false);

        lblHeaderMedicine = view.findViewById(R.id.lbl_header_medicine);
        txtMedicine = view.findViewById(R.id.txt_medicine);
        txtDose = view.findViewById(R.id.txt_dose);
        txtNext = view.findViewById(R.id.txt_next);
        txtRemaining = view.findViewById(R.id.txt_remian);
        txtEnd = view.findViewById(R.id.txt_end);

        ReminderDTO reminderDTO = ReminderAdapter.getReminderStaticDTO();
        lblHeaderMedicine.setText(reminderDTO.getMedicineHeader());
        txtMedicine.setText(reminderDTO.getMedicineHeader());
        txtDose.setText(reminderDTO.getDose());
        txtNext.setText(reminderDTO.getNextTime());
        txtRemaining.setText(reminderDTO.getRemaining());
        txtEnd.setText(reminderDTO.getEndAt());

        return view;
    }
}
