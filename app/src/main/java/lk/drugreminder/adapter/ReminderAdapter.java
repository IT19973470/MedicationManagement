package lk.drugreminder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.model.Reminder;
import lk.drugreminder.ui.reminder.ReminderFragment;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private List<Reminder> reminders;
    private LayoutInflater inflater;
    private Context context;
    private ReminderFragment fragment;
    private static Reminder reminderStatic;

    public ReminderAdapter(ReminderFragment fragment) {
        this.fragment = fragment;
    }

    public ReminderAdapter(List<Reminder> reminders, Context context) {
        this.reminders = reminders;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_reminder, parent, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReminderViewHolder holder, int position) {
        final Reminder reminder = reminders.get(position);
        holder.getLblHeaderMedication().setText(reminder.getMedicationHeader());
        holder.getTxtMedication().setText(reminder.getMedication());
        holder.getTxtDose().setText(reminder.getDose());
        holder.getTxtDue().setText(reminder.getDueTime());
        holder.getBtnTakeMedication().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reminderStatic = reminder;
                Navigation.findNavController(view).navigate(R.id.nav_fragment_reminder_accept);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    public static Reminder getReminderStatic() {
        return reminderStatic;
    }

    public static void setReminderStatic(Reminder reminderStatic) {
        ReminderAdapter.reminderStatic = reminderStatic;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setContext(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public ReminderFragment getFragment() {
        return fragment;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder {

        private TextView lblHeaderMedication, txtMedication, txtDose, txtDue;
        private Button btnTakeMedication;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            lblHeaderMedication = itemView.findViewById(R.id.lbl_header_medication);
            txtMedication = itemView.findViewById(R.id.txt_medication);
            txtDose = itemView.findViewById(R.id.txt_dose);
            txtDue = itemView.findViewById(R.id.txt_due);
            btnTakeMedication = itemView.findViewById(R.id.btn_take_medication);
//        Intent intent = new Intent(LoginActivity.this, ZoomWaitingActivity.class);
        }

        public TextView getLblHeaderMedication() {
            return lblHeaderMedication;
        }

        public void setLblHeaderMedication(TextView lblHeaderMedication) {
            this.lblHeaderMedication = lblHeaderMedication;
        }

        public TextView getTxtMedication() {
            return txtMedication;
        }

        public void setTxtMedication(TextView txtMedication) {
            this.txtMedication = txtMedication;
        }

        public TextView getTxtDose() {
            return txtDose;
        }

        public void setTxtDose(TextView txtDose) {
            this.txtDose = txtDose;
        }

        public TextView getTxtDue() {
            return txtDue;
        }

        public void setTxtDue(TextView txtDue) {
            this.txtDue = txtDue;
        }

        public Button getBtnTakeMedication() {
            return btnTakeMedication;
        }

        public void setBtnTakeMedication(Button btnTakeMedication) {
            this.btnTakeMedication = btnTakeMedication;
        }
    }
}
