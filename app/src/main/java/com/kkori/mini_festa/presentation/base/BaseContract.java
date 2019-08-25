package com.kkori.mini_festa.presentation.base;

public interface BaseContract {

    interface Presenter<T> {
        void createView(T view);

        void destroyView();
    }

    interface View {
        void setPresenter(Presenter<?> presenter);

        void showToast(String text);
    }

}
