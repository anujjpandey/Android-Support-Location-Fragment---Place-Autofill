package com.wildcoder.support.location.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by wildcoder on 26/04/16.
 */
public class GooglePlayServicesUtilities {

    /**
     * @param activity
     * @return true(when google play services are up to date)
     * @descripiton check current status of play services
     */
    public static boolean isGooglePlayServicesAvailable(Activity activity) {
        //SUCCESS, SERVICE_MISSING, SERVICE_VERSION_UPDATE_REQUIRED, SERVICE_DISABLED, SERVICE_INVALID.
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(activity);
        switch (result) {
            case ConnectionResult.SERVICE_MISSING:
                showGooglePlayServiceUpdateDialog(activity, 0, "You need to install google play services to use this app, would you like to install it?");
                return false;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                showGooglePlayServiceUpdateDialog(activity, 0, "You need to update google play services to use this app, would you like to update it?");

                return false;
            case ConnectionResult.SERVICE_DISABLED:
                showGooglePlayServiceUpdateDialog(activity, 1, "You need to enable google play services to use this app, would you like to enable it?");
                return false;
        }
        return true;
    }

    /**
     * @param activity
     * @param caSe
     * @param message
     * @descripiton show alert to inform user about current status of play service
     */
    private static void showGooglePlayServiceUpdateDialog(final Activity activity, final int caSe, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setNegativeButton(activity.getResources().getString(android.R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                activity.finish();
            }
        });
        builder.setPositiveButton(activity.getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (caSe == 0)
                    openInPlayStore(activity, "com.google.android.gms");
                else
                    openAppDetail(activity, "com.google.android.gms");
                activity.finish();
            }
        });
        builder.show();
    }

    /**
     * @param activity
     * @param appPackageName
     * @descripiton Open application in play store
     */
    private static void openInPlayStore(Activity activity, String appPackageName) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException anfe) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    /**
     * @param activity
     * @param packageName
     * @descripiton Open application settings
     */
    private static void openAppDetail(Activity activity, String packageName) {
        try {
            //Open the specific App Info page:
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + packageName));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            //e.printStackTrace();
            //Open the generic Apps page:
            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
            activity.startActivity(intent);
        }
    }
}
