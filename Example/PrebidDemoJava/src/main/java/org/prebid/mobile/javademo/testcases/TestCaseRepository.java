package org.prebid.mobile.javademo.testcases;

import com.google.common.collect.Lists;

import org.prebid.mobile.javademo.R;
import org.prebid.mobile.javademo.activities.ads.gam.original.GamOriginalApiDisplayBanner300x250;
import org.prebid.mobile.javademo.activities.ads.gam.original.NoGam;

import java.util.ArrayList;

public class TestCaseRepository {

    public static TestCase lastTestCase;

    public static ArrayList<TestCase> getList() {
        ArrayList<TestCase> result = Lists.newArrayList(
                new TestCase(
                        R.string.gam_original_display_banner_300x250,
                        AdFormat.DISPLAY_BANNER,
                        IntegrationKind.GAM_ORIGINAL,
                        GamOriginalApiDisplayBanner300x250.class
                ),
                new TestCase(
                        R.string.no_gam,
                        AdFormat.DISPLAY_BANNER,
                        IntegrationKind.NO_AD_SERVER,
                        NoGam.class
                )
        );
        return result;
    }

}
