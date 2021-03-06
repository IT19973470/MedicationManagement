package lk.drugreminder.ui.reminder;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lk.drugreminder.NotificationReceiver;
import lk.drugreminder.R;
import lk.drugreminder.adapter.MedicationAdapter;
import lk.drugreminder.adapter.ReminderAdapter;
import lk.drugreminder.adapter.ReminderHistoryAdapter;
import lk.drugreminder.calculations.Calculations;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Medication;
import lk.drugreminder.model.MedicationDTO;
import lk.drugreminder.model.PillsLog;
import lk.drugreminder.model.Reminder;

public class ReminderAcceptFragment extends Fragment {

    private TextView lblHeaderMedication, txtSickness, txtDose, txtDue, txtNext, txtRemaining, txtEnd, txtTookPills, txtMissedPills;
    private Button btnTakeMedication, btnSkipMedication;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ReminderHistoryAdapter reminderHistoryAdapter;
    private View view;
    static boolean tookPill = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reminder_accept, container, false);
        lblHeaderMedication = view.findViewById(R.id.lbl_header_medication);
        txtSickness = view.findViewById(R.id.txt_sickness);
        txtDose = view.findViewById(R.id.txt_dose);
        txtDue = view.findViewById(R.id.txt_due);
        txtNext = view.findViewById(R.id.txt_next);
        txtRemaining = view.findViewById(R.id.txt_remian);
        txtEnd = view.findViewById(R.id.txt_end);
        txtTookPills = view.findViewById(R.id.txt_took_pills);
        txtMissedPills = view.findViewById(R.id.txt_missed_pills);
        btnTakeMedication = view.findViewById(R.id.btn_took_medication);
        btnSkipMedication = view.findViewById(R.id.btn_skip_medication);

        btnTakeMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Medication");
                alert.setMessage("I take my medication");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        takePills();
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

        btnSkipMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Skip");
                alert.setMessage("Do you want to skip medication?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Navigation.findNavController(view).navigate(R.id.nav_fragment_add_reason);
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

        loadMedication();

        loadPillsLog();

        return view;
    }

    //view
    private void loadPillsLog() {
        List<PillsLog> pillsLogs = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_reminder_history);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        reminderHistoryAdapter = new ReminderHistoryAdapter(this);
        reminderHistoryAdapter.setContext(getActivity());
        reminderHistoryAdapter.setContext(getContext());

        Query dbPillsLog = FirebaseDB.getDBPillsLog().orderByChild("medicationId").equalTo(ReminderAdapter.getReminderStatic().getMedication().getMedicationId());
        dbPillsLog.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //calculation total pills and miss pills
                int tookPills = 0, missedPills = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PillsLog pillsLog = dataSnapshot.getValue(PillsLog.class);
                    if (pillsLog.isTookPills()) {
                        tookPills++;
                    } else {
                        missedPills++;
                    }
                    pillsLogs.add(
                            new PillsLog(pillsLog.getPillsLogId(), pillsLog.getMedication(), pillsLog.isTookPills(), pillsLog.getReason(), pillsLog.getTookTimeH(), pillsLog.getTookTimeM(), pillsLog.getTookDate())
                    );
                }
                Collections.reverse(pillsLogs);
                reminderHistoryAdapter.setPillsLogs(pillsLogs);
                recyclerView.setAdapter(reminderHistoryAdapter);
                txtTookPills.setText(tookPills + " Pills");
                txtMissedPills.setText(missedPills + " Pills");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //view
    private void loadMedication() {
        Query medications = FirebaseDB.getDBMedication().orderByChild("medicationId").equalTo(ReminderAdapter.getReminderStatic().getMedication().getMedicationId());
        medications.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(ReminderAdapter.getReminderStatic().getMedication().getMedicationId())) {
                    Medication medication = snapshot.child(ReminderAdapter.getReminderStatic().getMedication().getMedicationId()).getValue(Medication.class);
                    txtDue.setText(LocalTime.of(medication.getNextDueTimeH(), medication.getNextDueTimeM()).format(DateTimeFormatter.ofPattern("hh:mm a")));
                    int[] nextDueTime = Calculations.calcNextDueTime(medication.getNextDueTimeH(), medication.getNextDueTimeM(), medication.getIntervalH(), medication.getIntervalM());
                    txtNext.setText(LocalTime.of(nextDueTime[0], nextDueTime[1]).format(DateTimeFormatter.ofPattern("hh:mm a")));
                    txtRemaining.setText(medication.getTotalPills() + " Pills");
                    txtEnd.setText(Calculations.pillsEndOn(medication.getTotalPills(), medication.getDose(), medication.getLastMedicationH(), medication.getLastMedicationM(), medication.getIntervalH(), medication.getIntervalM()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        MedicationDTO reminder = ReminderAdapter.getReminderStatic();
        lblHeaderMedication.setText(reminder.getMedicationHeader());
        txtSickness.setText(reminder.getSicknessName());
        txtDose.setText(reminder.getDose());
    }

    //update
    private void takePills() {
        DatabaseReference dbPillsLog = FirebaseDB.getDBPillsLog();
        String id = dbPillsLog.push().getKey();
        LocalTime now = LocalTime.now();
        PillsLog pillsLog = new PillsLog(id, ReminderAdapter.getStaticMedication(), true, "N/A", now.getHour(), now.getMinute(), LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dbPillsLog.child(id).setValue(pillsLog);

        DatabaseReference updateMedication = FirebaseDB.getDBMedication();
        updateMedication.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Medication medication = ReminderAdapter.getStaticMedication();
                if (snapshot.hasChild(medication.getMedicationId())) {
                    medication.setTotalPills(medication.getTotalPills() - medication.getDose());
                    medication.setLastMedicationH(medication.getNextDueTimeH());
                    medication.setLastMedicationM(medication.getNextDueTimeM());
                    int[] nextDueTime = Calculations.calcNextDueTime(medication.getLastMedicationH(), medication.getLastMedicationM(), medication.getIntervalH(), medication.getIntervalM());
                    medication.setNextDueTimeH(nextDueTime[0]);
                    medication.setNextDueTimeM(nextDueTime[1]);
                    medication.setNextDueDay(nextDueTime[2]);
                    tookPill = true;
                    updateMedication.child(medication.getMedicationId()).setValue(medication);
                    Toast.makeText(getContext(), "Took Medication", Toast.LENGTH_LONG).show();
                    Navigation.findNavController(view).navigate(R.id.nav_fragment_medication_take);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
