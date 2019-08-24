package com.kkori.mini_festa.presentation.ui.event.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

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

    @BindView(R.id.time_tv)
    TextView timeTv;

    @BindView(R.id.profile_image)
    CircleImageView profileImage;

    @BindView(R.id.host_tv)
    TextView hostTv;

    @BindView(R.id.ticket_list)
    LinearLayout ticketList;

    @BindView(R.id.ticket_recycler)
    RecyclerView ticketRecycler;

    @BindView(R.id.content_wv)
    WebView contentWv;

    @BindView(R.id.like_btn)
    MaterialButton likeBtn;

    @Inject
    EventDetailContract.Presenter presenter;

    @Inject
    TicketListAdapter ticketListAdapter;

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
        locationTv.setText("at " + eventModel.getLocation().getName());

        if (eventModel.getTicketBoughtCount().equals("외부 이벤트")) {
            ticketBoughtCountTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }

        ticketBoughtCountTv.setText(eventModel.getTicketBoughtCount());

        timeTv.setText(eventModel.getEventProgressTime());

        Glide.with(getContext()).load(eventModel.getProfileImage()).into(profileImage);
        hostTv.setText(eventModel.getHostName());

        String IMAGE_RESIZE = "<style>img{display: inline;height: auto;max-width: 100%;}</style>";
        String BODY_RESIZE = "<body leftmargin=\"0\" topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\">";

        WebSettings settings = contentWv.getSettings();

        settings.setDefaultTextEncodingName("utf-8");
        settings.setTextZoom(100);
        settings.setDefaultFontSize(12);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        contentWv.setLongClickable(false);
        contentWv.setHapticFeedbackEnabled(false);
        contentWv.setOnLongClickListener(v -> true);
        coverIv. setOnTouchListener((v, event) -> (event.getAction() == MotionEvent.ACTION_MOVE));
        contentWv.setWebViewClient(new WebViewClient());
        contentWv.setWebChromeClient(new WebChromeClient());
        contentWv.setNetworkAvailable(true);
        contentWv.setVerticalScrollBarEnabled(false);
        contentWv.setHorizontalScrollBarEnabled(false);
        contentWv.setFocusable(false);
        contentWv.setFocusableInTouchMode(false);
        contentWv.loadData(IMAGE_RESIZE + BODY_RESIZE + eventModel.getContents(),
                "text/html; charset=utf-8", "utf-8");

        if (!eventModel.getTickets().isEmpty()) {
            ticketListAdapter.add(eventModel.getTickets());
            ticketRecycler.setAdapter(ticketListAdapter);
        } else {
            ticketList.setVisibility(View.GONE);
        }

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
