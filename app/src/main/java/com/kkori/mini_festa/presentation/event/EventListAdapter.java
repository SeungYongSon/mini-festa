package com.kkori.mini_festa.presentation.event;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kkori.mini_festa.R;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.util.ArrayList;
import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {

    private ArrayList<EventModel> eventModels = new ArrayList<>();

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);

        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder holder, int position) {
        holder.bind(eventModels.get(position));
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }

    void add(List<EventModel> eventModels) {
        this.eventModels.addAll(eventModels);
        notifyDataSetChanged();
    }

    class EventListViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageEvent;
        private TextView tvName;
        private TextView tvDate;
        private TextView tvHost;
        private TextView tvPrice;

        private EventListViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEvent = itemView.findViewById(R.id.item_event_image);
            tvName = itemView.findViewById(R.id.item_event_name);
            tvDate = itemView.findViewById(R.id.item_event_date);
            tvHost = itemView.findViewById(R.id.item_event_host);
            tvPrice = itemView.findViewById(R.id.item_event_price);
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
