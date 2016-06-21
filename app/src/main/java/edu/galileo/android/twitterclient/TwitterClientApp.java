package edu.galileo.android.twitterclient;

import android.app.Application;
import android.app.Fragment;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import edu.galileo.android.twitterclient.hashtags.di.DaggerHashtagsComponent;
import edu.galileo.android.twitterclient.hashtags.di.HashtagsComponent;
import edu.galileo.android.twitterclient.hashtags.di.HashtagsModule;
import edu.galileo.android.twitterclient.hashtags.ui.HashtagsView;
import edu.galileo.android.twitterclient.images.di.DaggerImagesComponent;
import edu.galileo.android.twitterclient.images.di.ImagesComponent;
import edu.galileo.android.twitterclient.images.di.ImagesModule;
import edu.galileo.android.twitterclient.images.ui.ImagesView;
import edu.galileo.android.twitterclient.images.ui.adapters.OnItemClickListener;
import edu.galileo.android.twitterclient.lib.di.LibsModule;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Alvaro on 16-06-2016.
 */
public class TwitterClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(
                BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    public ImagesComponent getImagesComponent(android.support.v4.app.Fragment fragment, ImagesView view,
                                              OnItemClickListener clickListener){
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(clickListener, view))
                .build();
    }

    public HashtagsComponent getHashtagsComponent(HashtagsView view,
                                                  edu.galileo.android.twitterclient.hashtags.ui.adapters.OnItemClickListener clickListener){
        return DaggerHashtagsComponent
                .builder()
                .libsModule(new LibsModule(null))
                .hashtagsModule(new HashtagsModule(clickListener, view))
                .build();
    }
}
