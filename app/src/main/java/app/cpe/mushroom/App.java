package app.cpe.mushroom;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.cpe.mushroom.data.db.DatabaseHelper;
import app.cpe.mushroom.data.db.Db;

/**
 * Created by DEV on 21/9/2560.
 */

public class App extends Application implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        initDB();
    }

    private void initDB() {
        Db.getInstance().init(new DatabaseHelper(this));
        Db.getInstance().openDbHelper();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Db.getInstance().closeDbHelper();
    }
}
