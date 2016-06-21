package edu.galileo.android.twitterclient.lib.di;

import android.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.lib.GlideImageLoader;
import edu.galileo.android.twitterclient.lib.GreenRobotEventBus;
import edu.galileo.android.twitterclient.lib.base.EventBus;
import edu.galileo.android.twitterclient.lib.base.ImageLoader;

/**
 * Created by Alvaro on 16-06-2016.
 */
@Module
public class LibsModule {
    private android.support.v4.app.Fragment fragment;

    public LibsModule(android.support.v4.app.Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(android.support.v4.app.Fragment fragment){
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    android.support.v4.app.Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
