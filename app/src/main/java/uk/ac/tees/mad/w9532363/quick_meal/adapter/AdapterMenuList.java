package uk.ac.tees.mad.w9532363.quick_meal.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import uk.ac.tees.mad.w9532363.quick_meal.Menu_Restaurant;
import uk.ac.tees.mad.w9532363.quick_meal.R;
import uk.ac.tees.mad.w9532363.quick_meal.models.Menus;
import uk.ac.tees.mad.w9532363.quick_meal.models.Model_Restaurant_List;

public class AdapterMenuList extends RecyclerView.Adapter<AdapterMenuList.MyViewHolder> {

    private List<Menus> menus;
    private MenuList_Listener listClick;

    public AdapterMenuList(List<Menus> menus, MenuList_Listener listClick){
        this.menus = menus;
        this.listClick = listClick;
    }

    public void update_Data(List<Menus> menus){
        this.menus = menus;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AdapterMenuList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_menu, parent, false);
        return new MyViewHolder(view);

    };

    @Override
    public void onBindViewHolder(@NonNull AdapterMenuList.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name_Menu.setText(menus.get(position).getName());
        holder.price_Menu.setText("Price :- £" + menus.get(position).getPrice());
        holder.cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Menus menus2 = menus.get(position);
                menus2.setTotal_in_the_cart(1);
                listClick.onClickCartItem(menus2);
                holder.quantity_add.setVisibility(View.VISIBLE);
                holder.cart_btn.setVisibility(View.GONE);
                holder.count_user.setText(menus2.getTotal_in_the_cart()+"");

            }
        });

        Glide.with(holder.image_thumb).load(menus.get(position).getUrl()).into(holder.image_thumb);

        holder.add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menus menus2 = menus.get(position);
                int entire = menus2.getTotal_in_the_cart();
                entire++;
                if (entire<=10)
                {
                    menus2.setTotal_in_the_cart(entire);
                    listClick.onUpdateClickCart(menus2);
                    holder.count_user.setText(entire +"");
                }
                listClick.onClickCartItem(menus2);
                holder.quantity_add.setVisibility(View.VISIBLE);
                holder.cart_btn.setVisibility(View.GONE);
                holder.count_user.setText(menus2.getTotal_in_the_cart()+"");

            }
        });

        holder.minus_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menus menus2 = menus.get(position);
                int entire = menus2.getTotal_in_the_cart();
                entire--;
                if (entire>=0)
                {
                    menus2.setTotal_in_the_cart(entire);
                    listClick.onUpdateClickCart(menus2);
                    holder.count_user.setText(entire +"");
                }
                else {
                    holder.quantity_add.setVisibility(View.GONE);
                    holder.cart_btn.setVisibility(View.VISIBLE);
                    menus2.setTotal_in_the_cart(entire);
                    listClick.onEliminateFromClickCart(menus2);
                }
                listClick.onClickCartItem(menus2);
                holder.quantity_add.setVisibility(View.VISIBLE);
                holder.cart_btn.setVisibility(View.GONE);
                holder.count_user.setText(menus2.getTotal_in_the_cart()+"");

            }
        });

    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name_Menu;
        LinearLayout quantity_add;
        ImageView image_thumb;
        ImageView add_image;
        ImageView minus_image;
        TextView cart_btn;
        TextView count_user;
        TextView price_Menu;

        public MyViewHolder(View view){
            super(view);
            count_user = view.findViewById(R.id.count_user);
            name_Menu = view.findViewById(R.id.name_Menu);
            add_image = view.findViewById(R.id.add_image);
            image_thumb = view.findViewById(R.id.image_thumb);
            minus_image = view.findViewById(R.id.minus_image);
            quantity_add =view.findViewById(R.id.quantity_add);
            cart_btn = view.findViewById(R.id.cart_btn);
            price_Menu = view.findViewById(R.id.price_Menu);
        }
    }

    public interface MenuList_Listener{
        public void onEliminateFromClickCart(Menus menus);
        public void onUpdateClickCart(Menus menus);
        public void onClickCartItem(Menus menus);
    }



}
