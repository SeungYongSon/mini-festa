package com.kkori.mini_festa.presentation.di.module;

import com.kkori.mini_festa.presentation.di.scope.MainActivityScope;
import com.kkori.mini_festa.presentation.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @MainActivityScope
    @ContributesAndroidInjector
    abstract MainActivity activityMain();

}
