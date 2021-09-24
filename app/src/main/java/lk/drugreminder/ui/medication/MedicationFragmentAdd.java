package lk.drugreminder.ui.medication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.adapter.MedicationAdapter;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Medication;

public class MedicationFragmentAdd extends Fragment {

    private LinearLayout lyPills, lyCards, lyBoxes;
    private LinearLayout lyAddPills, lyFirstMedi1, lyFirstMedi2;
    private Button btnAddByPills, btnAddByCards, btnAddByBoxes;
    private EditText txtPills, txtPillsIncard, txtCards, txtPillsIncardBox, txtCardsInbox, txtBoxes, txtTotalPills;
    private EditText txtDose, txtIntervalH, txtIntervalM;
    private TimePicker txtFirstMedication;
    private Button btnSavePills, btnUpdatePills;
    private TextView lblHeaderMedication, lblTotalPills, lblLastaddedPills, lblMediDose, lblInterval;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_medication_add, container, false);

        lyAddPills = view.findViewById(R.id.ly_add_pills);

        lyPills = view.findViewById(R.id.ly_pills);
        lyCards = view.findViewById(R.id.ly_cards);
        lyBoxes = view.findViewById(R.id.ly_boxes);

        lyFirstMedi1 = view.findViewById(R.id.ly_first_medi1);
        lyFirstMedi2 = view.findViewById(R.id.ly_first_medi2);

        txtPills = view.findViewById(R.id.txt_pills);
        txtPillsIncard = view.findViewById(R.id.txt_pills_incard);
        txtCards = view.findViewById(R.id.txt_cards);
        txtPillsIncardBox = view.findViewById(R.id.txt_pills_incard_box);
        txtCardsInbox = view.findViewById(R.id.txt_cards_inbox);
        txtBoxes = view.findViewById(R.id.txt_boxes);
        txtTotalPills = view.findViewById(R.id.txt_total_pills);

        lblHeaderMedication = view.findViewById(R.id.lbl_header_medication);
        lblTotalPills = view.findViewById(R.id.lbl_total_pills);
        lblLastaddedPills = view.findViewById(R.id.lbl_lastadded_pills);
        lblMediDose = view.findViewById(R.id.lbl_medi_dose);
        lblInterval = view.findViewById(R.id.lbl_interval);

        lblHeaderMedication.setText(MedicationAdapter.getStaticMedication().getMedicationName());

        txtDose = view.findViewById(R.id.txt_dose);
        txtIntervalH = view.findViewById(R.id.txt_intervalH);
        txtIntervalM = view.findViewById(R.id.txt_intervalM);
        txtFirstMedication = view.findViewById(R.id.txt_first_medication);

        btnSavePills = view.findViewById(R.id.btn_save_pills);
        btnUpdatePills = view.findViewById(R.id.btn_update_pills);

        btnAddByPills = view.findViewById(R.id.btn_add_by_pills);
        btnAddByCards = view.findViewById(R.id.btn_add_by_cards);
        btnAddByBoxes = view.findViewById(R.id.btn_add_by_box);

        setLayoutsFalse();
        lyPills.setVisibility(View.VISIBLE);
        btnAddByPills.setBackgroundTintList(getResources().getColorStateList(R.color.medi_medication_add_by));

        loadMedication();

        btnAddByPills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLayoutsFalse();
                btnAddByPills.setBackgroundTintList(getResources().getColorStateList(R.color.medi_medication_add_by));
                lyPills.setVisibility(View.VISIBLE);
            }
        });

        btnAddByCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLayoutsFalse();
                btnAddByCards.setBackgroundTintList(getResources().getColorStateList(R.color.medi_medication_add_by));
                lyCards.setVisibility(View.VISIBLE);
            }
        });

        btnAddByBoxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLayoutsFalse();
                btnAddByBoxes.setBackgroundTintList(getResources().getColorStateList(R.color.medi_medication_add_by));
                lyBoxes.setVisibility(View.VISIBLE);
            }
        });

        btnSavePills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPills();
            }
        });

        return view;
    }

    private void addPills() {
        DatabaseReference updateMedication = FirebaseDB.getDBMedication();
        updateMedication.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Medication medication = MedicationAdapter.getStaticMedication();
                if (snapshot.hasChild(medication.getMedicationId())) {
                    medication.setTotalPills(Integer.parseInt(txtTotalPills.getText().toString()));
                    medication.setLastAddedPills(Integer.parseInt(txtPills.getText().toString()));
                    medication.setDose(Integer.parseInt(txtDose.getText().toString()));
                    medication.setIntervalH(Integer.parseInt(txtIntervalH.getText().toString()));
                    medication.setIntervalM(Integer.parseInt(txtIntervalM.getText().toString()));
                    medication.setFirstMedicationH(txtFirstMedication.getHour());
                    medication.setFirstMedicationM(txtFirstMedication.getMinute());
                    updateMedication.child(medication.getMedicationId()).setValue(medication);
                    setTextEmpty();
                    Toast.makeText(getContext(), "Pills added successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadMedication() {
        DatabaseReference dbMedication = FirebaseDB.getDBMedication();
        Query medications = dbMedication.orderByChild("medicationId").equalTo(MedicationAdapter.getStaticMedication().getMedicationId());
        medications.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(MedicationAdapter.getStaticMedication().getMedicationId())) {
                    Medication medication = snapshot.child(MedicationAdapter.getStaticMedication().getMedicationId()).getValue(Medication.class);
                    lblTotalPills.setText(medication.getTotalPills() + " pills");
                    lblLastaddedPills.setText(medication.getLastAddedPills() + " pills");
                    lblMediDose.setText(medication.getDose() + " pills");
                    lblInterval.setText(medication.getIntervalH() + " hours  " + medication.getIntervalM() + " minutes");
                    if (medication.getFirstMedicationH() > -1) {
                        lyFirstMedi1.setVisibility(View.GONE);
                        lyFirstMedi2.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setTextEmpty() {
        EditText[] editTexts = {txtPillsIncardBox, txtPills, txtPillsIncard, txtBoxes, txtCards, txtCardsInbox, txtDose, txtIntervalH, txtIntervalM, txtTotalPills};
        for (EditText editText : editTexts) {
            editText.setText("");
        }
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
