package com.enggemy22.personalnotebad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton button;
    private FirebaseFirestore dp = FirebaseFirestore.getInstance();
    private CollectionReference noteBook = dp.collection("NoteBook");
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initate();
        setUPRecyclerView();
    }

    private void setUPRecyclerView() {
        Query query = noteBook.orderBy("title", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Data> options = new FirestoreRecyclerOptions.Builder<Data>()
                .setQuery(query, Data.class)
                .build();
        adapter = new RecyclerAdapter(options);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void initate() {
        recyclerView = findViewById(R.id.recycler);
        button = findViewById(R.id.add_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, AddNote.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

//        FirestoreRecyclerOptions<Data> options = new FirestoreRecyclerOptions.Builder<Data>()
//                .setQuery(query, Data.class)
//                .build();
//        adapter = new FirestoreRecyclerAdapter<Data, DataViewHolder>(options) {
//
//            public  void deleteItem(int position){
//                getSnapshots().getSnapshot(position).getReference().delete();
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull DataViewHolder dataViewHolder, int i, @NonNull Data data) {
//              dataViewHolder.setData(data.getTitle(),data.getBody());
//            }
//
//            @NonNull
//            @Override
//            public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
//                DataViewHolder holder= new DataViewHolder(view);
//                return holder;
//            }
//
//
//        };
//        recyclerView.setAdapter(adapter);
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
//                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//            }
//        }).attachToRecyclerView(recyclerView);
//        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

//    private class DataViewHolder extends RecyclerView.ViewHolder {
//        private View view;
//
//        DataViewHolder(View itemView) {
//            super(itemView);
//            view = itemView;
//        }
//
//        void setData(String dataTitle, String dataBody) {
//            TextView title = view.findViewById(R.id.title);
//            TextView content = view.findViewById(R.id.descreption);
//            title.setText(dataTitle);
//            content.setText(dataBody);
//        }
//
//    }
}

