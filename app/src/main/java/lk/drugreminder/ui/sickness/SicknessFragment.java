package lk.drugreminder.ui.sickness;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.adapter.SicknessAdapter;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Sickness;

public class SicknessFragment extends Fragment {

    private View view;
    private Button btnSickness, btnCancel;
    private DatabaseReference dbSickness;
    private EditText txtSickness;
    private RecyclerView recyclerView;
    private SicknessAdapter sicknessAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sickness_main, container, false);

        dbSickness = FirebaseDB.getDBSickness();

        txtSickness = view.findViewById(R.id.txt_sickness);
        btnSickness = view.findViewById(R.id.btn_add_disease);
        btnCancel = view.findViewById(R.id.btn_cancel);

        btnSickness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtSickness.getText().toString().equals("")) {
                    if (btnSickness.getText().equals("Add Sickness")) {
                        addSickness();
                    } else if (btnSickness.getText().equals("Update Sickness")) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                        alert.setTitle("Update");
                        alert.setMessage("Do you want to update " + SicknessAdapter.getStaticSickness().getSicknessName() + "?");
                        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                updateSickness();
                            }
                        });
                        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alert.show();
                    }
                } else {
                    Toast.makeText(getContext(), "Please enter a sickness", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtSickness.setText("");
                btnSickness.setText("Add Sickness");
            }
        });

        loadSicknesses();

        return view;
    }

    //add
    private void addSickness() {
        String id = dbSickness.push().getKey();
        Sickness sickness = new Sickness(id, txtSickness.getText().toString());
        dbSickness.child(id).setValue(sickness);
        txtSickness.setText("");
        Toast.makeText(getContext(), "Sickness added successfully", Toast.LENGTH_LONG).show();
    }

    //update
    private void updateSickness() {
        DatabaseReference updateSick = FirebaseDB.getDBSickness();
        updateSick.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Sickness sickness = SicknessAdapter.getStaticSickness();
                if (snapshot.hasChild(sickness.getSicknessId())) {
                    sickness.setSicknessName(txtSickness.getText().toString());
                    updateSick.child(sickness.getSicknessId()).setValue(sickness);
                    txtSickness.setText("");
                    btnSickness.setText("Add Sickness");
                    Toast.makeText(getContext(), "Sickness updated successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //view all
    private void loadSicknesses() {
        List<Sickness> sicknessList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_sickness);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        sicknessAdapter = new SicknessAdapter(this);
        sicknessAdapter.setContext(getContext());
        sicknessAdapter.setTxtSickness(txtSickness);
        sicknessAdapter.setBtnSickness(btnSickness);

        dbSickness.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sicknessList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    sicknessList.add(
                            dataSnapshot.getValue(Sickness.class)
                    );
                }
                sicknessAdapter.setSicknessList(sicknessList);
                recyclerView.setAdapter(sicknessAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
