package uk.ac.tees.mad.w9532363.quick_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {

    FirebaseAuth auth;

    private EditText email_Address;
    private Button recover_btn_password;
    private TextView back_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Hide Action Bar
        ActionBar bar = getSupportActionBar();
        bar.hide();

        //Firebase Recovery
        auth = FirebaseAuth.getInstance();
        // Accessing the XML Layouts...
        email_Address = findViewById(R.id.email_Address);
        recover_btn_password =findViewById(R.id.recover_btn_password);
        back_to_login = findViewById(R.id.back_to_login);

        recover_btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = email_Address.getText().toString().trim();
                if (emailId.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Enter your Email to get a Recovery", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.sendPasswordResetEmail(emailId).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Mail has been sent to recover password",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Forgot_Password.this, Sign_In.class));
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Email given is wrong please check again!!!", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                }
            }
        });












        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Forgot_Password.this, Sign_In.class);
                startActivity(i);
            }
        });


    }
}