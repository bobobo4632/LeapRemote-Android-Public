package org.mj.leapremote;

import org.mask.mediaprojectionlibrary.BaseFrameApplication;
import org.mj.leapremote.ui.fragments.QuickConnectFragment;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 应用基类Application(继承于框架基类Application)
 * Created by lishilin on 2016/11/29.
 */
public class BaseApplication extends BaseFrameApplication {


    public static void ignoreSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("TLS");
            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ignoreSSLHandshake();
    }

    @Override
    protected Class getCrashLauncherActivity() {
        return QuickConnectFragment.class;
    }

    @Override
    protected void uncaughtException(Thread thread, Throwable ex) {
        super.uncaughtException(thread, ex);
    }

    @Override
    protected void onInitData() {
        super.onInitData();
        LogUtil.i("BaseApplication onInitData");
    }

    @Override
    protected void onInitDataThread() {
        super.onInitDataThread();
        LogUtil.i("BaseApplication onInitDataThread");
    }
}
