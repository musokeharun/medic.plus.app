package com.bms.medicplus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bms.medicplus.R;
import com.bms.medicplus.models.Category;
import com.bumptech.glide.Glide;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    LayoutInflater inflater;
    Context context;
    List<Category> list;

    public CategoriesAdapter(Context context, List<Category> categories) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        list = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        Category category = list.get(position);
        holder.textView.setText(category.getText());
        Glide.with(context)
                .load(category.getImage())
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    void add() {

    }


    public void changed(List<Category> categories) {
        list = categories;
        notifyDataSetChanged();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private View layout;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.image);
            imageView = itemView.findViewById(R.id.text);
            layout = itemView.findViewById(R.id.parent);
        }
    }

}
