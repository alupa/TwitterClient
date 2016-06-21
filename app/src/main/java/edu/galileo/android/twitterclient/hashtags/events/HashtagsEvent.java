package edu.galileo.android.twitterclient.hashtags.events;

import java.util.List;

import edu.galileo.android.twitterclient.entities.Hashtag;
import edu.galileo.android.twitterclient.entities.Image;

/**
 * Created by Alvaro on 17-06-2016.
 */
public class HashtagsEvent {
    private String error;
    private List<Hashtag> hashtags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
