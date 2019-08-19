package com.kkori.mini_festa.presentation.event.board;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.kkori.mini_festa.R;
import com.kkori.mini_festa.presentation.MainActivity;
import com.kkori.mini_festa.presentation.base.BaseFragment;
import com.kkori.mini_festa.presentation.event.EventListAdapter;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class EventBoardFragment extends BaseFragment implements EventBoardContract.View, NestedScrollView.OnScrollChangeListener {

    @Inject
    EventBoardContract.Presenter presenter;

    @Inject
    EventListAdapter eventListAdapter;

    @BindView(R.id.event_recycler)
    RecyclerView eventRecycler;

    @BindView(R.id.first_loading_progress)
    ProgressBar firstLoadingProgress;

    @BindView(R.id.more_loading_progress)
    ProgressBar moreLoadingProgress;

    @BindView(R.id.nested_scroll)
    NestedScrollView nestedScrollView;

    @Override
    public int initLayoutResource() {
        return R.layout.fragment_event_board;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.loadEvent();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nestedScrollView.setOnScrollChangeListener(this);
        initEventRecyclerView(eventRecycler);

        if (eventListAdapter.getItemCount() > 0) {
            hideProgress(false);
        }
    }

    private void initEventRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(eventListAdapter);
        recyclerView.setHasFixedSize(false);
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
    public void showMoreLoadingProgress() {
        moreLoadingProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress(boolean isMoreLoading) {
        if (isMoreLoading) moreLoadingProgress.setVisibility(View.GONE);
        else firstLoadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (v.getChildAt(v.getChildCount() - 1) != null) {
            if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
                    scrollY > oldScrollY) {
                presenter.loadEvent();
            }
        }
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveEventDetail(EventModel event) {
        MainActivity mainActivity = (MainActivity) getActivity();

        if (mainActivity != null) {
            mainActivity.changeToEventDetailFragment(event);
        }
    }

}
