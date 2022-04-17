package uk.ac.tees.mad.w9532363.quick_meal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.List;

import uk.ac.tees.mad.w9532363.quick_meal.adapter.AdapterRestaurantList;
import uk.ac.tees.mad.w9532363.quick_meal.models.Model_Restaurant_List;

public class Restaurant_List extends AppCompatActivity implements AdapterRestaurantList.Restaurant_ListClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        getSupportActionBar().setTitle("         List of Restaurant's");

        List<Model_Restaurant_List> model_restaurantList = getRestaurantData();
        recyclerViewInit(model_restaurantList);

    }

    private  void recyclerViewInit(List<Model_Restaurant_List> model_restaurant_lists){

        RecyclerView viewRecycler = findViewById(R.id.recyclerView);

        viewRecycler.setLayoutManager(new LinearLayoutManager(this));

        AdapterRestaurantList adapterRestaurantList = new AdapterRestaurantList(model_restaurant_lists, this);
        viewRecycler.setAdapter(adapterRestaurantList);

    }

    private List<Model_Restaurant_List> getRestaurantData() {

        InputStream is = getResources().openRawResource(R.raw.data_restaurent);
        Writer write = new StringWriter();
        char[] buff = new char[1024];

        try {
            Reader read = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int m;
            while ((m = read.read(buff)) != -1) {
                write.write(buff,0,m);
            }

            }catch (Exception exception){

        }

      String strJson = write.toString();
        Gson gson = new Gson();
        Model_Restaurant_List[] modelRestaurantList = gson.fromJson(strJson, Model_Restaurant_List[].class);
        List<Model_Restaurant_List> restaurantList = Arrays.asList(modelRestaurantList);

        return restaurantList;
    }

    @Override
    public void onClickItem(Model_Restaurant_List restaurantmodel) {

        Intent intent = new Intent(Restaurant_List.this, Menu_Restaurant.class);
        intent.putExtra("Model_Restaurant_List", restaurantmodel);
        startActivity(intent);

    }
}
