package uk.ac.tees.mad.w9532363.quick_meal;

import androidx.annotation.NonNull;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_In extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText password_Address;
    private EditText email_Address;
    private Button sign_in;
    private TextView forgot_password;
    private TextView new_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().setTitle("              Already a User! Login Down.");

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null)
        {
            finish();
            startActivity(new Intent(Sign_In.this, Introduction_Screen.class));

        }

        email_Address = findViewById(R.id.email_Address);
        new_user = findViewById(R.id.new_user);
        password_Address = findViewById(R.id.password_Address);
        sign_in = findViewById(R.id.sign_in);
        forgot_password = findViewById(R.id.forgot_password);


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(Sign_In.this, Forgot_Password.class);
                startActivity(i);
            }
        });

        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(Sign_In.this, Sign_Up.class);
                startActivity(i);
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password  =  password_Address.getText().toString().trim();
                String email =  email_Address.getText().toString().trim();

                if (password.isEmpty() || email.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),
                            "All the Field are required to access the application", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                verificationmailCheck();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Account Does not Exist at the moment!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });
    }
    private void verificationmailCheck()
    {
        FirebaseUser user = auth.getCurrentUser();
        if (user.isEmailVerified()==true)
        {
            Toast.makeText(getApplicationContext(), "Successfully Logged In!", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(Sign_In.this, Introduction_Screen.class));
        }
        else
        {
            Toast.makeText(getApplicationContext(), "First Verify the Email Id!", Toast.LENGTH_SHORT).show();
            auth.signOut();
        }
    }
}