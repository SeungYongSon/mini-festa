package com.kkori.mini_festa.presentation.di.module;

import android.app.Application;
import android.content.Context;

import com.kkori.mini_festa.FestaApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {NetworkModule.class, LocalModule.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(FestaApplication application) {
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication(FestaApplication application) {
        return application;
    }

}
