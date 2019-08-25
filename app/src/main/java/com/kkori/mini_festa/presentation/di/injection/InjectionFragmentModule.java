package com.kkori.mini_festa.presentation.di.injection;

import com.kkori.mini_festa.presentation.di.module.event.EventBoardModule;
import com.kkori.mini_festa.presentation.di.module.event.EventDetailModule;
import com.kkori.mini_festa.presentation.di.module.event.EventModule;
import com.kkori.mini_festa.presentation.di.scope.EventFragmentScope;
import com.kkori.mini_festa.presentation.ui.event.board.EventBoardFragment;
import com.kkori.mini_festa.presentation.ui.event.detail.EventDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InjectionFragmentModule {

    @EventFragmentScope
    @ContributesAndroidInjector(modules = {EventModule.class, EventBoardModule.class})
    abstract EventBoardFragment eventBoardFragment();

    @EventFragmentScope
    @ContributesAndroidInjector(modules = {EventModule.class, EventDetailModule.class})
    abstract EventDetailFragment eventDetailFragment();

}
