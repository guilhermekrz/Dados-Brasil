package com.brokoli.dadosbrasil;

import android.app.Application;

import com.brokoli.dadosbrasil.persistence.Migration;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by guilhermekrzisch on 1/28/16.
 */
// Future releases:
    // 1 - Set location and sort by closer locations
    // 2 - Display more information, with a better UX
    // 3 - Add link to other apps in PlayStore
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setupCrashlytics();
        setupTimber();
        setupRealm();
    }

    private void setupCrashlytics() {
        if(!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }
    }

    private void setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void setupRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("dadosbrasil.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .migration(new Migration())
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
