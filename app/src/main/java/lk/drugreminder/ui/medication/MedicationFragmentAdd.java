package lk.drugreminder.ui.medication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import lk.drugreminder.R;

public class MedicationFragmentAdd extends Fragment {

    private LinearLayout lyPills, lyCards, lyBoxes;
    private LinearLayout lyAddPills;
    private Button btnAddByPills, btnAddByCards, btnAddByBoxes;
    private Button btnSavePills, btnUpdatePills;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_medication_add, container, false);

        lyAddPills = view.findViewById(R.id.ly_add_pills);

        lyPills = view.findViewById(R.id.ly_pills);
        lyCards = view.findViewById(R.id.ly_cards);
        lyBoxes = view.findViewById(R.id.ly_boxes);

        btnSavePills = view.findViewById(R.id.btn_save_pills);
        btnUpdatePills = view.findViewById(R.id.btn_update_pills);

        btnAddByPills = view.findViewById(R.id.btn_add_by_pills);
        btnAddByCards = view.findViewById(R.id.btn_add_by_cards);
        btnAddByBoxes = view.findViewById(R.id.btn_add_by_box);

        setLayoutsFalse();
        lyPills.setVisibility(View.VISIBLE);
        btnAddByPills.setBackgroundTintList(getResources().getColorStateList(R.color.medi_medication_add_by));

        btnAddByPills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLayoutsFalse();
                btnAddByPills.setBackgroundTintList(getResources().getColorStateList(R.color.medi_medication_add_by));
                btnSavePills.setText("Add pills");
                btnUpdatePills.setText("Update pills");
                lyPills.setVisibility(View.VISIBLE);
            }
        });

        btnAddByCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLayoutsFalse();
                btnAddByCards.setBackgroundTintList(getResources().getColorStateList(R.color.medi_medication_add_by));
                btnSavePills.setText("Add cards");
                btnUpdatePills.setText("Update cards");
                lyCards.setVisibility(View.VISIBLE);
            }
        });

        btnAddByBoxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLayoutsFalse();
                btnAddByBoxes.setBackgroundTintList(getResources().getColorStateList(R.color.medi_medication_add_by));
                btnSavePills.setText("Add boxes");
                btnUpdatePills.setText("Update boxes");
                lyBoxes.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    private void setLayoutsFalse() {
        btnAddByPills.setBackgroundTintList(getResources().getColorStateList(R.color.medi_medication_add_by_not_clicked));
        btnAddByCards.setBackgroundTintList(getResources().getColorStateList(R.color.medi_medication_add_by_not_clicked));
        btnAddByBoxes.setBackgroundTintList(getResources().getColorStateList(R.color.medi_medication_add_by_not_clicked));
        lyPills.setVisibility(View.GONE);
        lyCards.setVisibility(View.GONE);
        lyBoxes.setVisibility(View.GONE);
    }
}
