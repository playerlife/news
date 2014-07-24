package net.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by joejoe on 2014/7/21.
 */
public class NetworkConnect {

    /**
     * 检测网络是否可用
     *
     * @param ctx
     * @return
     */



    public static boolean isNetworkAvaible(Context ctx) {

        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo networkInfo : info) {
                    if (networkInfo.isConnected() && networkInfo.isAvailable()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 手动检测网络
     *
     * @param context
     */
    public static void autoCheckNetwork(Context context) {

        if (!isNetworkAvaible(context)) {
            Toast.makeText(context, "您的网络已断开", Toast.LENGTH_LONG).show();
        }
    }



}
