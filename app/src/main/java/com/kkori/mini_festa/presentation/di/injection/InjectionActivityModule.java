package com.kkori.mini_festa.presentation.di.injection;

import com.kkori.mini_festa.presentation.di.scope.MainActivityScope;
import com.kkori.mini_festa.presentation.ui.event.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InjectionActivityModule {

    @MainActivityScope
    @ContributesAndroidInjector
    abstract MainActivity activityMain();

}
