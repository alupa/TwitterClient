package edu.galileo.android.twitterclient.images;

import edu.galileo.android.twitterclient.images.events.ImagesEvent;

/**
 * Created by Alvaro on 17-06-2016.
 */
public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onEventMainThread(ImagesEvent event);
}
