package sg.edu.rp.c346.firebasetodoapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;


public class MainActivity extends AppCompatActivity {

    private TextView tvTitle;
    private EditText etTitle;
    private TextView tvDate;
    private EditText etDate;
    private Button btnAdd;

    private FirebaseFirestore db;
    private CollectionReference colRef;
    private DocumentReference docRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        colRef = db.collection("toDoItems");
        docRef = colRef.document("toDoItem");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null){
                    return;
                }

                if (snapshot != null && snapshot.exists()){
                    Message msg = snapshot.toObject(Message.class);
                    tvTitle.setText(msg.getTitle());
                    tvDate.setText(msg.getDate());
                }
            }
        });

        tvTitle = findViewById(R.id.textViewTitle);
        etTitle = findViewById(R.id.editTextTitle);
        tvDate = findViewById(R.id.textViewDate);
        etDate = findViewById(R.id.editTextDate);

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddOnClick(v);
            }
        });


    }

    private void btnAddOnClick(View v) {

        String title = etTitle.getText().toString();
        String date = etDate.getText().toString();
        Message msg = new Message(title, date);
        docRef.set(msg);

    }
}
