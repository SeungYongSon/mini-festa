package com.kkori.mini_festa.presentation.ui.event.board;

import com.kkori.mini_festa.presentation.base.BaseContract;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.util.List;

public interface EventBoardContract {

    interface View extends BaseContract.View {
        Boolean checkNetWork();

        void moveEventDetail(EventModel event);

        void addEventToAdapter(List<EventModel> events);

        void addEventToAdapter(EventModel event);

        void showIntroduce(boolean isFavorite);

        void showLoadingProgress(boolean isMoreLoading);

        void hideLoadingProgress(boolean isMoreLoading);

        void showToast(String text);

        void clearEventList();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void initEvent();

        void loadMoreEvent();

        void loadFavoriteEvent();
    }

}
