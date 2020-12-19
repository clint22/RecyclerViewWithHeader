package com.clintpauldev.recyclerviewwithheader;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

final class SlotSection extends Section {

    private final String title;
    private final List<SlotModel> slotModelList;
    private final ClickListener clickListener;
    private Context context;
    private int headerPosition;
    private int sessionNumber;

    SlotSection(@NonNull final String title,
                int sessionNumber,
                @NonNull final List<SlotModel> slotModelList,
                @NonNull final ClickListener clickListener,
                Context context) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_items)
                .headerResourceId(R.layout.section_header)
                .build());

        this.title = title;
        this.slotModelList = slotModelList;
        this.clickListener = clickListener;
        this.context = context;
        this.sessionNumber = sessionNumber;
    }

    @Override
    public int getContentItemsTotal() {
        return slotModelList.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(final View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        final SlotModel slotModel = slotModelList.get(position);
        itemHolder.textViewSessionSlot.setText(slotModel.getSlotToken());

        itemHolder.rootView.setOnClickListener(v ->
                clickListener.onItemRootViewClicked(slotModel, sessionNumber)
        );
    }


    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(final View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
        final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
        headerHolder.textViewSessionHeader.setText(title);
    }

    interface ClickListener {
        void onItemRootViewClicked(final SlotModel slotModel, int sessionNumber);
    }
}