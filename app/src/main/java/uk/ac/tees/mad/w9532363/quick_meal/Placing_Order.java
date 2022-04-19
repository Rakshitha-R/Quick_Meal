package uk.ac.tees.mad.w9532363.quick_meal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import uk.ac.tees.mad.w9532363.quick_meal.adapter.PlaceOrderAdapter;
import uk.ac.tees.mad.w9532363.quick_meal.models.Menus;
import uk.ac.tees.mad.w9532363.quick_meal.models.Model_Restaurant_List;

public class Placing_Order extends AppCompatActivity {

    private RecyclerView cart_Items_ViewRecycler;
    private PlaceOrderAdapter placeOrderAdapter;
    private SwitchCompat delivery_switch;
    private EditText name_input;

    private EditText address_input;
    private TextView sub_total_amount;
    private TextView delivery_charge;
    private TextView place_your_order_btn;
    private EditText city_input;
    private EditText state_input;
    private TextView total_charge_amount;
    private EditText pin_code_input;
    private EditText card_number_input;
    private boolean isOnDelivery;
    private EditText card_expiry_input;
    private EditText card_pin_input;
    private TextView delivery_charge_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placing_order);

        Model_Restaurant_List modelRestaurantList = getIntent().getParcelableExtra("Model_Restaurant_List");
        ActionBar bar = getSupportActionBar();
        bar.setTitle(modelRestaurantList.getName());
        bar.setSubtitle(modelRestaurantList.getAddress());
        bar.setDisplayHomeAsUpEnabled(true);

        name_input = findViewById(R.id.name_input);
        address_input = findViewById(R.id.address_input);
        pin_code_input = findViewById(R.id.pin_code_input);
        cart_Items_ViewRecycler = findViewById(R.id.cart_Items_ViewRecycler);
        city_input = findViewById(R.id.city_input);
        card_pin_input = findViewById(R.id.card_pin_input);
        card_number_input = findViewById(R.id.card_number_input);
        delivery_switch = findViewById(R.id.delivery_switch);
        delivery_charge = findViewById(R.id.delivery_charge);
        card_expiry_input = findViewById(R.id.card_expiry_input);
        state_input = findViewById(R.id.state_input);
        sub_total_amount = findViewById(R.id.sub_total_amount);
        place_your_order_btn = findViewById(R.id.place_your_order_btn);
        total_charge_amount = findViewById(R.id.total_charge_amount);
        delivery_charge_amount = findViewById(R.id.delivery_charge_amount);

        delivery_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    name_input.setVisibility(View.VISIBLE);
                    pin_code_input.setVisibility(View.VISIBLE);
                    city_input.setVisibility(View.VISIBLE);
                    isOnDelivery = true;
                    state_input.setVisibility(View.VISIBLE);
                    calculatedTotalAmount(modelRestaurantList);
                    address_input.setVisibility(View.VISIBLE);
                    delivery_charge.setVisibility(View.VISIBLE);
                    delivery_charge_amount.setVisibility(View.VISIBLE);
                }
                else
                {
                    name_input.setVisibility(View.GONE);
                    pin_code_input.setVisibility(View.GONE);
                    city_input.setVisibility(View.GONE);
                    state_input.setVisibility(View.GONE);
                    isOnDelivery = false;
                    calculatedTotalAmount(modelRestaurantList);
                    address_input.setVisibility(View.GONE);
                    delivery_charge.setVisibility(View.GONE);
                    delivery_charge_amount.setVisibility(View.GONE);
                }
            }
        });
        recyclerViewInit(modelRestaurantList);

        calculatedTotalAmount(modelRestaurantList);

        place_your_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOrderPlaceButtonClick(modelRestaurantList);
            }
        });
    }

    private void calculatedTotalAmount(Model_Restaurant_List modelRestaurantList) {
        float subTotal = 0f;
        for (Menus menus : modelRestaurantList.getMenus()){
            subTotal += menus.getPrice()* menus.getTotal_in_the_cart();
        }
        sub_total_amount.setText("£"+ String.format("%.2f", subTotal));
        if (isOnDelivery){
            delivery_charge_amount.setText("£"+ String.format("%.2f", modelRestaurantList.getDelivery_charge()));
            subTotal += modelRestaurantList.getDelivery_charge();
        }
        total_charge_amount.setText("£"+ String.format("%.2f", subTotal));

    }
    private void onOrderPlaceButtonClick(Model_Restaurant_List modelRestaurantList){
        if (isOnDelivery && TextUtils.isEmpty(name_input.getText().toString())){
            name_input.setError("Enter Name Here: ");
            return;
        } else if (isOnDelivery && TextUtils.isEmpty(address_input.getText().toString())){
            address_input.setError("Enter Address Here: ");
            return;
        } else if (isOnDelivery && TextUtils.isEmpty(city_input.getText().toString())){
            city_input.setError("Enter City: ");
            return;
        } else if (isOnDelivery && TextUtils.isEmpty(state_input.getText().toString())){
            state_input.setError("Enter State: ");
            return;
        } else if (isOnDelivery && TextUtils.isEmpty(card_pin_input.getText().toString())) {
            card_pin_input.setError("Enter Card Pin Here/ CVV: ");
            return;
        } else if (isOnDelivery && TextUtils.isEmpty(card_number_input.getText().toString())) {
            card_number_input.setError("Enter Card Number Here: ");
            return;
        }else if (isOnDelivery && TextUtils.isEmpty(card_expiry_input.getText().toString())) {
            card_expiry_input.setError("Enter Card Expiry: ");
            return;
        }
        Intent intent = new Intent(Placing_Order.this, SuccessOrder.class);
        intent.putExtra("Model_Restaurant_List", modelRestaurantList);
        startActivityForResult(intent, 1000);

    }
    private void recyclerViewInit(Model_Restaurant_List modelRestaurantList){
        cart_Items_ViewRecycler.setLayoutManager(new LinearLayoutManager(this));
        placeOrderAdapter = new PlaceOrderAdapter(modelRestaurantList.getMenus());
        cart_Items_ViewRecycler.setAdapter(placeOrderAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            default:
                // Nothing To-Do
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1000){
            setResult(Activity.RESULT_OK);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}