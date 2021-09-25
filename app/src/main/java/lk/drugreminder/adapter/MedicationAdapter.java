package lk.drugreminder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.model.Medication;
import lk.drugreminder.ui.medication.MedicationFragment;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder> {

    private List<Medication> medicationList;
    private LayoutInflater inflater;
    private Context context;
    private MedicationFragment fragment;
    private static Medication staticMedication;
    private EditText txtMedication;
    private Button btnMedication;

    public MedicationAdapter() {

    }

    public MedicationAdapter(List<Medication> medicationList, Context context) {
        this.medicationList = medicationList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MedicationAdapter.MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_medication, parent, false);
        return new MedicationAdapter.MedicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MedicationAdapter.MedicationViewHolder holder, int position) {
        final Medication medication = medicationList.get(position);
        holder.getTxtMedication().setText(medication.getMedicationName());
        holder.getTxtMedication().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMedication.setText(medication.getMedicationName());
                btnMedication.setText("Update Medication");
                staticMedication = medication;
            }
        });
        holder.getTxtNext().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                staticMedication = medication;
                Navigation.findNavController(view).navigate(R.id.nav_fragment_medication_add);
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicationList.size();
    }

    public EditText getTxtMedication() {
        return txtMedication;
    }

    public void setTxtMedication(EditText txtMedication) {
        this.txtMedication = txtMedication;
    }

    public Button getBtnMedication() {
        return btnMedication;
    }

    public void setBtnMedication(Button btnMedication) {
        this.btnMedication = btnMedication;
    }

    public List<Medication> getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }

    public static Medication getStaticMedication() {
        return staticMedication;
    }

    public static void setStaticMedication(Medication staticMedication) {
        MedicationAdapter.staticMedication = staticMedication;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public MedicationFragment getFragment() {
        return fragment;
    }

    public void setFragment(MedicationFragment fragment) {
        this.fragment = fragment;
    }

    public class MedicationViewHolder extends RecyclerView.ViewHolder {

        private TextView txtMedication, txtNext;

        public MedicationViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMedication = itemView.findViewById(R.id.txt_medication);
            txtNext = itemView.findViewById(R.id.txt_next);
        }

        public TextView getTxtMedication() {
            return txtMedication;
        }

        public void setTxtMedication(TextView txtMedication) {
            this.txtMedication = txtMedication;
        }

        public TextView getTxtNext() {
            return txtNext;
        }

        public void setTxtNext(TextView txtNext) {
            this.txtNext = txtNext;
        }
    }
}
