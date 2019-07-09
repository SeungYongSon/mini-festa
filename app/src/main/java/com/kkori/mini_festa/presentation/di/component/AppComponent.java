package com.kkori.mini_festa.presentation.di.component;

import com.kkori.mini_festa.presentation.di.app.BaseApp;
import com.kkori.mini_festa.presentation.di.module.ActivityModule;
import com.kkori.mini_festa.presentation.di.module.AppModule;
import com.kkori.mini_festa.presentation.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AppModule.class, ActivityModule.class, FragmentModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<BaseApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(BaseApp application);

        AppComponent build();

    }

}
