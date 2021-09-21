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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.adapter.SicknessAdapter;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Sickness;

public class SicknessFragment extends Fragment {

    private View view;
    private Button btnDisease;
    private DatabaseReference dbDisease;
    private EditText txtDisease;
    private RecyclerView recyclerView;
    private SicknessAdapter sicknessAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sickness_main, container, false);

        dbDisease = FirebaseDB.getFirebaseDatabase();

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

        loadSicknesses();

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

    private void loadSicknesses() {
        List<Sickness> sicknessList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_sickness);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        sicknessAdapter = new SicknessAdapter(this);

        dbDisease.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sicknessList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    sicknessList.add(
                            dataSnapshot.getValue(Sickness.class)
                    );
                }
                sicknessAdapter.setSicknessList(sicknessList);
                sicknessAdapter.setContext(getContext());
                recyclerView.setAdapter(sicknessAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
