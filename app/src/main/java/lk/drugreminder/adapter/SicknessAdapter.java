package lk.drugreminder.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Sickness;
import lk.drugreminder.ui.sickness.SicknessFragment;

public class SicknessAdapter extends RecyclerView.Adapter<SicknessAdapter.SicknessViewHolder> {

    private List<Sickness> sicknessList;
    private LayoutInflater inflater;
    private Context context;
    private SicknessFragment fragment;
    private static Sickness staticSickness;
    private EditText txtSickness;
    private Button btnSickness;

    public SicknessAdapter(SicknessFragment fragment) {
        this.fragment = fragment;
    }

    public SicknessAdapter(List<Sickness> sicknessList, Context context) {
        this.sicknessList = sicknessList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SicknessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_sickness, parent, false);
        return new SicknessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SicknessViewHolder holder, int position) {
        final Sickness sickness = sicknessList.get(position);
        holder.getTxtSickness().setText(sickness.getSicknessName());
        holder.getTxtSickness().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtSickness.setText(sickness.getSicknessName());
                btnSickness.setText("Update Sickness");
                staticSickness = sickness;
            }
        });

        holder.getTxtDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Delete");
                alert.setMessage("Do you want to delete " + sickness.getSicknessName() + "?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteSickness(sickness);
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
    }

    //delete
    private void deleteSickness(Sickness sickness) {
        DatabaseReference deleteSick = FirebaseDB.getDBSickness();
        deleteSick.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(sickness.getSicknessId())) {
                    deleteSick.child(sickness.getSicknessId()).removeValue();
                    txtSickness.setText("");
                    btnSickness.setText("Add Sickness");
                    Toast.makeText(context, "Sickness deleted successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return sicknessList.size();
    }

    public List<Sickness> getSicknessList() {
        return sicknessList;
    }

    public void setSicknessList(List<Sickness> sicknessList) {
        this.sicknessList = sicknessList;
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

    public SicknessFragment getFragment() {
        return fragment;
    }

    public void setFragment(SicknessFragment fragment) {
        this.fragment = fragment;
    }

    public static Sickness getStaticSickness() {
        return staticSickness;
    }

    public static void setStaticSickness(Sickness staticSickness) {
        SicknessAdapter.staticSickness = staticSickness;
    }

    public void setTxtSickness(EditText txtSickness) {
        this.txtSickness = txtSickness;
    }

    public void setBtnSickness(Button btnSickness) {
        this.btnSickness = btnSickness;
    }

    public class SicknessViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSickness, txtDelete;

        public SicknessViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSickness = itemView.findViewById(R.id.txt_sickness);
            txtDelete = itemView.findViewById(R.id.txt_delete);
        }

        public TextView getTxtSickness() {
            return txtSickness;
        }

        public void setTxtSickness(TextView txtSickness) {
            this.txtSickness = txtSickness;
        }

        public TextView getTxtDelete() {
            return txtDelete;
        }

        public void setTxtDelete(TextView txtDelete) {
            this.txtDelete = txtDelete;
        }
    }

}
