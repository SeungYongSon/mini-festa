package com.kkori.mini_festa.presentation.ui.event;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.kkori.mini_festa.R;
import com.kkori.mini_festa.presentation.base.BaseActivity;
import com.kkori.mini_festa.presentation.ui.event.board.EventBoardFragment;
import com.kkori.mini_festa.presentation.ui.event.detail.EventDetailFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    @BindView(R.id.back_image)
    ImageView backImage;

    @BindView(R.id.like_image)
    ImageView likeImage;

    private EventBoardFragment eventBoardFragment = new EventBoardFragment();
    private EventDetailFragment eventDetailFragment = new EventDetailFragment();

    @Override
    public int initLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, eventBoardFragment)
                .commit();

        backImage.setVisibility(View.INVISIBLE);
        likeImage.setVisibility(View.VISIBLE);

        likeImage.setTag(R.drawable.ic_favorite_border);
    }

    @OnClick(R.id.back_image)
    void backClick() {
        if (backImage.getVisibility() == View.VISIBLE) {
            onBackPressed();
        }
    }

    @OnClick(R.id.like_image)
    void likeClick() {
        if (likeImage.getVisibility() == View.VISIBLE) {
            Integer tag = (Integer) likeImage.getTag();

            if (tag == R.drawable.ic_favorite_border) {
                likeImage.setImageResource(R.drawable.ic_favorite);
                likeImage.setTag(R.drawable.ic_favorite);
                eventBoardFragment.presenter.loadFavoriteEvent();
            } else {
                likeImage.setImageResource(R.drawable.ic_favorite_border);
                likeImage.setTag(R.drawable.ic_favorite_border);
                eventBoardFragment.presenter.initEvent();
            }
        }
    }

    public void changeToEventDetailFragment(int eventId) {
        Bundle args = new Bundle();

        args.putInt("eventId", eventId);

        eventDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.main_fragment, eventDetailFragment)
                .addToBackStack(null)
                .commit();

        backImage.setVisibility(View.VISIBLE);
        likeImage.setVisibility(View.INVISIBLE);

        appBarLayout.setExpanded(true);
    }

    @Override
    public void onBackPressed() {
        appBarLayout.setExpanded(true);

        if (getSupportFragmentManager().beginTransaction().isAddToBackStackAllowed()) {
            backImage.setVisibility(View.INVISIBLE);
            likeImage.setVisibility(View.VISIBLE);
        }

        super.onBackPressed();
    }
}
