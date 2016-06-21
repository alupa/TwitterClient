package edu.galileo.android.twitterclient.hashtags;

import edu.galileo.android.twitterclient.hashtags.events.HashtagsEvent;

/**
 * Created by Alvaro on 17-06-2016.
 */
public interface HashtagsPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagsTweets();
    void onEventMainThread(HashtagsEvent event);
}
