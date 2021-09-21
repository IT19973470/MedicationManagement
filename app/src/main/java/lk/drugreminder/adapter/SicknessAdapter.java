package lk.drugreminder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.model.Sickness;
import lk.drugreminder.ui.sickness.SicknessFragment;

public class SicknessAdapter extends RecyclerView.Adapter<SicknessAdapter.SicknessViewHolder>{

    private List<Sickness> sicknessList;
    private LayoutInflater inflater;
    private Context context;
    private SicknessFragment fragment;
    private static Sickness sicknessStatic;

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
        holder.getTxtSickness().setText(sickness.getDiseaseName());
        holder.getTxtSickness().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sicknessStatic = sickness;
//                Navigation.findNavController(view).navigate(R.id.nav_fragment_reminder_accept);
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
        this.inflater = LayoutInflater.from(context);
    }

    public SicknessFragment getFragment() {
        return fragment;
    }

    public void setFragment(SicknessFragment fragment) {
        this.fragment = fragment;
    }

    public static Sickness getSicknessStatic() {
        return sicknessStatic;
    }

    public static void setSicknessStatic(Sickness sicknessStatic) {
        SicknessAdapter.sicknessStatic = sicknessStatic;
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
