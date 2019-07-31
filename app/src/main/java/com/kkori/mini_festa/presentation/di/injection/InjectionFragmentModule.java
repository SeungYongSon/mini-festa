package com.kkori.mini_festa.presentation.di.injection;

import com.kkori.mini_festa.presentation.di.module.event.EventBoardModule;
import com.kkori.mini_festa.presentation.di.scope.EventFragmentScope;
import com.kkori.mini_festa.presentation.event.board.EventBoardFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InjectionFragmentModule {

    @EventFragmentScope
    @ContributesAndroidInjector(modules = {EventBoardModule.class})
    abstract EventBoardFragment eventBoardFragment();

}
