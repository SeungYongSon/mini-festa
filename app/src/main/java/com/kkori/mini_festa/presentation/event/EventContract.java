package com.kkori.mini_festa.presentation.event;

import com.kkori.mini_festa.presentation.base.BaseContract;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.util.List;

public interface EventContract {

    interface View extends BaseContract.View {
        Boolean checkNetWork();

        void addEventsToAdapter(List<EventModel> events);

        void hideProgress();

        void showToast(String text);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void initEvent();
    }

}
