package com.kkori.mini_festa.presentation.event.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.kkori.mini_festa.R;
import com.kkori.mini_festa.presentation.base.BaseFragment;
import com.kkori.mini_festa.presentation.model.EventModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class EventDetailFragment extends BaseFragment implements EventDetailContract.View {

    @Inject
    EventDetailContract.Presenter presenter;

    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    @BindView(R.id.cover_image)
    ImageView coverIv;

    @BindView(R.id.name_tv)
    TextView nameTv;

    @BindView(R.id.location_tv)
    TextView locationTv;

    @BindView(R.id.ticket_bought_count_tv)
    TextView ticketBoughtCountTv;

    @BindView(R.id.date_tv)
    TextView dateTv;

    @BindView(R.id.profile_image)
    CircleImageView profileImage;

    @BindView(R.id.host_tv)
    TextView hostTv;

    @BindView(R.id.ticket_tv)
    TextView ticketTv;

    @BindView(R.id.content_wv)
    WebView contentWv;

    @BindView(R.id.like_btn)
    MaterialButton likeBtn;

    @Override
    public int initLayoutResource() {
        return R.layout.fragment_event_detail;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int id = getArguments().getInt("eventId");

        presenter.initEvent(id);
    }

    @OnClick(R.id.like_btn)
    void likeClick() {
        presenter.setFavoriteEvent();
    }

    @SuppressLint({"SetTextI18n", "SetJavaScriptEnabled"})
    @Override
    public void initUI(EventModel eventModel) {
        Glide.with(getContext()).load(eventModel.getCoverImage()).into(coverIv);

        nameTv.setText(eventModel.getName());
        locationTv.setText("at " + eventModel.getLocationName());

        if (eventModel.getTicketBoughtCount().equals("외부 이벤트")) {
            ticketBoughtCountTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }

        ticketBoughtCountTv.setText(eventModel.getTicketBoughtCount());

        dateTv.setText(eventModel.getStartDate() + "\n - " + eventModel.getEndDate());

        Glide.with(getContext()).load(eventModel.getProfileImage()).into(profileImage);
        hostTv.setText(eventModel.getHostName());

        String IMAGE_RESIZE = "<style>img{display: inline;height: auto;max-width: 100%;}</style>";

        contentWv.setLongClickable(false);
        contentWv.setHapticFeedbackEnabled(false);
        contentWv.setOnLongClickListener(v -> true);
        contentWv.getSettings().setTextZoom(100);
        contentWv.getSettings().setDefaultFontSize(12);
        contentWv.setWebViewClient(new WebViewClient());
        contentWv.setWebChromeClient(new WebChromeClient());
        contentWv.setNetworkAvailable(true);
        contentWv.getSettings().setJavaScriptEnabled(true);

        contentWv.getSettings().setDomStorageEnabled(true);
        contentWv.loadData(IMAGE_RESIZE + eventModel.getContents(),
                "text/html", "UDF-8");

        ticketTv.setText(eventModel.getTicketPriceRange());

        if (eventModel.isFavorite()) {
            likeBtn.setText("좋아요 취소");
        } else {
            likeBtn.setText("좋아요");
        }

    }

    @Override
    public void setLikeBtnText(boolean isFavorite) {
        if (isFavorite) {
            likeBtn.setText("좋아요 취소");
            showToast("좋아요 리스트에 추가되었습니다!");
        } else {
            likeBtn.setText("좋아요");
            showToast("좋아요 리스트에서 사라졌습니다!");
        }
    }

}
