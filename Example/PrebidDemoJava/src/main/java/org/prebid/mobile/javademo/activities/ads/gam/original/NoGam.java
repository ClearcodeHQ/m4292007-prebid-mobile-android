package org.prebid.mobile.javademo.activities.ads.gam.original;

import android.os.Bundle;

import androidx.annotation.Nullable;

import org.prebid.mobile.BannerAdUnit;
import org.prebid.mobile.BannerBaseAdUnit;
import org.prebid.mobile.Signals;
import org.prebid.mobile.api.rendering.BannerView;
import org.prebid.mobile.javademo.activities.BaseAdActivity;

import java.util.Collections;

// This example is working fine
public class NoGam extends BaseAdActivity {
    private static final String CONFIG_ID = "1";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 250;

    public BannerAdUnit adUnit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createAd();
    }

    private void createAd() {
        BannerBaseAdUnit.Parameters parameters = new BannerBaseAdUnit.Parameters();
        parameters.setApi(Collections.singletonList(Signals.Api.MRAID_2));
        adUnit = new BannerAdUnit(CONFIG_ID, WIDTH, HEIGHT);
        adUnit.setParameters(parameters);

        final org.prebid.mobile.AdSize adSize = new org.prebid.mobile.AdSize(WIDTH, HEIGHT);
        final BannerView bannerView = new BannerView(this, CONFIG_ID, adSize);

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
