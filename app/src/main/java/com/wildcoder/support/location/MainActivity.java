package com.wildcoder.support.location;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wildcoder.support.location.fragments.BaseFragment;
import com.wildcoder.support.location.fragments.LocationBasedFragment;
import com.wildcoder.support.location.utils.GooglePlayServicesUtilities;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if (GooglePlayServicesUtilities.isGooglePlayServicesAvailable(this))
            getSupportFragmentManager().beginTransaction().replace(R.id.contentPanel, new LocationBasedFragment()).commit();
    }


    @Override
    public void onBackPressed() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList != null) {
            for (Fragment f : fragmentList) {
                if (f != null && f instanceof BaseFragment)
                    if (((BaseFragment) f).onBackPressed())
                        return;
            }
        }
        super.onBackPressed();
    }
   
}
