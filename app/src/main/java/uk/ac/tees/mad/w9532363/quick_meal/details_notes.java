package uk.ac.tees.mad.w9532363.quick_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class details_notes extends AppCompatActivity {

    private TextView note_content_detail;
    FloatingActionButton note_edit;
    private TextView note_title_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_notes);

        Toolbar bar = findViewById(R.id.note_detail_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        note_content_detail = findViewById(R.id.note_content_detail);
        note_edit = findViewById(R.id.note_edit);
        note_title_detail = findViewById(R.id.note_title_detail);
        Intent i = getIntent();


        note_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(view.getContext(), note_edit_activity.class);
                inte.putExtra("content", i.getStringExtra("content"));
                inte.putExtra("title", i.getStringExtra("title"));
                inte.putExtra("id_of_notes", i.getStringExtra("id_of_notes"));
                view.getContext().startActivity(inte);
            }
        });

        note_title_detail.setText(i.getStringExtra("title"));
        note_content_detail.setText(i.getStringExtra("content"));
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