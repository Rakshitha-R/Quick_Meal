package uk.ac.tees.mad.w9532363.quick_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class New_Notes_Create extends AppCompatActivity {


    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore firestore;
    EditText note_content;
    FloatingActionButton note_save;
    EditText note_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notes_create);
        note_save = findViewById(R.id.note_save);
        note_content = findViewById(R.id.note_content);
        note_title = findViewById(R.id.note_title);

        Toolbar bar = findViewById(R.id.note_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();


        note_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteTitle = note_title.getText().toString();
                String make_content = note_content.getText().toString();
                if (make_content.isEmpty() || noteTitle.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),
                            "Need to fill both the feilds!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DocumentReference reference = firestore.collection("Notes Data")
                            .document(user.getUid()).collection("My Notes").document();
                    Map<String, Object> note = new HashMap<>();
                    note.put("title", noteTitle);
                    note.put("content", make_content);

                    reference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(),
                                    "Successfully Note Created!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(New_Notes_Create.this, MainActivity_Notes.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),
                                    "Failed to create your Note!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}