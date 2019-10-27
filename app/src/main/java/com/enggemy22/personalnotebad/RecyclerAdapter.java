package com.enggemy22.personalnotebad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RecyclerAdapter extends FirestoreRecyclerAdapter<Data, RecyclerAdapter.RecyclerViewHolder> {

    private AdapterView.OnItemClickListener listener;

    public RecyclerAdapter(@NonNull FirestoreRecyclerOptions<Data> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerViewHolder holder, int i, @NonNull Data data) {
        holder.textTitle.setText(data.title);
        holder.textDescreption.setText(data.body);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle;
        TextView textDescreption;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.titleText);
            textDescreption = itemView.findViewById(R.id.descreptionText);


        }
    }

}
