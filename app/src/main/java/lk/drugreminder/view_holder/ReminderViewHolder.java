package lk.drugreminder.view_holder;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import lk.drugreminder.R;
import lk.drugreminder.model.ReminderDTO;

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
