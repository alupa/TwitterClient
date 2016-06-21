package edu.galileo.android.twitterclient.hashtags.di;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.android.twitterclient.entities.Hashtag;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.hashtags.HashtagsInteractor;
import edu.galileo.android.twitterclient.hashtags.HashtagsInteractorImpl;
import edu.galileo.android.twitterclient.hashtags.HashtagsPresenter;
import edu.galileo.android.twitterclient.hashtags.HashtagsPresenterImpl;
import edu.galileo.android.twitterclient.hashtags.HashtagsRepository;
import edu.galileo.android.twitterclient.hashtags.HashtagsRepositoryImpl;
import edu.galileo.android.twitterclient.hashtags.ui.HashtagsView;
import edu.galileo.android.twitterclient.hashtags.ui.adapters.HashtagsAdapter;
import edu.galileo.android.twitterclient.images.ImagesInteractor;
import edu.galileo.android.twitterclient.images.ImagesInteractorImpl;
import edu.galileo.android.twitterclient.images.ImagesPresenter;
import edu.galileo.android.twitterclient.images.ImagesPresenterImpl;
import edu.galileo.android.twitterclient.images.ImagesRepository;
import edu.galileo.android.twitterclient.images.ImagesRepositoryImpl;
import edu.galileo.android.twitterclient.images.ui.ImagesView;
import edu.galileo.android.twitterclient.images.ui.adapters.ImagesAdapter;
import edu.galileo.android.twitterclient.images.ui.adapters.OnItemClickListener;
import edu.galileo.android.twitterclient.lib.base.EventBus;
import edu.galileo.android.twitterclient.lib.base.ImageLoader;

/**
 * Created by Alvaro on 20-06-2016.
 */
@Module
public class HashtagsModule {
    private HashtagsView view;
    private edu.galileo.android.twitterclient.hashtags.ui.adapters.OnItemClickListener clickListener;

    public HashtagsModule(edu.galileo.android.twitterclient.hashtags.ui.adapters.OnItemClickListener clickListener, HashtagsView view) {
        this.clickListener = clickListener;
        this.view = view;
    }

    @Provides
    @Singleton
    HashtagsAdapter providesAdapter(edu.galileo.android.twitterclient.hashtags.ui.adapters.OnItemClickListener clickListener, List<Hashtag> dataset){
        return new HashtagsAdapter(clickListener, dataset);
    }

    @Provides
    @Singleton
    edu.galileo.android.twitterclient.hashtags.ui.adapters.OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Hashtag> providesItemsList(){
        return new ArrayList<Hashtag>();
    }

    @Provides
    @Singleton
    HashtagsPresenter providesHashtagsPresenter(EventBus eventBus, HashtagsInteractor interactor,
                                                HashtagsView view){
        return new HashtagsPresenterImpl(eventBus, interactor, view);
    }

    @Provides
    @Singleton
    HashtagsView providesImagesView(){
        return this.view;
    }

    @Provides
    @Singleton
    HashtagsInteractor providesHashtagsInteractor(HashtagsRepository repository){
        return new HashtagsInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagsRepository providesHashtagsRepository(CustomTwitterApiClient client, EventBus eventBus){
        return new HashtagsRepositoryImpl(client, eventBus);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session){
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwitter(){
        return Twitter.getSessionManager().getActiveSession();
    }
}
