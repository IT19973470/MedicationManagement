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
import lk.drugreminder.model.ReminderDTO;
import lk.drugreminder.ui.reminder.ReminderFragment;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private List<ReminderDTO> reminderDTOS;
    private LayoutInflater inflater;
    private Context context;
    private ReminderFragment fragment;
    private static ReminderDTO reminderStaticDTO;

    public ReminderAdapter(ReminderFragment fragment) {
        this.fragment = fragment;
    }

    public ReminderAdapter(List<ReminderDTO> reminderDTOS, Context context) {
        this.reminderDTOS = reminderDTOS;
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
        final ReminderDTO reminderDTO = reminderDTOS.get(position);
        holder.getLblHeaderMedicine().setText(reminderDTO.getMedicineHeader());
        holder.getTxtMedicine().setText(reminderDTO.getMedicine());
        holder.getTxtDose().setText(reminderDTO.getDose());
        holder.getTxtDue().setText(reminderDTO.getDueTime());
        holder.getBtnTakeMedicine().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reminderStaticDTO = reminderDTO;
                Navigation.findNavController(view).navigate(R.id.nav_fragment_reminder_accept);
            }
        });
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

    public ReminderFragment getFragment() {
        return fragment;
    }

    public static ReminderDTO getReminderStaticDTO() {
        return reminderStaticDTO;
    }

    public static void setReminderStaticDTO(ReminderDTO reminderStaticDTO) {
        ReminderAdapter.reminderStaticDTO = reminderStaticDTO;
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder {

        private TextView lblHeaderMedicine, txtMedicine, txtDose, txtDue;
        private Button btnTakeMedicine;
//    private LinearLayout layoutTutor;
//    private ProgressBar zoomProgress;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            lblHeaderMedicine = itemView.findViewById(R.id.lbl_header_medicine);
            txtMedicine = itemView.findViewById(R.id.txt_medicine);
            txtDose = itemView.findViewById(R.id.txt_dose);
            txtDue = itemView.findViewById(R.id.txt_due);
            btnTakeMedicine = itemView.findViewById(R.id.btn_take_medicine);
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
    }
}
