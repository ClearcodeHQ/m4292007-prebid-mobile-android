package org.prebid.mobile.javademo.activities.ads.gam.original;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;

import org.prebid.mobile.BannerAdUnit;
import org.prebid.mobile.BannerBaseAdUnit;
import org.prebid.mobile.PrebidMobile;
import org.prebid.mobile.Signals;
import org.prebid.mobile.addendum.AdViewUtils;
import org.prebid.mobile.addendum.PbFindSizeError;
import org.prebid.mobile.api.rendering.BannerView;
import org.prebid.mobile.eventhandlers.GamBannerEventHandler;
import org.prebid.mobile.javademo.activities.BaseAdActivity;

import java.util.Collections;

public class GamOriginalApiDisplayBanner300x250 extends BaseAdActivity {
    public BannerAdUnit adUnit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createAd();
    }

    private void createAd() {
        BannerBaseAdUnit.Parameters parameters = new BannerBaseAdUnit.Parameters();
        parameters.setApi(Collections.singletonList(Signals.Api.MRAID_2));
        final String CONFIG_ID = "2"; // 1 for admaru bidder stored request, 2 for appnexus

        org.prebid.mobile.AdSize adSize = new org.prebid.mobile.AdSize(300, 250);
        GamBannerEventHandler eventHandler = new GamBannerEventHandler(this, "/1249652/admaruSSP_display_test", adSize);
        final BannerView bannerView = new BannerView(this, CONFIG_ID, eventHandler);


        getAdWrapperView().addView(bannerView);

        bannerView.loadAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adUnit != null) {
            adUnit.stopAutoRefresh();
        }
    }
}