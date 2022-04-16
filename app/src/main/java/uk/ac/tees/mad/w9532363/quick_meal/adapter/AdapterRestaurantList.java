package uk.ac.tees.mad.w9532363.quick_meal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import uk.ac.tees.mad.w9532363.quick_meal.R;
import uk.ac.tees.mad.w9532363.quick_meal.Restaurant_List;
import uk.ac.tees.mad.w9532363.quick_meal.models.Model_Restaurant_List;

public class AdapterRestaurantList extends RecyclerView.Adapter<AdapterRestaurantList.MyViewHolder> {

    private List<Model_Restaurant_List> model_restaurant_list;
    private Restaurant_ListClick listClick;

    public AdapterRestaurantList(List<Model_Restaurant_List> model_restaurant_list, Restaurant_ListClick listClick){
        this.model_restaurant_list = model_restaurant_list;
        this.listClick = listClick;
    }

    public void update_Data(List<Model_Restaurant_List> model_restaurant_list){
        this.model_restaurant_list = model_restaurant_list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AdapterRestaurantList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler, parent, false);
        return new MyViewHolder(view);

    };

    @Override
    public void onBindViewHolder(@NonNull AdapterRestaurantList.MyViewHolder holder, int position) {

        holder.name_Restaurant.setText(model_restaurant_list.get(position).getName());
        holder.address_Restaurant.setText("Address: " + model_restaurant_list.get(position).getAddress());
        holder.hours_Restaurant.setText("Today's hours: " + "10 AM to 12 PM" );//model_restaurant_list.get(position).getHour());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listClick.onClickItem(model_restaurant_list.get(holder.getAdapterPosition()));
            }
        });

        Glide.with(holder.image_thumb).load(model_restaurant_list.get(position).getImage()).into(holder.image_thumb);

    }

    @Override
    public int getItemCount() {
        return model_restaurant_list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name_Restaurant;
        ImageView image_thumb;
        TextView address_Restaurant;
        TextView hours_Restaurant;

        public MyViewHolder(View view){
            super(view);
            name_Restaurant = view.findViewById(R.id.name_Restaurant);
            image_thumb = view.findViewById(R.id.image_thumb);
            hours_Restaurant = view.findViewById(R.id.hours_Restaurant);
            address_Restaurant = view.findViewById(R.id.address_Restaurant);
        }
    }

    public interface Restaurant_ListClick{
        public void onClickItem(Model_Restaurant_List restaurantmodel);
    }



}
