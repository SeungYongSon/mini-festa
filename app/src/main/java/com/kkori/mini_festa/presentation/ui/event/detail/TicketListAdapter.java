package com.kkori.mini_festa.presentation.ui.event.detail;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kkori.mini_festa.R;
import com.kkori.mini_festa.presentation.model.TicketModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.TicketListViewHolder> {

    private ArrayList<TicketModel> ticketModels;

    @Inject
    TicketListAdapter() {
        this.ticketModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public TicketListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_detail, parent, false);

        return new TicketListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketListViewHolder holder, int position) {
        holder.bind(ticketModels.get(position), holder.itemView.getContext());
    }

    @Override
    public int getItemCount() {
        return ticketModels.size();
    }

    public void add(List<TicketModel> ticketModels) {
        this.ticketModels.addAll(ticketModels);
        notifyDataSetChanged();
    }

    public void add(TicketModel ticketModel) {
        this.ticketModels.add(ticketModel);
        notifyDataSetChanged();
    }

    class TicketListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.price_tv)
        TextView priceTv;
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.description_tv)
        TextView descriptionTv;
        @BindView(R.id.quantity_tv)
        TextView quantityTv;
        @BindView(R.id.limit_per_user_tv)
        TextView limitPerUserTv;
        @BindView(R.id.sale_date_tv)
        TextView saleDateTv;
        @BindView(R.id.count_tv)
        TextView countTv;

        private TicketListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(TicketModel ticketModel, Context context) {
            priceTv.setText(ticketModel.getPrice());
            quantityTv.setText(ticketModel.getQuantity());
            limitPerUserTv.setText(ticketModel.getLimitPerUser());
            saleDateTv.setText(ticketModel.getSaleDate());
            countTv.setText(ticketModel.getCount());

            if (!ticketModel.getName().isEmpty()) {
                nameTv.setText(ticketModel.getName());
            }

            if (!ticketModel.getDescription().isEmpty()) {
                descriptionTv.setText(ticketModel.getDescription());
            } else {
                descriptionTv.setVisibility(View.GONE);
            }

            if (!ticketModel.isSale()) {
                priceTv.setTextColor(Color.argb(255, 227, 227, 227));
                nameTv.setTextColor(Color.argb(255, 227, 227, 227));
                descriptionTv.setTextColor(Color.argb(255, 227, 227, 227));
                quantityTv.setTextColor(Color.argb(255, 227, 227, 227));
                limitPerUserTv.setTextColor(Color.argb(255, 227, 227, 227));
                saleDateTv.setTextColor(Color.argb(255, 227, 227, 227));
                countTv.setTextColor(Color.argb(255, 227, 227, 227));

                quantityTv.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.ic_quantity_light), null, null, null);
                limitPerUserTv.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.ic_limit_per_user_light), null, null, null);
                saleDateTv.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.ic_sale_date_light), null, null, null);
                countTv.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.ic_count_light), null, null, null);
            }
        }

    }
}
