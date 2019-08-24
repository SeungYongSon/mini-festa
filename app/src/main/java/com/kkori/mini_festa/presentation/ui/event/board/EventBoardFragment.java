package com.kkori.mini_festa.presentation.ui.event.board;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.kkori.mini_festa.R;
import com.kkori.mini_festa.presentation.ui.event.MainActivity;
import com.kkori.mini_festa.presentation.base.BaseFragment;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class EventBoardFragment extends BaseFragment implements EventBoardContract.View, NestedScrollView.OnScrollChangeListener {

    @Inject
    public EventBoardContract.Presenter presenter;

    @Inject
    EventListAdapter eventListAdapter;

    @BindView(R.id.introduce)
    LinearLayout introduce;

    @BindView(R.id.favorite_introduce)
    TextView favoriteIntroduce;

    @BindView(R.id.event_recycler)
    RecyclerView eventRecycler;

    @BindView(R.id.first_loading_progress)
    ProgressBar firstLoadingProgress;

    @BindView(R.id.more_loading_progress)
    ProgressBar moreLoadingProgress;

    @BindView(R.id.nested_scroll)
    NestedScrollView nestedScrollView;

    private boolean isFavoriteIntroduce = false;

    @Override
    public int initLayoutResource() {
        return R.layout.fragment_event_board;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initEvent();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nestedScrollView.setOnScrollChangeListener(this);
        initEventRecyclerView(eventRecycler);

        if (eventListAdapter.getItemCount() > 0) {
            hideLoadingProgress(false);
        }

        showIntroduce(isFavoriteIntroduce);
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
    public void addEventToAdapter(List<EventModel> events) {
        eventListAdapter.add(events);
        eventRecycler.smoothScrollToPosition(eventListAdapter.getInsertPoint());
    }

    @Override
    public void addEventToAdapter(EventModel event) {
        eventListAdapter.add(event);
        eventRecycler.smoothScrollToPosition(eventListAdapter.getInsertPoint());
    }

    @Override
    public void showIntroduce(boolean isFavorite) {
        if (isFavorite) {
            introduce.setVisibility(View.INVISIBLE);
            favoriteIntroduce.setVisibility(View.VISIBLE);
            isFavoriteIntroduce = true;
        } else {
            introduce.setVisibility(View.VISIBLE);
            favoriteIntroduce.setVisibility(View.INVISIBLE);
            isFavoriteIntroduce = false;
        }
    }

    @Override
    public void showLoadingProgress(boolean isMoreLoading) {
        if (isMoreLoading) moreLoadingProgress.setVisibility(View.VISIBLE);
        else firstLoadingProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgress(boolean isMoreLoading) {
        if (isMoreLoading) moreLoadingProgress.setVisibility(View.GONE);
        else firstLoadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (v.getChildAt(v.getChildCount() - 1) != null) {
            if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
                    scrollY > oldScrollY) {
                presenter.loadMoreEvent();
            }
        }
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearEventList() {
        eventListAdapter.remove();
    }

    @Override
    public void moveEventDetail(EventModel event) {
        MainActivity mainActivity = (MainActivity) getActivity();

        if (mainActivity != null) {
            mainActivity.changeToEventDetailFragment(event.getEventId());
        }
    }

}
