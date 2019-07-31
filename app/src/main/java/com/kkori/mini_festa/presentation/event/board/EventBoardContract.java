package com.kkori.mini_festa.presentation.event.board;

import com.kkori.mini_festa.presentation.base.BaseContract;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.util.List;

public interface EventBoardContract {

    interface View extends BaseContract.View {
        Boolean checkNetWork();

        void addEventsToAdapter(List<EventModel> events);

        void showMoreLoadingProgress();

        void hideProgress(boolean isMoreLoading);

        void showToast(String text);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadEvent();
    }

}
