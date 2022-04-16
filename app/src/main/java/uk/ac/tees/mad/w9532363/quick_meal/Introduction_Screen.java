package uk.ac.tees.mad.w9532363.quick_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Introduction_Screen extends AppCompatActivity {

    private TextView write_notes;
    private TextView order_food;

    private ImageView order_food_image;
    private ImageView write_notes_image;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_screen);
        getSupportActionBar().setTitle("    Select your Category Here...");
        auth = FirebaseAuth.getInstance();

        write_notes = findViewById(R.id.write_notes);
        write_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Introduction_Screen.this,"Opening Notes...",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Introduction_Screen.this, MainActivity_Notes.class);
                startActivity(i);
            }
        });



        order_food = findViewById(R.id.order_food);
        order_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Introduction_Screen.this,"Opening Food Ordering...",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Introduction_Screen.this, Restaurant_List.class);
                startActivity(i);
            }
        });

        write_notes_image = findViewById(R.id.write_notes_image);
        write_notes_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Introduction_Screen.this,"Opening Notes...",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Introduction_Screen.this, MainActivity_Notes.class);
                startActivity(i);
            }
        });



        order_food_image = findViewById(R.id.order_food_image);
        order_food_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Introduction_Screen.this,"Opening Food Ordering...",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Introduction_Screen.this, Restaurant_List.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.log_out:
                auth.signOut();
                finish();
                Toast.makeText(Introduction_Screen.this,"Successfully Logged-Out!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Introduction_Screen.this, Sign_In.class));
        }
        return super.onOptionsItemSelected(item);
    }
}