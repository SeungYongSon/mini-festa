package com.kkori.mini_festa.presentation.di.injection;

import com.kkori.mini_festa.presentation.di.module.event.EventModule;
import com.kkori.mini_festa.presentation.di.scope.EventFragmentScope;
import com.kkori.mini_festa.presentation.event.EventFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InjectionFragmentModule {

    @EventFragmentScope
    @ContributesAndroidInjector(modules = {EventModule.class})
    abstract EventFragment eventFragment();

}
