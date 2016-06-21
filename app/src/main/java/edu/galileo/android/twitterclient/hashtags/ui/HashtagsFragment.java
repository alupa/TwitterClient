package edu.galileo.android.twitterclient.hashtags.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.TwitterClientApp;
import edu.galileo.android.twitterclient.entities.Hashtag;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.hashtags.HashtagsPresenter;
import edu.galileo.android.twitterclient.hashtags.di.HashtagsComponent;
import edu.galileo.android.twitterclient.hashtags.ui.adapters.HashtagsAdapter;
import edu.galileo.android.twitterclient.hashtags.ui.adapters.OnItemClickListener;
import edu.galileo.android.twitterclient.images.ImagesPresenter;
import edu.galileo.android.twitterclient.images.di.ImagesComponent;
import edu.galileo.android.twitterclient.images.ui.adapters.ImagesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashtagsFragment extends Fragment implements HashtagsView, OnItemClickListener {

    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.container)
    FrameLayout container;

    @Inject
    HashtagsPresenter presenter;
    @Inject
    HashtagsAdapter adapter;

    public HashtagsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        setupInjection();
        setupRecyclerView();
        presenter.getHashtagsTweets();
        return view;
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        TwitterClientApp app = (TwitterClientApp) getActivity().getApplication();
        HashtagsComponent hashtagsComponent = app.getHashtagsComponent(this, this);
        //presenter = hashtagsComponent.getPresenter();
        hashtagsComponent.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showImages() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImages() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<Hashtag> items) {
        adapter.setItems(items);
    }

    @Override
    public void onItemClick(Hashtag hashtag) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(hashtag.getTweetUrl()));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
