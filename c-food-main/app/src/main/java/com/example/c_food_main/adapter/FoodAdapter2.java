package com.example.c_food_main.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c_food_main.R;
import com.example.c_food_main.activity.FoodDetailActivity;
import com.example.c_food_main.model.FoodModel;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class FoodAdapter2 extends RecyclerView.Adapter<FoodAdapter2.ViewHolder> {
    private Context mConText;
    private ArrayList<FoodModel> list;

    public FoodAdapter2(Context mConText, ArrayList<FoodModel> list) {
        this.mConText = mConText;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mConText);
        View view = layoutInflater.inflate(R.layout.rv_food_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           ImageView image = holder.item_image;
           TextView name, calories;
           name = holder.item_name;
           calories = holder.item_calories;
           image.setImageResource(list.get(position).getImage());
           name.setText(list.get(position).getName());
           calories.setText(Integer.toString(list.get(position).getCalories()));

    }

    @Override
    public int getItemCount() {
        return list.size();
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPosition = getAdapterPosition();
                    Log.i("ViewHolderPosition", Integer.toString(currentPosition));
                    String foodName = list.get(currentPosition).getName();
                    //String foodId = li.data().listFoods().items().get(currentPosition).id();
                    Intent intent = new Intent(v.getContext(), FoodDetailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("Food_Name", foodName);
                    //intent.putExtra("Food_id",foodId);
                    v.getContext().startActivity(intent);
                }
            });
            likeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPosition = getAdapterPosition();
                    String foodName = list.get(currentPosition).getName();
                    Toasty.success(v.getContext(), "Add " + foodName + " into favorite food" , Toast.LENGTH_SHORT, true).show();
                }
            });
        }
    }
}
