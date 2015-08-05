package com.shop13;

import android.app.Application;

import com.google.android.gms.analytics.Tracker;


public class GoogleAnal extends Application {
    /**
     * The Analytics singleton. The field is set in onCreate method override when the application
     * class is initially created.
     */
    private static com.google.android.gms.analytics.GoogleAnalytics analytics;

    /**
     * The default app tracker. The field is from onCreate callback when the application is
     * initially created.
     */
    private static Tracker tracker;

    /**
     * Access to the global Analytics singleton. If this method returns null you forgot to either
     * set android:name="&lt;this.class.name&gt;" attribute on your application element in
     * AndroidManifest.xml or you are not setting this.analytics field in onCreate method override.
     */
    public static com.google.android.gms.analytics.GoogleAnalytics analytics() {
        return analytics;
    }

    /**
     * The default app tracker. If this method returns null you forgot to either set
     * android:name="&lt;this.class.name&gt;" attribute on your application element in
     * AndroidManifest.xml or you are not setting this.tracker field in onCreate method override.
     */
    public static Tracker tracker() {
        return tracker;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        analytics = com.google.android.gms.analytics.GoogleAnalytics.getInstance(this);
        //analytics.setLocalDispatchPeriod(1800);

        //Tracker id
        tracker = analytics.newTracker("UA-65982739-1");

        // Provide unhandled exceptions reports. Do that first after creating the tracker
        tracker.enableExceptionReporting(true);

        // Enable Remarketing, Demographics & Interests reports
        // https://developers.google.com/analytics/devguides/collection/android/display-features
        tracker.enableAdvertisingIdCollection(true);

        // Enable automatic activity tracking for your app
        tracker.enableAutoActivityTracking(true);
    }
}
