package com.bms.medicplus.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bms.medicplus.R;
import com.bms.medicplus.models.Boarding;

import java.util.List;

public class BoardingAdapter extends RecyclerView.Adapter<BoardingAdapter.BoardingViewHolder> {
    private final List<Boarding> onBoardingItems;

    public BoardingAdapter(List<Boarding> onBoardingItems) {
        this.onBoardingItems = onBoardingItems;
    }

    @NonNull
    @Override
    public BoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.onboarding_screen, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BoardingViewHolder holder, int position) {
        holder.setOnBoardingData(onBoardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    static class BoardingViewHolder extends RecyclerView.ViewHolder {
        private final TextView textTitle;
        private final TextView textDescription;
        private final ImageView imageOnboarding;

        BoardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            imageOnboarding = itemView.findViewById(R.id.imageOnboarding);
        }

        void setOnBoardingData(Boarding onBoardingItem) {
            textTitle.setText(onBoardingItem.getTitle());
            textDescription.setText(onBoardingItem.getDescription());
            imageOnboarding.setImageResource(onBoardingItem.getImage());
        }
    }
}