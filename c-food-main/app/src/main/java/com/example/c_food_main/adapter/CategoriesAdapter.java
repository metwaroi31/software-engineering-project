package com.example.c_food_main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c_food_main.R;
import com.example.c_food_main.activity.FoodDetailActivity;
import com.example.c_food_main.activity.SearchFoodBasedCategories;
import com.example.c_food_main.model.CategoriesModel;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<CategoriesModel> categoriesList;

    public CategoriesAdapter(Context context, ArrayList<CategoriesModel> categoriesList) {
        this.mContext = context;
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.rv_category_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ImageView image = holder.item_image;
            TextView name = holder.item_name;
            image.setImageResource(categoriesList.get(position).getImage());
            name.setText(categoriesList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.category_image);
            item_name = itemView.findViewById(R.id.category_name_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPo = getAdapterPosition();
                    String category = categoriesList.get(currentPo).getName();
                    Intent intent = new Intent(v.getContext(), SearchFoodBasedCategories.class);
                    intent.putExtra("Category",category);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);
                }
            });

        }
    }
}
