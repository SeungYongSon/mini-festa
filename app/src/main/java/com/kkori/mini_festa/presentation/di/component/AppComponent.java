package com.kkori.mini_festa.presentation.di.component;

import com.kkori.mini_festa.presentation.di.app.BaseApp;
import com.kkori.mini_festa.presentation.di.module.ActvityModule;
import com.kkori.mini_festa.presentation.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AppModule.class, ActvityModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<BaseApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(BaseApp application);

        AppComponent build();

    }

}
