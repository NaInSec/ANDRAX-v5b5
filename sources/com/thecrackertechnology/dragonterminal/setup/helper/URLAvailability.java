package com.thecrackertechnology.dragonterminal.setup.helper;

import android.content.Context;
import com.thecrackertechnology.dragonterminal.utils.NetworkUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class URLAvailability {

    public enum ResultCode {
        URL_OK,
        URL_CONNECTION_FAILED,
        URL_INVALID,
        URL_NO_INTERNET
    }

    public static ResultCode checkUrlAvailability(Context context, String str) {
        if (!NetworkUtils.INSTANCE.isNetworkAvailable(context)) {
            return ResultCode.URL_NO_INTERNET;
        }
        try {
            ((HttpURLConnection) new URL(str).openConnection()).disconnect();
            return ResultCode.URL_OK;
        } catch (MalformedURLException unused) {
            return ResultCode.URL_INVALID;
        } catch (IOException | ClassCastException unused2) {
            return ResultCode.URL_CONNECTION_FAILED;
        }
    }
}
