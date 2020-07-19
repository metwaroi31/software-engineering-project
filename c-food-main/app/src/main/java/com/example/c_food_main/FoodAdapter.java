package com.example.c_food_main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.amplify.generated.graphql.ListFoodsQuery;
import com.apollographql.apollo.api.Response;
import com.example.c_food_main.model.FoodModel;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private Context mContext;
    private Response<ListFoodsQuery.Data> foodList;

    FoodAdapter(Context context, Response<ListFoodsQuery.Data> list) {
        this.mContext = context;
        this.foodList = list;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.rv_food_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ImageView image = holder.item_image;
        TextView name, calories;
        Button likeBtn;

        name = holder.item_name;
        calories = holder.item_calories;
        likeBtn = holder.likeBtn;

//        image.setImageResource(foodList.data().listFoods().items().get(position).getImage());
        name.setText(foodList.data().listFoods().items().get(position).name());
        calories.setText(Double.toString((foodList.data().listFoods().items().get(position).weight())));

    }

    @Override
    public int getItemCount() {
        return foodList.data().listFoods().items().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_name, item_calories;
        Button likeBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.food_image);

            item_name = itemView.findViewById(R.id.food_name_text);
            item_calories = itemView.findViewById(R.id.food_calories);
            likeBtn = itemView.findViewById(R.id.like_food_btn);
        }
    }
}
