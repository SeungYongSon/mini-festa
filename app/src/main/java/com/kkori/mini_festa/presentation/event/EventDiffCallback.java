package com.kkori.mini_festa.presentation.event;

import androidx.recyclerview.widget.DiffUtil;

import com.kkori.mini_festa.presentation.model.EventModel;

import java.util.List;

public class EventDiffCallback extends DiffUtil.Callback {
    private final List<EventModel> oldEventModelList;
    private final List<EventModel> newEventModelList;

    EventDiffCallback(List<EventModel> oldEventModelList, List<EventModel> newEventModelList) {
        this.oldEventModelList = oldEventModelList;
        this.newEventModelList = newEventModelList;
    }

    @Override
    public int getOldListSize() {
        return oldEventModelList.size();
    }

    @Override
    public int getNewListSize() {
        return newEventModelList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldEventModelList.get(oldItemPosition).getEventId() == newEventModelList.get(newItemPosition).getEventId() ;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldEventModelList.get(oldItemPosition).getEventId() == newEventModelList.get(newItemPosition).getEventId() ;
    }

}
