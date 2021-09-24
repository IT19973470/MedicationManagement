package lk.drugreminder.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.model.Reminder;
import lk.drugreminder.ui.reminder.ReminderAcceptFragment;

public class ReminderHistoryAdapter extends RecyclerView.Adapter<ReminderHistoryAdapter.ReminderHistoryViewHolder> {

    private List<Reminder> reminders;
    private LayoutInflater inflater;
    private Context context;
    private ReminderAcceptFragment fragment;
    private static Reminder reminderStaticDTO;

    public ReminderHistoryAdapter(ReminderAcceptFragment fragment) {
        this.fragment = fragment;
    }

    public ReminderHistoryAdapter(List<Reminder> reminders, Context context) {
        this.reminders = reminders;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ReminderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_reminder_history, parent, false);
        return new ReminderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReminderHistoryViewHolder holder, int position) {
        final Reminder reminder = reminders.get(position);
        holder.getLblHeaderMedication().setText(reminder.getMedicationHeader());
        holder.getTxtMedication().setText(reminder.getMedication());
        holder.getTxtDose().setText(reminder.getDose());
        holder.getTxtDue().setText(reminder.getDueTime());
        holder.getTxtTook().setText(reminder.getTookTime());
        holder.getLyReason().setVisibility(View.GONE);
        if (reminder.isMissed()) {
            holder.getLblHeaderMedication().setTextColor(Color.parseColor("red"));
            holder.getLinearLayout().setBackground(holder.getLinearLayout().getContext().getDrawable(R.drawable.card_border_missed));
            holder.getTxtReason().setText(reminder.getReason());
            holder.getLyReason().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setContext(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public ReminderAcceptFragment getFragment() {
        return fragment;
    }

    public static Reminder getReminderStaticDTO() {
        return reminderStaticDTO;
    }


    public class ReminderHistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView lblHeaderMedication, txtMedication, txtDose, txtDue, txtTook, txtReason;
        private Button btnTakeMedication;
        private LinearLayout linearLayout, lyReason;

        public ReminderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            lblHeaderMedication = itemView.findViewById(R.id.lbl_header_medication);
            txtMedication = itemView.findViewById(R.id.txt_medication);
            txtDose = itemView.findViewById(R.id.txt_dose);
            txtDue = itemView.findViewById(R.id.txt_due);
            txtTook = itemView.findViewById(R.id.txt_took);
            txtReason = itemView.findViewById(R.id.txt_reason);
            btnTakeMedication = itemView.findViewById(R.id.btn_take_medication);
            linearLayout = itemView.findViewById(R.id.recycler_reminder_history);
            lyReason = itemView.findViewById(R.id.ly_reason);
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

        public TextView getTxtTook() {
            return txtTook;
        }

        public void setTxtTook(TextView txtTook) {
            this.txtTook = txtTook;
        }

        public TextView getTxtReason() {
            return txtReason;
        }

        public void setTxtReason(TextView txtReason) {
            this.txtReason = txtReason;
        }

        public LinearLayout getLinearLayout() {
            return linearLayout;
        }

        public void setLinearLayout(LinearLayout linearLayout) {
            this.linearLayout = linearLayout;
        }

        public LinearLayout getLyReason() {
            return lyReason;
        }

        public void setLyReason(LinearLayout lyReason) {
            this.lyReason = lyReason;
        }
    }

}
