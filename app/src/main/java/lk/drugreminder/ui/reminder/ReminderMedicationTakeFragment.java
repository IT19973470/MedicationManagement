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
import lk.drugreminder.model.MedicationDTO;
import lk.drugreminder.model.Reminder;

public class ReminderMedicationTakeFragment extends Fragment {

    private TextView lblHeaderMedication, txtMedication, txtDose, txtDue, txtNext, txtRemaining, txtEnd;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reminder_medication_take, container, false);

        lblHeaderMedication = view.findViewById(R.id.lbl_header_medication);
        txtMedication = view.findViewById(R.id.txt_medication);
        txtDose = view.findViewById(R.id.txt_dose);
        txtNext = view.findViewById(R.id.txt_next);
        txtRemaining = view.findViewById(R.id.txt_remian);
        txtEnd = view.findViewById(R.id.txt_end);

        MedicationDTO reminder = ReminderAdapter.getReminderStatic();
        lblHeaderMedication.setText(reminder.getMedicationHeader());
        txtMedication.setText(reminder.getMedicationHeader());
        txtDose.setText(reminder.getDose());
        txtNext.setText(reminder.getNextDueTime());
        txtRemaining.setText(reminder.getRemaining());
        txtEnd.setText(reminder.getEndAt());

        return view;
    }
}
