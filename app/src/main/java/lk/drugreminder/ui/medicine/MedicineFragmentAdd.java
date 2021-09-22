package lk.drugreminder.ui.medicine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import lk.drugreminder.R;

public class MedicineFragmentAdd extends Fragment {

    private LinearLayout lyPills, lyCards, lyBoxes;
    private LinearLayout lyAddPills;
    private Button btnAddByPills, btnAddByCards, btnAddByBoxes;
    private Button btnSavePills, btnUpdatePills;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_medicine_add, container, false);

        lyAddPills = view.findViewById(R.id.ly_add_pills);

        lyPills = view.findViewById(R.id.ly_pills);
        lyCards = view.findViewById(R.id.ly_cards);
        lyBoxes = view.findViewById(R.id.ly_boxes);

        btnSavePills = view.findViewById(R.id.btn_save_pills);
        btnUpdatePills = view.findViewById(R.id.btn_update_pills);

        btnAddByPills = view.findViewById(R.id.btn_add_by_pills);
        btnAddByCards = view.findViewById(R.id.btn_add_by_cards);
        btnAddByBoxes = view.findViewById(R.id.btn_add_by_box);

        return view;
    }

}
