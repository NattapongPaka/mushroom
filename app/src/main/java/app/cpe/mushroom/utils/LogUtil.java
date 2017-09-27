package app.cpe.mushroom.utils;



import com.orhanobut.logger.Logger;

import app.cpe.mushroom.BuildConfig;

/**
 * Created by DEV on 27/9/2560.
 */

public class LogUtil {

    public static void D(String condition, Object... objects) {
        if (BuildConfig.DEBUG) {
            Logger.d(condition, objects);
        }
    }

    public static void E(String condition, Object... objects) {
        if (BuildConfig.DEBUG) {
            Logger.e(condition, objects);
        }
    }
}
