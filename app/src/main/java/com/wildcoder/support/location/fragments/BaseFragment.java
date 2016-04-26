package com.wildcoder.support.location.fragments;

import android.support.v4.app.Fragment;
import android.view.View;


/**
 * Created by Wild Coder on 28-10-2015.
 */
public class BaseFragment extends Fragment implements IFragmentListener {


    public boolean onBackPressed() {
        return true;
    }


}
