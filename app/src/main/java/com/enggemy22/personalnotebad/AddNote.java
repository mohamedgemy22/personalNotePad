package com.enggemy22.personalnotebad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddNote extends AppCompatActivity {
    private EditText edit_title;
    private EditText edit_Descreption;
    private FloatingActionButton button2;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        iniate();
        uploadData();
    }

    private void uploadData() {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title = edit_title.getText().toString();
                String Descreption = edit_Descreption.getText().toString();
                db.collection("NoteBook")
                        .add(new Data(Title, Descreption))
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(), "note saved", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void iniate() {
        edit_title = findViewById(R.id.txt_Title);
        edit_Descreption = findViewById(R.id.txt_Descreption);
        button2 = findViewById(R.id.saveNote);
    }
}
