package lk.drugreminder.ui.medication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.adapter.MedicationAdapter;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Medication;
import lk.drugreminder.model.Sickness;

public class MedicationFragment extends Fragment {

    private Spinner spinner;
    private DatabaseReference dbSickness, dbMedication;
    private View view;
    private List<Sickness> sicknesses;
    private EditText txtMedication;
    private Button btnMedication, btnCancel;
    private int sicknessPosition;
    private RecyclerView recyclerView;
    private MedicationAdapter medicationAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_medication_main, container, false);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.);

        spinner = view.findViewById(R.id.spinner_sicknesses);
        txtMedication = view.findViewById(R.id.txt_medication);
        btnMedication = view.findViewById(R.id.btn_add_medication);
        btnCancel = view.findViewById(R.id.btn_cancel);

        dbSickness = FirebaseDB.getDBSickness();
        dbMedication = FirebaseDB.getDBMedication();

        btnMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtMedication.getText().toString().equals("")) {
                    if (btnMedication.getText().equals("Add Medication")) {
                        addMedication();
                    } else if (btnMedication.getText().equals("Update Medication")) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                        alert.setTitle("Update");
                        alert.setMessage("Do you want to update " + MedicationAdapter.getStaticMedication().getMedicationName() + "?");
                        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                updateMedication();
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
                    Toast.makeText(getContext(), "Please enter a medication", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMedication.setText("");
                btnMedication.setText("Add Medication");
            }
        });

        loadSicknesses();

//        LinearLayout app_layer = (LinearLayout) view.findViewById(R.id.lbl_medication);
//        app_layer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.nav_fragment_medication_add);
//            }
//        });

        return view;
    }

    private void addMedication() {
        String id = dbMedication.push().getKey();
        Medication medication = new Medication(id, txtMedication.getText().toString(), sicknesses.get(sicknessPosition).getSicknessId());
//        dbDisease.push().setValue(sickness);
        dbMedication.child(id).setValue(medication);
        txtMedication.setText("");
        Toast.makeText(getContext(), "Medication added successfully", Toast.LENGTH_LONG).show();
    }

    private void updateMedication() {
        DatabaseReference updateMedication = FirebaseDB.getDBMedication();
        updateMedication.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Medication medication = MedicationAdapter.getStaticMedication();
                if (snapshot.hasChild(medication.getMedicationId())) {
                    medication.setMedicationName(txtMedication.getText().toString());
                    updateMedication.child(medication.getMedicationId()).setValue(medication);
                    txtMedication.setText("");
                    btnMedication.setText("Add Medication");
                    Toast.makeText(getContext(), "Medication updated successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadSicknesses() {
        sicknesses = new ArrayList<>();
        dbSickness.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sicknesses.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    sicknesses.add(
                            dataSnapshot.getValue(Sickness.class)
                    );
                }
                ArrayAdapter<Sickness> adapter = new ArrayAdapter<Sickness>(view.getContext(),
                        android.R.layout.simple_spinner_item, sicknesses);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        sicknessPosition = position;
                        loadMedications();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadMedications() {
        List<Medication> medicationList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_medication);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        medicationAdapter = new MedicationAdapter(this);

        Query medications = dbMedication.orderByChild("sicknessId").equalTo(sicknesses.get(sicknessPosition).getSicknessId());
        medications.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                medicationList.clear();
                for (DataSnapshot medication : snapshot.getChildren()) {
                    medicationList.add(medication.getValue(Medication.class));
                }
                medicationAdapter.setMedicationList(medicationList);
                medicationAdapter.setContext(getContext());
                medicationAdapter.setTxtMedication(txtMedication);
                medicationAdapter.setBtnMedication(btnMedication);
                recyclerView.setAdapter(medicationAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
