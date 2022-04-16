package uk.ac.tees.mad.w9532363.quick_meal.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import uk.ac.tees.mad.w9532363.quick_meal.R;
import uk.ac.tees.mad.w9532363.quick_meal.models.Menus;

public class PlaceOrderAdapter extends RecyclerView.Adapter<PlaceOrderAdapter.MyViewHolder> {

    private List<Menus> menus;


    public PlaceOrderAdapter(List<Menus> menus){
        this.menus = menus;
    }

    public void update_Data(List<Menus> menus){
        this.menus = menus;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PlaceOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_place_order, parent, false);
        return new MyViewHolder(view);

    };

    @Override
    public void onBindViewHolder(@NonNull PlaceOrderAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name_Menu.setText(menus.get(position).getName());
        holder.price_Menu.setText("Price :- £" + String.format("%.2f", menus.get(position).getPrice()*menus.get(position).getTotal_in_the_cart()));
        holder.quantity_Menu.setText("Quantity: "+ menus.get(position).getTotal_in_the_cart());


        Glide.with(holder.image_thumb).load(menus.get(position).getUrl()).into(holder.image_thumb);
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name_Menu;
        ImageView image_thumb;
        TextView quantity_Menu;
        TextView price_Menu;

        public MyViewHolder(View view){
            super(view);

            name_Menu = view.findViewById(R.id.name_Menu);
            image_thumb = view.findViewById(R.id.image_thumb);
            quantity_Menu = view.findViewById(R.id.quantity_Menu);
            price_Menu = view.findViewById(R.id.price_Menu);
        }
    }




}
