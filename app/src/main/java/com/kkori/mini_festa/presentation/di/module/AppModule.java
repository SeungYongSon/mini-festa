package com.kkori.mini_festa.presentation.di.module;

import android.app.Application;
import android.content.Context;

import com.kkori.mini_festa.presentation.di.app.BaseApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module(includes = {NetworkModule.class, LocalModule.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(BaseApp application) {
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication(BaseApp application) {
        return application;
    }

    @Provides
    @Singleton
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
