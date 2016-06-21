package edu.galileo.android.twitterclient.hashtags.ui;

import java.util.List;

import edu.galileo.android.twitterclient.entities.Hashtag;
import edu.galileo.android.twitterclient.entities.Image;

/**
 * Created by Alvaro on 17-06-2016.
 */
public interface HashtagsView {
    void showImages();
    void hideImages();
    void showProgressBar();
    void hideProgressBar();

    void onError(String error);
    void setContent(List<Hashtag> items);
}
