package com.kkori.mini_festa.presentation.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment implements BaseContract.View {

    private BaseContract.Presenter<?> presenter;

    public abstract int initLayoutResource();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayoutResource(), container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setPresenter(BaseContract.Presenter<?> presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.destroyView();
    }

    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

}

