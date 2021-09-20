package lk.drugreminder.ui.sickness;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import lk.drugreminder.R;
import lk.drugreminder.entity.Sickness;

public class SicknessFragment extends Fragment {

    private View view;
    private Button btnDisease;
    private DatabaseReference dbDisease;
    private EditText txtDisease;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sickness, container, false);

        dbDisease = FirebaseDatabase.getInstance().getReference("sickness");

        txtDisease = view.findViewById(R.id.txt_disease);
        btnDisease = view.findViewById(R.id.btn_add_disease);

        btnDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtDisease.getText().toString().equals("")) {
                    addDisease();
                } else {
                    Toast.makeText(getContext(), "Please enter a sickness", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private void addDisease() {
        String id = dbDisease.push().getKey();
        Sickness sickness = new Sickness(id, txtDisease.getText().toString());
//        dbDisease.push().setValue(sickness);
        dbDisease.child(id).setValue(sickness);
        txtDisease.setText("");
        Toast.makeText(getContext(), "Sickness added successfully", Toast.LENGTH_LONG).show();
    }
}
