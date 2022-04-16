package uk.ac.tees.mad.w9532363.quick_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button register, login;
    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        register = findViewById(R.id.button_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Register directed!",Toast.LENGTH_LONG).show();
                Intent intent_register = new Intent(MainActivity.this, Sign_Up.class);
                startActivity(intent_register);
            }
        });



        login = findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Login directed!", Toast.LENGTH_LONG).show();
                Intent intent_login = new Intent(MainActivity.this,Sign_In.class);
                startActivity(intent_login);
            }
        });

        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_skip = new Intent(MainActivity.this, Sign_Up.class);
                startActivity(intent_skip);

                Toast.makeText(MainActivity.this,
                        "Sorry Cannot Enter without Registering! Please Register", Toast.LENGTH_SHORT).show();
            }
        });


    }
}