package uk.ac.tees.mad.w9532363.quick_meal;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uk.ac.tees.mad.w9532363.quick_meal.adapter.AdapterMenuList;
import uk.ac.tees.mad.w9532363.quick_meal.models.Menus;
import uk.ac.tees.mad.w9532363.quick_meal.models.Model_Restaurant_List;

public class Menu_Restaurant extends AppCompatActivity implements AdapterMenuList.MenuList_Listener{

    private List<Menus> menus = null;
    private List<Menus> inCartListItems;
    private TextView btn_check;
    private int entireItemsInTheCart = 0;
    private AdapterMenuList adapterMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_restaurant);

        Model_Restaurant_List modelRestaurantList = getIntent().getParcelableExtra("Model_Restaurant_List");
        ActionBar bar = getSupportActionBar();

        bar.setTitle(modelRestaurantList.getName());
        bar.setSubtitle(modelRestaurantList.getAddress());
        bar.setDisplayHomeAsUpEnabled(true);


        menus = modelRestaurantList.getMenus();
        recyclerViewinit();

        btn_check = findViewById(R.id.btn_checkout);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inCartListItems != null && inCartListItems.size() <= 0){
                    Toast.makeText(Menu_Restaurant.this, "Please add any number of items in the cart for checkout! ", Toast.LENGTH_SHORT).show();
                    return;
                }
                modelRestaurantList.setMenus(inCartListItems);
                Intent intent = new Intent(Menu_Restaurant.this, Placing_Order.class);
                intent.putExtra("Model_Restaurant_List", modelRestaurantList);
                startActivityForResult(intent, 1000);
            }
        });
    }


    private void recyclerViewinit() {

        RecyclerView recyclerView = findViewById(R.id.view_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapterMenuList = new AdapterMenuList(menus, this);

        recyclerView.setAdapter(adapterMenuList);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == Activity.RESULT_OK){
            finish();
        }
    }

    @Override
    public void onEliminateFromClickCart(Menus menus) {
        if (inCartListItems.contains(menus)){
            inCartListItems.remove(menus);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            default:
                // no need right now....
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUpdateClickCart(Menus menus) {
        if (inCartListItems.contains(menus)){
            int index1 = inCartListItems.indexOf(menus);
            inCartListItems.remove(index1);
            inCartListItems.add(index1, menus);

            entireItemsInTheCart = 0;

            for (Menus menus1: inCartListItems){
                entireItemsInTheCart = entireItemsInTheCart + menus1.getTotal_in_the_cart();
            }
            btn_check.setText("Checkout (" + entireItemsInTheCart + " ) items");
        }

    }

    @Override
    public void onClickCartItem(Menus menus) {
        if(inCartListItems==null){
            inCartListItems = new ArrayList<>();
        }
        inCartListItems.add(menus);
        entireItemsInTheCart = 0;

        for (Menus menus1: inCartListItems){
            entireItemsInTheCart = entireItemsInTheCart + menus1.getTotal_in_the_cart();
        }
        btn_check.setText("Checkout (" + entireItemsInTheCart + " ) items");
    }

}