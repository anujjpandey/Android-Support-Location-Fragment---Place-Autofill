package com.wildcoder.support.location.fragments;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.places.Place;

/**
 * Created by wildcoder on 26/04/16.
 */
public class LocationBasedFragment extends SupportLocationFragment implements SupportLocationFragment.IOnRejectLocationEnabling {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setManualLocationEnabled(false);
        setOnRejectLocationListener(this);

    }

    @Override
    protected void onLocationLoaded(Location location, String mLastUpdateTime) {

    }

    @Override
    protected void onPlaceReceived(Place place) {

    }

    @Override
    protected void onFetching() {

    }

    @Override
    public void onRejectLocationSetting() {

    }
}
