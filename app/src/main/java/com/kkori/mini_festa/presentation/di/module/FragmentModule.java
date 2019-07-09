package com.kkori.mini_festa.presentation.di.module;

import com.kkori.mini_festa.presentation.di.module.event.EventModule;
import com.kkori.mini_festa.presentation.di.scope.EventFragmentScope;
import com.kkori.mini_festa.presentation.ui.event.EventFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @EventFragmentScope
    @ContributesAndroidInjector(modules = {EventModule.class})
    abstract EventFragment eventFragment();

}
