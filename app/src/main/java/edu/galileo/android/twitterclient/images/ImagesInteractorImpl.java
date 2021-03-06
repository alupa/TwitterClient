package edu.galileo.android.twitterclient.images;

/**
 * Created by Alvaro on 17-06-2016.
 */
public class ImagesInteractorImpl implements ImagesInteractor {
    private ImagesRepository repository;

    public ImagesInteractorImpl(ImagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getImages();
    }
}
