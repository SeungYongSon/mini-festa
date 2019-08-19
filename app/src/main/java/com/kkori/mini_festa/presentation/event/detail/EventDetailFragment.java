package com.kkori.mini_festa.presentation.event.detail;

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
import com.kkori.mini_festa.R;
import com.kkori.mini_festa.presentation.base.BaseFragment;
import com.kkori.mini_festa.presentation.model.EventModel;

import javax.inject.Inject;

import butterknife.BindView;

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

    @BindView(R.id.date_tv)
    TextView dateTv;

    @BindView(R.id.host_tv)
    TextView hostTv;

    @BindView(R.id.ticket_tv)
    TextView ticketTv;

    @BindView(R.id.content_wv)
    WebView contentWv;

    @Override
    public int initLayoutResource() {
        return R.layout.fragment_event_detail;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EventModel eventModel = getArguments().getParcelable("eventModel");

        Glide.with(getContext()).load(eventModel.getCoverImage()).into(coverIv);

        nameTv.setText(eventModel.getName());
        locationTv.setText("at " + eventModel.getLocationName());

        dateTv.setText(eventModel.getStartDate());
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
        contentWv.loadData(IMAGE_RESIZE + ((EventModel) getArguments().getParcelable("eventModel")).getContents(),
                "text/html", "UDF-8");

        ticketTv.setText(eventModel.getTicketPriceRange());
    }
}
