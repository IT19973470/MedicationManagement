package lk.drugreminder.ui.medication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.navigation.Navigation;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import lk.drugreminder.R;
import lk.drugreminder.adapter.MedicationAdapter;
import lk.drugreminder.calculations.Calculations;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Medication;

public class MedicationFragmentAdd extends Fragment {

    private LinearLayout lyPills, lyCards, lyBoxes;
    private LinearLayout lyAddPills;
    private Button btnAddByPills, btnAddByCards, btnAddByBoxes;
    private EditText txtPills, txtPillsIncard, txtCards, txtPillsInCardBox, txtCardsInbox, txtBoxes, txtTotalPills, txtRemovePills;
    private EditText txtDose, txtIntervalH, txtIntervalM;
    private TimePicker txtFirstMedication;
    private Button btnSavePills, btnUpdatePills, btnRemovePills, btnRemoveMedication;
    private TextView lblHeaderMedication, lblTotalPills, lblLastAddedPills, lblMediDose, lblInterval, lblMediEnd;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_medication_add, container, false);

        lyAddPills = view.findViewById(R.id.ly_add_pills);

        lyPills = view.findViewById(R.id.ly_pills);
        lyCards = view.findViewById(R.id.ly_cards);
        lyBoxes = view.findViewById(R.id.ly_boxes);

        txtPills = view.findViewById(R.id.txt_pills);
        txtPillsIncard = view.findViewById(R.id.txt_pills_incard);
        txtCards = view.findViewById(R.id.txt_cards);
        txtPillsInCardBox = view.findViewById(R.id.txt_pills_incard_box);
        txtCardsInbox = view.findViewById(R.id.txt_cards_inbox);
        txtBoxes = view.findViewById(R.id.txt_boxes);
        txtTotalPills = view.findViewById(R.id.txt_total_pills);
        lblMediEnd = view.findViewById(R.id.lbl_medi_end);

        lblHeaderMedication = view.findViewById(R.id.lbl_header_medication);
        lblTotalPills = view.findViewById(R.id.lbl_total_pills);
        lblLastAddedPills = view.findViewById(R.id.lbl_lastadded_pills);
        lblMediDose = view.findViewById(R.id.lbl_medi_dose);
        lblInterval = view.findViewById(R.id.lbl_interval);

        lblHeaderMedication.setText(MedicationAdapter.getStaticMedication().getMedicationName());

        txtDose = view.findViewById(R.id.txt_dose);
        txtIntervalH = view.findViewById(R.id.txt_intervalH);
        txtIntervalM = view.findViewById(R.id.txt_intervalM);
        txtFirstMedication = view.findViewById(R.id.txt_first_medication);
        txtRemovePills = view.findViewById(R.id.txt_remove_pills);

        txtDose.setText(MedicationAdapter.getStaticMedication().getDose() + "");
        txtIntervalH.setText(MedicationAdapter.getStaticMedication().getIntervalH() + "");
        txtIntervalM.setText(MedicationAdapter.getStaticMedication().getIntervalM() + "");

        btnSavePills = view.findViewById(R.id.btn_save_pills);
        btnUpdatePills = view.findViewById(R.id.btn_update_pills);
        btnRemovePills = view.findViewById(R.id.btn_remove_pills);
        btnRemoveMedication = view.findViewById(R.id.btn_remove_medication);

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
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Add");
                alert.setMessage("Do you want to add pills?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Integer.parseInt(txtTotalPills.getText().toString()) > 0) {
                            addPills();
                        } else {
                            Toast.makeText(getContext(), "Please enter pills amount", Toast.LENGTH_LONG).show();
                        }
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
        });

        btnUpdatePills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Update");
                alert.setMessage("Do you want to update pills?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updatePills();
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
        });

        btnRemovePills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Remove");
                alert.setMessage("Do you want to remove pills?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removePills();
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
        });

        btnRemoveMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Remove");
                alert.setMessage("Do you want to remove medication?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteMedication();
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
        });

        txtPills.addTextChangedListener(getKeyListener("Pills"));
        txtPillsIncard.addTextChangedListener(getKeyListener("PillsInCard"));
        txtCards.addTextChangedListener(getKeyListener("PillsInCard"));
        txtPillsInCardBox.addTextChangedListener(getKeyListener("PillsInCardBox"));
        txtCardsInbox.addTextChangedListener(getKeyListener("PillsInCardBox"));
        txtBoxes.addTextChangedListener(getKeyListener("PillsInCardBox"));

        return view;
    }

    //add
    private void addPills() {
        DatabaseReference addMedication = FirebaseDB.getDBMedication();
        addMedication.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Medication medication = MedicationAdapter.getStaticMedication();
                if (snapshot.hasChild(medication.getMedicationId())) {
                    medication.setTotalPills(medication.getTotalPills() + Integer.parseInt(txtTotalPills.getText().toString()));
                    medication.setLastAddedPills(Integer.parseInt(txtTotalPills.getText().toString()));
                    medication.setDose(Integer.parseInt(txtDose.getText().toString()));
                    medication.setIntervalH(Integer.parseInt(txtIntervalH.getText().toString()));
                    medication.setIntervalM(Integer.parseInt(txtIntervalM.getText().toString()));
                    medication.setLastMedicationH(txtFirstMedication.getHour());
                    medication.setLastMedicationM(txtFirstMedication.getMinute());
                    int[] nextDueTime = Calculations.calcNextDueTime(medication.getLastMedicationH(), medication.getLastMedicationM(), medication.getIntervalH(), medication.getIntervalM());
                    medication.setNextDueTimeH(nextDueTime[0]);
                    medication.setNextDueTimeM(nextDueTime[1]);
                    addMedication.child(medication.getMedicationId()).setValue(medication);
                    setTextEmpty();
                    Toast.makeText(getContext(), "Pills added successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //update
    private void updatePills() {
        DatabaseReference updateMedication = FirebaseDB.getDBMedication();
        updateMedication.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Medication medication = MedicationAdapter.getStaticMedication();
                if (snapshot.hasChild(medication.getMedicationId())) {
                    medication.setDose(Integer.parseInt(txtDose.getText().toString()));
                    medication.setIntervalH(Integer.parseInt(txtIntervalH.getText().toString()));
                    medication.setIntervalM(Integer.parseInt(txtIntervalM.getText().toString()));
                    medication.setLastMedicationH(txtFirstMedication.getHour());
                    medication.setLastMedicationM(txtFirstMedication.getMinute());
                    int[] nextDueTime = Calculations.calcNextDueTime(medication.getLastMedicationH(), medication.getLastMedicationM(), medication.getIntervalH(), medication.getIntervalM());
                    medication.setNextDueTimeH(nextDueTime[0]);
                    medication.setNextDueTimeM(nextDueTime[1]);
                    updateMedication.child(medication.getMedicationId()).setValue(medication);
                    setTextEmpty();
                    Toast.makeText(getContext(), "Pills updated successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //update
    private void removePills() {
        Medication medication = MedicationAdapter.getStaticMedication();
        if (medication.getTotalPills() >= Integer.parseInt(txtRemovePills.getText().toString())) {
            DatabaseReference updateMedication = FirebaseDB.getDBMedication();
            updateMedication.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(medication.getMedicationId())) {
                        medication.setTotalPills(medication.getTotalPills() - Integer.parseInt(txtRemovePills.getText().toString()));
                        updateMedication.child(medication.getMedicationId()).setValue(medication);
                        txtRemovePills.setText("0");
                        Toast.makeText(getContext(), "Pills removed successfully", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            Toast.makeText(getContext(), "Pills amount exceeded", Toast.LENGTH_LONG).show();
        }
    }

    //delete
    private void deleteMedication() {
        DatabaseReference deleteMedication = FirebaseDB.getDBMedication();
        deleteMedication.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Medication medication = MedicationAdapter.getStaticMedication();
                if (snapshot.hasChild(medication.getMedicationId())) {
                    deleteMedication.child(medication.getMedicationId()).removeValue();
                    Navigation.findNavController(view).navigate(R.id.nav_fragment_medication);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //view all
    private void loadMedication() {
        DatabaseReference dbMedication = FirebaseDB.getDBMedication();
        Query medications = dbMedication.orderByChild("medicationId").equalTo(MedicationAdapter.getStaticMedication().getMedicationId());
        medications.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(MedicationAdapter.getStaticMedication().getMedicationId())) {
                    Medication medication = snapshot.child(MedicationAdapter.getStaticMedication().getMedicationId()).getValue(Medication.class);
                    lblTotalPills.setText(medication.getTotalPills() + " Pills");
                    lblLastAddedPills.setText(medication.getLastAddedPills() + " Pills");
                    lblMediDose.setText(medication.getDose() + " Pills");
                    lblInterval.setText(medication.getIntervalH() + " hours  " + medication.getIntervalM() + " minutes");
                    lblMediEnd.setText(Calculations.pillsEndOn(medication.getTotalPills(), medication.getDose(), medication.getLastMedicationH(), medication.getLastMedicationM(), medication.getIntervalH(), medication.getIntervalM()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setTextEmpty() {
        EditText[] editTexts = {txtPillsInCardBox, txtPills, txtPillsIncard, txtBoxes, txtCards, txtCardsInbox, txtTotalPills};
        for (EditText editText : editTexts) {
            editText.setText("0");
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

    //calculate the total pills
    private TextWatcher getKeyListener(String inputType) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    switch (inputType) {
                        case "Pills": {
                            txtTotalPills.setText(txtPills.getText().toString());
                            break;
                        }
                        case "PillsInCard": {
                            int totPills = Integer.parseInt(txtPillsIncard.getText().toString()) * Integer.parseInt(txtCards.getText().toString());
                            txtTotalPills.setText(totPills + "");
                            break;
                        }
                        case "PillsInCardBox": {
                            int totPills = Integer.parseInt(txtPillsInCardBox.getText().toString()) * Integer.parseInt(txtCardsInbox.getText().toString()) * Integer.parseInt(txtBoxes.getText().toString());
                            txtTotalPills.setText(totPills + "");
                            break;
                        }
                    }
                } catch (NumberFormatException e) {

                }
            }
        };
    }
}
