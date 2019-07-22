package com.kkori.mini_festa.presentation.event;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kkori.mini_festa.R;
import com.kkori.mini_festa.presentation.model.EventModel;
import com.kkori.mini_festa.presentation.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class EventFragment extends BaseFragment implements EventContract.View {

    @Inject
    EventContract.Presenter presenter;

    @Inject
    EventListAdapter eventListAdapter;

    private ProgressBar loadingProgress;

    @Override
    public int initLayoutResource() {
        return R.layout.fragment_event;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadingProgress = view.findViewById(R.id.loading_progress);

        RecyclerView eventRecycler = view.findViewById(R.id.event_recycler);
        initEventRecyclerView(eventRecycler);

        presenter.initEvent();
    }

    private void initEventRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(eventListAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(false);
/*        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            private int space = 36 ;

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = space;
                outRect.right = space;

                //for vertical scrolling
                outRect.bottom = space;
                outRect.top = space;
            }
        });*/
    }

    @Override
    public Boolean checkNetWork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(CONNECTIVITY_SERVICE);
        return (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected());
    }

    @Override
    public void addEventsToAdapter(List<EventModel> events) {
        eventListAdapter.add(events);
    }

    @Override
    public void hideProgress() {
        loadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

}
