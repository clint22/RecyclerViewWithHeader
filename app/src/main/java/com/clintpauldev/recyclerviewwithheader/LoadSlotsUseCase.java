package com.clintpauldev.recyclerviewwithheader;

import java.util.ArrayList;
import java.util.List;

final class LoadSlotsUseCase {

    List<SlotModel> execute(final ArrayList<SlotModel> stringArray) {
        final List<SlotModel> slotModelList = new ArrayList<>();
        for (SlotModel str : stringArray) {
            slotModelList.add(new SlotModel(str.getSlotToken()));
        }
        return slotModelList;
    }
}
