package com.kkori.mini_festa.presentation.event.detail;

import com.kkori.mini_festa.presentation.base.BaseContract;
import com.kkori.mini_festa.presentation.model.EventModel;

public interface EventDetailContract {

    interface View extends BaseContract.View {
        void initUI(EventModel eventModel);
        void setLikeBtnText(boolean isFavorite);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void initEvent(int id);
        void setFavoriteEvent();
    }

}
