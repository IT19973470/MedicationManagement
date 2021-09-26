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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.model.PillsLog;
import lk.drugreminder.model.Reminder;
import lk.drugreminder.ui.reminder.ReminderAcceptFragment;

public class ReminderHistoryAdapter extends RecyclerView.Adapter<ReminderHistoryAdapter.ReminderHistoryViewHolder> {

    private List<PillsLog> pillsLogs;
    private LayoutInflater inflater;
    private Context context;
    private ReminderAcceptFragment fragment;
    private static PillsLog staticPillsLog;

    public ReminderHistoryAdapter(ReminderAcceptFragment fragment) {
        this.fragment = fragment;
    }

    public ReminderHistoryAdapter(List<PillsLog> pillsLogs, Context context) {
        this.pillsLogs = pillsLogs;
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
        final PillsLog pillsLog = pillsLogs.get(position);
        holder.getLblHeaderMedication().setText("Took Pill");
        holder.getLblTook().setText("Took At :");
        holder.getTxtMedication().setText(pillsLog.getMedication().getMedicationName());
        holder.getTxtDose().setText(pillsLog.getMedication().getDose() + " Pills");
        holder.getTxtDue().setText(LocalTime.of(pillsLog.getMedication().getNextDueTimeH(), pillsLog.getMedication().getNextDueTimeM()).format(DateTimeFormatter.ofPattern("hh:mm a")));
        holder.getTxtTook().setText(pillsLog.getTookDate() + " at " + LocalTime.of(pillsLog.getTookTimeH(), pillsLog.getTookTimeM()).format(DateTimeFormatter.ofPattern("hh:mm a")));
        holder.getLyReason().setVisibility(View.GONE);
        if (!pillsLog.isTookPills()) {
            holder.getLblHeaderMedication().setText("Missed Pill");
            holder.getLblTook().setText("Skipped At :");
            holder.getLblHeaderMedication().setTextColor(Color.parseColor("red"));
            holder.getLinearLayout().setBackground(holder.getLinearLayout().getContext().getDrawable(R.drawable.card_border_missed));
            holder.getTxtReason().setText(pillsLog.getReason());
            holder.getLyReason().setVisibility(View.VISIBLE);
            holder.getLinearLayout().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    staticPillsLog = pillsLog;
                    Navigation.findNavController(view).navigate(R.id.nav_fragment_skip_reason);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return pillsLogs.size();
    }

    public List<PillsLog> getPillsLogs() {
        return pillsLogs;
    }

    public void setPillsLogs(List<PillsLog> pillsLogs) {
        this.pillsLogs = pillsLogs;
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

    public static PillsLog getStaticPillsLog() {
        return staticPillsLog;
    }


    public class ReminderHistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView lblHeaderMedication, txtMedication, txtDose, txtDue, txtTook, txtReason, lblTook;
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
            lblTook = itemView.findViewById(R.id.lbl_took);
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

        public TextView getLblTook() {
            return lblTook;
        }

        public void setLblTook(TextView lblTook) {
            this.lblTook = lblTook;
        }
    }

}
