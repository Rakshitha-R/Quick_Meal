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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    private FirebaseAuth auth;

    private EditText user_Name;

    private EditText phone_Number;
    private EditText email_Address;
    private EditText password_Address;
    private Button register;
    private TextView old_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        getSupportActionBar().hide();
        getSupportActionBar().setTitle("                  New! Register Down.");

        user_Name = findViewById(R.id.user_Name);
        phone_Number = findViewById(R.id.phone_Number);
        email_Address = findViewById(R.id.email_Address);
        password_Address = findViewById(R.id.password_Address);
        register = findViewById(R.id.register);
        old_user = findViewById(R.id.old_user);
        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("User Details");

                String name = user_Name.getText().toString().trim();
                String phone = phone_Number.getText().toString().trim();
                String email_Add = email_Address.getText().toString().trim();
                String password_Add = password_Address.getText().toString().trim();

                UserClass userClass = new UserClass(name, phone, email_Add, password_Add);
                reference.child(name).setValue(userClass);


                String email = email_Address.getText().toString().trim();
                String password = password_Address.getText().toString().trim();

                if(password.isEmpty() || email.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),
                            "All the Fields need to be filled to Register the new User!", Toast.LENGTH_SHORT).show();
                }
                else if (password.length()<6)
                {
                    Toast.makeText(getApplicationContext(),
                            "Enter Password more than 6 Characters...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Registration successfully Done!", Toast.LENGTH_SHORT).show();
                                sendVerificationEmail();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Registeration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });


        old_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Sign_Up.this, Sign_In.class);
                startActivity(i);
            }
        });

    }
    private void sendVerificationEmail()
    {
        FirebaseUser user = auth.getCurrentUser();
        if (user!= null)
        {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),
                            "Verify and Log In back using the link sent to the Email!", Toast.LENGTH_LONG).show();
                    auth.signOut();
                    finish();

                    startActivity(new Intent(Sign_Up.this, Sign_In.class));
                }
            });
        }else
        {
            Toast.makeText(getApplicationContext(),
                    "Failed to send the Email for Verification!", Toast.LENGTH_SHORT).show();
        }


    }
}