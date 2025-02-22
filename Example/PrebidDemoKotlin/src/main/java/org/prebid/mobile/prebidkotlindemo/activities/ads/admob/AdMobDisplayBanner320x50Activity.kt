/*
 *    Copyright 2018-2019 Prebid.org, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.prebid.mobile.prebidkotlindemo.activities.ads.admob

import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import org.prebid.mobile.AdSize
import org.prebid.mobile.PrebidMobile
import org.prebid.mobile.admob.AdMobMediationBannerUtils
import org.prebid.mobile.admob.PrebidBannerAdapter
import org.prebid.mobile.api.mediation.MediationBannerAdUnit
import org.prebid.mobile.prebidkotlindemo.activities.BaseAdActivity

class AdMobDisplayBanner320x50Activity : BaseAdActivity() {

    companion object {
        const val AD_UNIT_ID = "ca-app-pub-1875909575462531/3793078260"
        const val CONFIG_ID = "imp-prebid-banner-320-50"
        const val STORED_RESPONSE = "response-prebid-banner-320-50"
        const val WIDTH = 320
        const val HEIGHT = 50
    }

    private var bannerView: AdView? = null
    private var adUnit: MediationBannerAdUnit? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // The ID of Mocked Bid Response on PBS. Only for test cases.
        PrebidMobile.setStoredAuctionResponse(STORED_RESPONSE)

        createAd()
    }

    private fun createAd() {
        /** Google recommends put activity for mediation ad networks */
        bannerView = AdView(this)
        bannerView?.setAdSize(com.google.android.gms.ads.AdSize.BANNER)
        bannerView?.adUnitId = AD_UNIT_ID
        bannerView?.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Log.d("AdMobBanner", "Won ad network: ${bannerView?.responseInfo?.mediationAdapterClassName}")
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                Log.d("AdMobBanner", "Failed to load ad $p0")
            }
        }
        adWrapperView.addView(bannerView)

        val extras = Bundle()
        val request = AdRequest
            .Builder()
            .addNetworkExtrasBundle(PrebidBannerAdapter::class.java, extras)
            .build()
        val mediationUtils = AdMobMediationBannerUtils(extras, bannerView)

        adUnit = MediationBannerAdUnit(
            this,
            CONFIG_ID,
            AdSize(WIDTH, HEIGHT),
            mediationUtils
        )
        adUnit?.setRefreshInterval(refreshTimeSeconds / 1000)
        adUnit?.fetchDemand { _ ->
            bannerView?.loadAd(request)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        bannerView?.destroy()
        adUnit?.destroy()
    }

}
