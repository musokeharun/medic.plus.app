package com.bms.medicplus.home.fragments.appointments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bms.medicplus.databinding.FragmentAppointmentListItemBinding;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyAppointmentsRecyclerViewAdapter extends RecyclerView.Adapter<MyAppointmentsRecyclerViewAdapter.ViewHolder> {

    private final List<Appointment> appointments;
    private Context context;
    private PowerMenu powerMenu;

    public MyAppointmentsRecyclerViewAdapter(Context context, List<Appointment> items) {
        super();
        appointments = items;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentAppointmentListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

    }


    private final OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
        @Override
        public void onItemClick(int position, PowerMenuItem item) {
            //powerMenu.setSelectedPosition(position); // change selected item
            powerMenu.dismiss();
        }
    };


    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //        public final AppCompatImageView docImage;
        //        public final AppCompatTextView docName;
        //        public final AppCompatTextView docType;
        //        public final AppCompatTextView docPlace;
        //        public final MaterialButton docCall;
        //        public final MaterialButton docCalendar;
        //        public final MaterialButton docTime;
        //        private LinearLayoutCompat actionsLayout;
        public final ImageButton docActions;

        public ViewHolder(FragmentAppointmentListItemBinding binding) {
            super(binding.getRoot());
            docActions = binding.docActions;
        }

    }
}