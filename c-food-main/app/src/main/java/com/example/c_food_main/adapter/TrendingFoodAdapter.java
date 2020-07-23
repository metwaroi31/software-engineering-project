package com.example.c_food_main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c_food_main.R;
import com.example.c_food_main.model.FoodModel;

import java.util.ArrayList;

public class TrendingFoodAdapter extends RecyclerView.Adapter<TrendingFoodAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<FoodModel> foodList;

    public TrendingFoodAdapter(Context context, ArrayList<FoodModel> foodList) {
        this.mContext = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.rv_trending_food_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView image = holder.imageView;
        TextView name = holder.item_name;
        TextView ranking = holder.item_ranking;
        TextView calories = holder.item_calories;

        image.setImageResource(foodList.get(position).getImage());
        name.setText(foodList.get(position).getName());
        calories.setText("Calories: "+Integer.toString(foodList.get(position).getCalories()));
        ranking.setText(Integer.toString(position+1));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView item_name, item_ranking, item_calories;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView =itemView.findViewById(R.id.trending_food_image);
            item_name = itemView.findViewById(R.id.trending_food_name_text);
            item_ranking = itemView.findViewById(R.id.rank_number_text);
            item_calories = itemView.findViewById(R.id.trending_food_calories);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }
}
