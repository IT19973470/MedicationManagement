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
import lk.drugreminder.model.Medication;
import lk.drugreminder.model.MedicationDTO;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private List<MedicationDTO> reminders;
    private LayoutInflater inflater;
    private Context context;
    //    private ReminderFragment fragment;
    private static MedicationDTO reminderStatic;
    private static Medication staticMedication;

//    public ReminderAdapter(ReminderFragment fragment) {
//        this.fragment = fragment;
//    }


    public ReminderAdapter() {
    }

    public ReminderAdapter(List<MedicationDTO> reminders, Context context) {
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
        final MedicationDTO reminder = reminders.get(position);
        holder.getLblHeaderMedication().setText(reminder.getMedicationHeader());
        holder.getTxtSickness().setText(reminder.getSicknessName());
        holder.getTxtDose().setText(reminder.getDose());
        holder.getTxtDue().setText(reminder.getNextDueTime());
        holder.getTxtEndOn().setText(reminder.getEndAt());
        holder.getBtnTakeMedication().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reminderStatic = reminder;
                staticMedication = reminder.getMedication();
                Navigation.findNavController(view).navigate(R.id.nav_fragment_reminder_accept);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    public static MedicationDTO getReminderStatic() {
        return reminderStatic;
    }

    public static void setReminderStatic(MedicationDTO reminderStatic) {
        ReminderAdapter.reminderStatic = reminderStatic;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setContext(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

//    public ReminderFragment getFragment() {
//        return fragment;
//    }

    public List<MedicationDTO> getReminders() {
        return reminders;
    }

    public void setReminders(List<MedicationDTO> reminders) {
        this.reminders = reminders;
    }

    public static Medication getStaticMedication() {
        return staticMedication;
    }

    public static void setStaticMedication(Medication staticMedication) {
        ReminderAdapter.staticMedication = staticMedication;
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder {

        private TextView lblHeaderMedication, txtSickness, txtDose, txtDue, txtEndOn;
        private Button btnTakeMedication;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            lblHeaderMedication = itemView.findViewById(R.id.lbl_header_medication);
            txtSickness = itemView.findViewById(R.id.txt_sickness);
            txtDose = itemView.findViewById(R.id.txt_dose);
            txtDue = itemView.findViewById(R.id.txt_due);
            txtEndOn = itemView.findViewById(R.id.txt_end_on);
            btnTakeMedication = itemView.findViewById(R.id.btn_take_medication);
//        Intent intent = new Intent(LoginActivity.this, ZoomWaitingActivity.class);
        }

        public TextView getLblHeaderMedication() {
            return lblHeaderMedication;
        }

        public void setLblHeaderMedication(TextView lblHeaderMedication) {
            this.lblHeaderMedication = lblHeaderMedication;
        }

        public TextView getTxtSickness() {
            return txtSickness;
        }

        public void setTxtSickness(TextView txtSickness) {
            this.txtSickness = txtSickness;
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

        public TextView getTxtEndOn() {
            return txtEndOn;
        }

        public void setTxtEndOn(TextView txtEndOn) {
            this.txtEndOn = txtEndOn;
        }

        public Button getBtnTakeMedication() {
            return btnTakeMedication;
        }

        public void setBtnTakeMedication(Button btnTakeMedication) {
            this.btnTakeMedication = btnTakeMedication;
        }
    }
}
