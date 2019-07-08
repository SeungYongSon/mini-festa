package com.kkori.mini_festa.presentation.base;

public abstract class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter<T> {

    private T view = null;

    @Override
    public void createView(T view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void destroyView() {
        this.view = null;
    }

    public BaseContract.View getView() {
        return view;
    }

}
