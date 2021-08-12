package lk.drugreminder.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.model.ReminderDTO;
import lk.drugreminder.ui.reminder.ReminderAcceptFragment;
import lk.drugreminder.view_holder.ReminderHistoryViewHolder;

public class ReminderHistoryAdapter extends RecyclerView.Adapter<ReminderHistoryViewHolder> {

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
        if (reminderDTO.isMissed()) {
            holder.getLblHeaderMedicine().setTextColor(Color.parseColor("red"));
            holder.getLinearLayout().setBackground(holder.getLinearLayout().getContext().getDrawable(R.drawable.card_border_missed));
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

//    public static void setReminderStaticDTO(ReminderDTO reminderStaticDTO) {
//        ReminderAdapter.reminderStaticDTO = reminderStaticDTO;
//    }
}
