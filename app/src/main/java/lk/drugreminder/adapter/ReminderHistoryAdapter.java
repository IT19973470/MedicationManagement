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
import lk.drugreminder.model.ReminderDTO;
import lk.drugreminder.ui.reminder.ReminderAcceptFragment;

public class ReminderHistoryAdapter extends RecyclerView.Adapter<ReminderHistoryAdapter.ReminderHistoryViewHolder> {

    private List<ReminderDTO> reminderDTOS;
    private LayoutInflater inflater;
    private Context context;
    private ReminderAcceptFragment fragment;
    private static ReminderDTO reminderStaticDTO;

    public ReminderHistoryAdapter(ReminderAcceptFragment fragment) {
        this.fragment = fragment;
    }

    public ReminderHistoryAdapter(List<ReminderDTO> reminderDTOS, Context context) {
        this.reminderDTOS = reminderDTOS;
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
        final ReminderDTO reminderDTO = reminderDTOS.get(position);
        holder.getLblHeaderMedicine().setText(reminderDTO.getMedicineHeader());
        holder.getTxtMedicine().setText(reminderDTO.getMedicine());
        holder.getTxtDose().setText(reminderDTO.getDose());
        holder.getTxtDue().setText(reminderDTO.getDueTime());
        holder.getTxtTook().setText(reminderDTO.getTookTime());
        holder.getLyReason().setVisibility(View.GONE);
        if (reminderDTO.isMissed()) {
            holder.getLblHeaderMedicine().setTextColor(Color.parseColor("red"));
            holder.getLinearLayout().setBackground(holder.getLinearLayout().getContext().getDrawable(R.drawable.card_border_missed));
            holder.getTxtReason().setText(reminderDTO.getReason());
            holder.getLyReason().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return reminderDTOS.size();
    }

    public void setReminderDTOS(List<ReminderDTO> reminderDTOS) {
        this.reminderDTOS = reminderDTOS;
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

    public static ReminderDTO getReminderStaticDTO() {
        return reminderStaticDTO;
    }


    public class ReminderHistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView lblHeaderMedicine, txtMedicine, txtDose, txtDue, txtTook, txtReason;
        private Button btnTakeMedicine;
        private LinearLayout linearLayout, lyReason;
//    private LinearLayout layoutTutor;
//    private ProgressBar zoomProgress;

        public ReminderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            lblHeaderMedicine = itemView.findViewById(R.id.lbl_header_medicine);
            txtMedicine = itemView.findViewById(R.id.txt_medicine);
            txtDose = itemView.findViewById(R.id.txt_dose);
            txtDue = itemView.findViewById(R.id.txt_due);
            txtTook = itemView.findViewById(R.id.txt_took);
            txtReason = itemView.findViewById(R.id.txt_reason);
            btnTakeMedicine = itemView.findViewById(R.id.btn_take_medicine);
            linearLayout = itemView.findViewById(R.id.recycler_reminder_history);
            lyReason = itemView.findViewById(R.id.ly_reason);
//        Intent intent = new Intent(LoginActivity.this, ZoomWaitingActivity.class);
        }

        public TextView getLblHeaderMedicine() {
            return lblHeaderMedicine;
        }

        public void setLblHeaderMedicine(TextView lblHeaderMedicine) {
            this.lblHeaderMedicine = lblHeaderMedicine;
        }

        public TextView getTxtMedicine() {
            return txtMedicine;
        }

        public void setTxtMedicine(TextView txtMedicine) {
            this.txtMedicine = txtMedicine;
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

        public Button getBtnTakeMedicine() {
            return btnTakeMedicine;
        }

        public void setBtnTakeMedicine(Button btnTakeMedicine) {
            this.btnTakeMedicine = btnTakeMedicine;
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

//    public static void setReminderStaticDTO(ReminderDTO reminderStaticDTO) {
//        ReminderAdapter.reminderStaticDTO = reminderStaticDTO;
//    }
}
