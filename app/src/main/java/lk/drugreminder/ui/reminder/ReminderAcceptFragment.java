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
                Navigation.findNavController(view).navigate(R.id.nav_fragment_skip_reason);
            }
        });



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
