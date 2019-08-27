package com.kkori.mini_festa.presentation.ui.event.detail;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.kkori.mini_festa.R;
import com.kkori.mini_festa.presentation.base.BaseFragment;
import com.kkori.mini_festa.presentation.model.EventModel;
import com.kkori.mini_festa.presentation.model.LocationModel;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class EventDetailFragment extends BaseFragment implements EventDetailContract.View, OnMapReadyCallback {

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

    @BindView(R.id.content_wv)
    WebView contentWv;

    @BindView(R.id.location_name_tv)
    TextView locationNameTv;

    @BindView(R.id.location_address_tv)
    TextView locationAddressTv;

    @BindView(R.id.location_description_tv)
    TextView locationDescriptionTv;

    @BindView(R.id.ticket_list)
    LinearLayout ticketList;

    @BindView(R.id.ticket_recycler)
    RecyclerView ticketRecycler;

    @BindView(R.id.like_btn)
    MaterialButton likeBtn;

    @Inject
    EventDetailContract.Presenter presenter;

    @Inject
    TicketListAdapter ticketListAdapter;

    private SupportMapFragment locationMap;
    private LocationModel location;

    private CompositeDisposable disposable;

    @Override
    public int initLayoutResource() {
        return R.layout.fragment_event_detail;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        disposable = new CompositeDisposable();

        int id = getArguments().getInt("eventId");

        locationMap = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.location_map);

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
        coverIv.setOnTouchListener((v, event) -> (event.getAction() == MotionEvent.ACTION_MOVE));
        contentWv.setNetworkAvailable(true);
        contentWv.setVerticalScrollBarEnabled(false);
        contentWv.setHorizontalScrollBarEnabled(false);
        contentWv.setFocusable(false);
        contentWv.setFocusableInTouchMode(false);

        presenter.initEvent(id);
    }

    @OnClick(R.id.like_btn)
    void likeClick() {
        presenter.setFavoriteEvent();
    }

    @SuppressLint({"SetTextI18n", "SetJavaScriptEnabled"})
    @Override
    public void initUI(EventModel eventModel) {
        nestedScrollView.scrollTo(0, 0);

        location = eventModel.getLocation();

        String IMAGE_RESIZE = "<style>img{display: inline;height: auto;max-width: 100%;}</style>";
        String BODY_RESIZE = "<body leftmargin=\"0\" topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\">";

        disposable.add(Observable.timer(100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    nameTv.setText(eventModel.getName());
                    locationTv.setText("at " + location.getName());

                    if (eventModel.getTicketBoughtCount().equals("외부 이벤트")) {
                        ticketBoughtCountTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }

                    ticketBoughtCountTv.setText(eventModel.getTicketBoughtCount());

                    timeTv.setText(eventModel.getEventProgressTime());

                    hostTv.setText(eventModel.getHostName());

                    locationNameTv.setText(location.getName());
                    locationAddressTv.setText(location.getAddress());
                    if (!location.getDescription().isEmpty())
                        locationDescriptionTv.setText(location.getDescription());
                    else
                        locationDescriptionTv.setVisibility(View.GONE);

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

                    contentWv.loadData(IMAGE_RESIZE + BODY_RESIZE + eventModel.getContents(),
                            "text/html; charset=utf-8", "utf-8");

                    locationMap.getMapAsync(this);
                }));

        disposable.add(Observable.timer(100, TimeUnit.MILLISECONDS)
                .flatMap(ignore -> Observable.just(Glide.with(getContext()).load(eventModel.getCoverImage())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(drawableRequestBuilder -> drawableRequestBuilder.into(coverIv)));

        disposable.add(Observable.timer(110, TimeUnit.MILLISECONDS)
                .flatMap(ignore -> Observable.just(Glide.with(getContext()).load(eventModel.getProfileImage())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(drawableRequestBuilder -> drawableRequestBuilder.into(profileImage)));
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Geocoder geocoder = new Geocoder(getContext());
        MarkerOptions markerOptions = new MarkerOptions();

        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        disposable.add(Single.timer(140, TimeUnit.MILLISECONDS)
                .flatMap(aLong -> Single.just(geocoder))
                .map(geo -> geo.getFromLocationName(location.getAddress(), 5))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addresses -> {
                    if (!addresses.isEmpty()) {
                        Address address = addresses.get(0);

                        LatLng addressPosition = new LatLng(address.getLatitude(), address.getLongitude());

                        markerOptions.position(addressPosition);
                        markerOptions.title(location.getName());
                        markerOptions.snippet(location.getAddress());

                        googleMap.addMarker(markerOptions);

                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(addressPosition));
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
                    } else {
                        showToast("주소를 찾지 못했습니다!");
                    }
                }, throwable -> showToast("네트워크가 원활하지 않습니다.")));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (locationMap != null) {
            locationMap.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposable.dispose();
    }
}
