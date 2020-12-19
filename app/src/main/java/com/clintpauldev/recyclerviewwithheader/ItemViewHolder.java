package com.clintpauldev.recyclerviewwithheader;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

final class ItemViewHolder extends RecyclerView.ViewHolder {

    final View rootView;
    final TextView textViewSessionSlot;

    ItemViewHolder(@NonNull final View view) {
        super(view);
        rootView = view;
        textViewSessionSlot = view.findViewById(R.id.textViewSessionSlot);
    }
}
