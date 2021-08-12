package lk.drugreminder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lk.drugreminder.R;
import lk.drugreminder.model.ReminderDTO;
import lk.drugreminder.ui.reminder.ReminderFragment;
import lk.drugreminder.view_holder.ReminderViewHolder;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderViewHolder> {

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
}
