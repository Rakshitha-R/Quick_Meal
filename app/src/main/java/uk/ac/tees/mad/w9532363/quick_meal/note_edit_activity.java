package uk.ac.tees.mad.w9532363.quick_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class note_edit_activity extends AppCompatActivity {

    EditText note_content_edit;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore firestore;
    FloatingActionButton note_save_edit;
    EditText note_title_edit;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);


        i = getIntent();
        firestore = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();


        note_content_edit = findViewById(R.id.note_content_edit);
        note_save_edit = findViewById(R.id.note_save_edit);
        note_title_edit = findViewById(R.id.note_title_edit);

        Toolbar bar = findViewById(R.id.note_toolbar_edit);

        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        note_save_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title_new = note_title_edit.getText().toString();
                String content_new = note_content_edit.getText().toString();

                if (content_new.isEmpty() || title_new.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Few Feilds are Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    DocumentReference reference = firestore.collection("Notes Data").document(user.getUid())
                            .collection("My Notes").document(i.getStringExtra("id_of_notes"));
                    Map<String,Object> no = new HashMap<>();
                    no.put("title", title_new);
                    no.put("content", content_new);
                    reference.set(no).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Updated the Notes...", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(note_edit_activity.this, MainActivity_Notes.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to Update the Notes...", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        String title_note = i.getStringExtra("title");
        String content_note = i.getStringExtra("content");
        note_content_edit.setText(content_note);
        note_title_edit.setText(title_note);

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