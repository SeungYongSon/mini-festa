package com.kkori.mini_festa.presentation.di.component;

import com.kkori.mini_festa.FestaApplication;
import com.kkori.mini_festa.presentation.di.injection.InjectionActivityModule;
import com.kkori.mini_festa.presentation.di.module.AppModule;
import com.kkori.mini_festa.presentation.di.injection.InjectionFragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AppModule.class, InjectionActivityModule.class, InjectionFragmentModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<FestaApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(FestaApplication application);

        AppComponent build();

    }

}
