package water.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class BWaterPreferences {

    private static final String MyPREFERENCES = Constants.LIQOUR_PREFERENCE;
    public static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public static void setUserLoggedIn(Context context, boolean value) {
        getSharedPreference(context).edit().putBoolean(Constants.IS_LOGGED_IN, value).apply();
    }

    public static boolean getUserLoggedIn(Context context) {
        return getSharedPreference(context).getBoolean(Constants.IS_LOGGED_IN, false);
    }
    public static void setFireBaseToken(Context context, String value) {
        getSharedPreference(context).edit().putString(Constants.FIREBASE_TOKEN, value).apply();
    }

    public static String getFireBaseToken(Context context) {
        return getSharedPreference(context).getString(Constants.FIREBASE_TOKEN, "");
    }

    public static void isFirstRun(Context context, boolean value) {
        getSharedPreference(context).edit().putBoolean(Constants.FIRST_RUN, value).apply();
    }

    public static boolean getFirstRun(Context context) {
        return getSharedPreference(context).getBoolean(Constants.FIRST_RUN, false);
    }


    public static void clearPreferences(Context context) {
        getSharedPreference(context).edit().clear().apply();
    }

}
