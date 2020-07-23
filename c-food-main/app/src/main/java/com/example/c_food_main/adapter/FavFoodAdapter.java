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
import com.example.c_food_main.model.FoodModel;

import java.util.ArrayList;

public class FavFoodAdapter extends RecyclerView.Adapter<FavFoodAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<FoodModel> foodList;

    public FavFoodAdapter(Context mContext, ArrayList<FoodModel> foodList) {
        this.mContext = mContext;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.rv_fav_food_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView image = holder.imageView;
        TextView name = holder.item_name;
        TextView calories = holder.item_calories;

//        image.setImageResource(foodList.get(position).getImage());
        name.setText(foodList.get(position).getName());
        calories.setText("Calories: "+Integer.toString(foodList.get(position).getCalories()));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView item_name, item_calories;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.fav_food_image);
            item_name = itemView.findViewById(R.id.fav_food_name_text);
            item_calories = itemView.findViewById(R.id.fav_food_calories);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPosition = getAdapterPosition();
                    int image = foodList.get(currentPosition).getImage();
                    String foodname = foodList.get(currentPosition).getName();
                    Intent intent = new Intent(v.getContext(), FoodDetailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("Food_Image",image);
                    intent.putExtra("Food_Name",foodname);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
