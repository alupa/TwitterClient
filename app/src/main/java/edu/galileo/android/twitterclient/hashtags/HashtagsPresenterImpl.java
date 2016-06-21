package edu.galileo.android.twitterclient.hashtags;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.twitterclient.hashtags.events.HashtagsEvent;
import edu.galileo.android.twitterclient.hashtags.ui.HashtagsView;
import edu.galileo.android.twitterclient.images.events.ImagesEvent;
import edu.galileo.android.twitterclient.images.ui.ImagesView;
import edu.galileo.android.twitterclient.lib.base.EventBus;

/**
 * Created by Alvaro on 18-06-2016.
 */
public class HashtagsPresenterImpl implements HashtagsPresenter {
    private HashtagsView view;
    private EventBus eventBus;
    private HashtagsInteractor interactor;

    public HashtagsPresenterImpl(EventBus eventBus, HashtagsInteractor interactor, HashtagsView view) {
        this.eventBus = eventBus;
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getHashtagsTweets() {
        if (view != null){
            view.hideImages();
            view.showProgressBar();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(HashtagsEvent event) {
        String errorMsg = event.getError();
        if (view != null){
            view.showImages();
            view.hideProgressBar();
            if (errorMsg != null){
                view.onError(errorMsg);
            }else{
                view.setContent(event.getHashtags());
            }
        }
    }
}
