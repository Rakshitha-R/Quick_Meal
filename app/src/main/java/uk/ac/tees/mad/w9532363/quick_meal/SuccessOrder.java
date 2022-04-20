package uk.ac.tees.mad.w9532363.quick_meal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import uk.ac.tees.mad.w9532363.quick_meal.models.Model_Restaurant_List;

public class SuccessOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_order);

        Model_Restaurant_List modelRestaurantList = getIntent().getParcelableExtra("Model_Restaurant_List");
        ActionBar bar = getSupportActionBar();
        bar.setTitle(modelRestaurantList.getName());

        bar.setSubtitle(modelRestaurantList.getAddress());
        bar.setDisplayHomeAsUpEnabled(false);

        TextView btn_done =findViewById(R.id.btn_done);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}