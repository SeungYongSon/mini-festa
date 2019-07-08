package com.kkori.mini_festa.presentation.di.module;

import com.kkori.mini_festa.presentation.di.module.main.MainModule;
import com.kkori.mini_festa.presentation.di.scope.ActivityScope;
import com.kkori.mini_festa.presentation.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActvityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity activityMain();

}
