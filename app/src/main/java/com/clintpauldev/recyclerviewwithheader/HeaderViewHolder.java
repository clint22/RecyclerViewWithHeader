package com.clintpauldev.recyclerviewwithheader;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

final class HeaderViewHolder extends RecyclerView.ViewHolder {

    final TextView textViewSessionHeader;
    HeaderViewHolder(@NonNull final View view) {
        super(view);
        textViewSessionHeader = view.findViewById(R.id.textViewSessionHeader);
    }
}
