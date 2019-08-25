package com.kkori.mini_festa.presentation.ui.event.board;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kkori.mini_festa.R;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> implements ListUpdateCallback {

    private ArrayList<EventModel> eventModels;
    private EventBoardContract.View view;
    private int insertPoint = -1;

    @Inject
    EventListAdapter(EventBoardContract.View view) {
        this.view = view;
        this.eventModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_board, parent, false);

        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder holder, int position) {
        holder.bind(eventModels.get(position));
        holder.itemView.setOnClickListener(v -> view.moveEventDetail(eventModels.get(position)));
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }


    public void onChanged(int position, int count, Object payload) {
        notifyItemRangeChanged(position, count, payload);
    }

    public void onInserted(int position, int count) {
        if (insertPoint == -1 || insertPoint > position) {
            insertPoint = position;
        }
        notifyItemRangeInserted(position, count);
    }

    public void onMoved(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
    }

    public void onRemoved(int position, int count) {
        notifyItemRangeRemoved(position, count);
    }

    public void updateEmployeeListItem(List<EventModel> eventModels) {
        final EventDiffCallback diffCallback = new EventDiffCallback(this.eventModels, eventModels);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.eventModels.addAll(eventModels);

        diffResult.dispatchUpdatesTo((ListUpdateCallback) this);
    }

    public void updateEmployeeListItem(EventModel eventModel) {
        final EventDiffCallback diffCallback = new EventDiffCallback(this.eventModels, eventModels);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.eventModels.add(eventModel);

        diffResult.dispatchUpdatesTo((ListUpdateCallback) this);
    }

    public void add(List<EventModel> eventModels) {
        this.eventModels.addAll(eventModels);
        notifyDataSetChanged();
    }

    public void add(EventModel eventModel) {
        this.eventModels.add(eventModel);
        notifyDataSetChanged();
    }

    public void remove() {
        this.eventModels.clear();
        notifyDataSetChanged();
    }

    public int getInsertPoint() {
        Log.e("asdf", String.valueOf(insertPoint));
        return 2;
    }

    class EventListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_event_image)
        ImageView imageEvent;
        @BindView(R.id.item_event_name)
        TextView tvName;
        @BindView(R.id.item_event_date)
        TextView tvDate;
        @BindView(R.id.item_event_host)
        TextView tvHost;
        @BindView(R.id.item_event_price)
        TextView tvPrice;

        private EventListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(EventModel eventModel) {
            Glide.with(itemView.getContext()).load(eventModel.getCoverImage()).into(imageEvent);
            tvName.setText(eventModel.getName());
            tvDate.setText(eventModel.getStartDate());
            tvHost.setText(eventModel.getHostName());
            tvPrice.setText(eventModel.getTicketPriceRange());
        }

    }
}
