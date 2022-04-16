package uk.ac.tees.mad.w9532363.quick_meal;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity_Notes extends AppCompatActivity {

    private FirebaseAuth auth;
    RecyclerView recycler;
    StaggeredGridLayoutManager staggeredGrid;
    FloatingActionButton back_off;
    FloatingActionButton note_create;
    FirebaseFirestore firestore;
    FirebaseUser user;


    FirestoreRecyclerAdapter<Model_Firebase, ViewHolderNotes> adapterNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notes);

        user = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();
        note_create = findViewById(R.id.note_create);
        back_off = findViewById(R.id.back_off);
        getSupportActionBar().setTitle("   Write your Notes Here...");

        note_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity_Notes.this, New_Notes_Create.class));
            }
        });

        back_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity_Notes.this, Introduction_Screen.class));
            }
        });

        Query q1 = firestore.collection("Notes Data")
                .document(user.getUid()).collection("My Notes").orderBy("title", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Model_Firebase> allusernotes = new FirestoreRecyclerOptions.Builder<Model_Firebase>().setQuery(q1,Model_Firebase.class).build();

        adapterNotes = new FirestoreRecyclerAdapter<Model_Firebase, ViewHolderNotes>(allusernotes) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderNotes holder, int position, @NonNull Model_Firebase model) {

                ImageView btn_popup = holder.itemView.findViewById(R.id.btn_menu_popup);

                holder.content_Notes.setText(model.getContent());
                holder.title_Notes.setText(model.getTitle());
                String id_Doc = adapterNotes.getSnapshots().getSnapshot(position).getId();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(), details_notes.class);
                        i.putExtra("id_of_notes", id_Doc);
                        i.putExtra("content", model.getContent());
                        i.putExtra("title", model.getTitle());
                        view.getContext().startActivity(i);
                    }
                });


                btn_popup.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View view) {
                        PopupMenu menu = new PopupMenu(view.getContext(), view);
                        menu.setGravity(Gravity.END);

                        menu.getMenu().add("Delete Note").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                DocumentReference reference = firestore.collection("Notes Data").document(user.getUid()).collection("My Notes").document(id_Doc);
                                reference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(view.getContext(), "Note Deleted!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(view.getContext(), "Note Delete Failed!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                return false;
                            }
                        });


                        menu.getMenu().add("Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Intent i = new Intent(view.getContext(), note_edit_activity.class);
                                i.putExtra("id_of_notes", id_Doc);
                                i.putExtra("content", model.getContent());
                                i.putExtra("title", model.getTitle());
                                view.getContext().startActivity(i);
                                return false;
                            }
                        });

                        menu.show();

                    }
                });
            }

            @NonNull
            @Override
            public ViewHolderNotes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_recyclerview, parent, false);
                return new ViewHolderNotes(v);
            }
        };

        recycler = findViewById(R.id.view_recycler);
        recycler.setHasFixedSize(true);

        staggeredGrid = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(staggeredGrid);

        recycler.setAdapter(adapterNotes);
    }

    public class ViewHolderNotes extends RecyclerView.ViewHolder
    {
        LinearLayout notem;
        private TextView title_Notes;
        private TextView content_Notes;

        public ViewHolderNotes(@NonNull View itemView) {
            super(itemView);
            notem = itemView.findViewById(R.id.notes);
            title_Notes = itemView.findViewById(R.id.title_note);
            content_Notes = itemView.findViewById(R.id.content_note);
        }
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
               Toast.makeText(MainActivity_Notes.this,"Successfully Logged-Out!",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(MainActivity_Notes.this, Sign_In.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapterNotes != null)
        {
            adapterNotes.stopListening();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapterNotes.startListening();
    }

}