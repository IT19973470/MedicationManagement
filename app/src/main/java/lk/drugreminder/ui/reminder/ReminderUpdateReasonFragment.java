package lk.drugreminder.ui.reminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import lk.drugreminder.R;
import lk.drugreminder.adapter.ReminderHistoryAdapter;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.PillsLog;

public class ReminderUpdateReasonFragment extends Fragment {

    private View view;
    private Button btnUpdateReason, btnDeleteReason;
    private DatabaseReference dbReason;
    private EditText txtReason;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reminder_skip_reason, container, false);

        txtReason = view.findViewById(R.id.txt_reason);
        txtReason.setText(ReminderHistoryAdapter.getStaticPillsLog().getReason());
        btnUpdateReason = view.findViewById(R.id.btn_update);
        btnDeleteReason = view.findViewById(R.id.btn_delete);

        btnUpdateReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtReason.getText().toString().equals("")) {
                    updateReason();
                } else {
                    Toast.makeText(getContext(), "Please enter a reason", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private void updateReason() {
        DatabaseReference updateLog = FirebaseDB.getDBPillsLog();
        updateLog.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PillsLog pillsLog = ReminderHistoryAdapter.getStaticPillsLog();
                if (snapshot.hasChild(pillsLog.getPillsLogId())) {
                    pillsLog.setReason(txtReason.getText().toString());
                    updateLog.child(pillsLog.getPillsLogId()).setValue(pillsLog);
                    txtReason.setText("");
                    Toast.makeText(getContext(), "Reason updated successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
