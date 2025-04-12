package org.mj.leapremote;

import android.util.DisplayMetrics;

import com.alibaba.fastjson.JSONObject;
import org.mj.leapremote.model.Device;
import org.mj.leapremote.model.User;
import org.mj.leapremote.util.DataUtil;
import org.mj.leapremote.util.ServerUtil;
import org.mj.leapremote.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Const {
    public static final boolean isDebug = false;
    public static List<String> baseDomains = Arrays.asList("mjczy.top", "mjczy.us.kg", "mjczy.life", "mjczy.xyz", "mjczy.club", "mjczy.info", "mjczy.org", "mjczy.net");
    public static List<String> dnsServers = Arrays.asList("", "223.5.5.5", "2400:3200::1", "114.114.114.114", "2001:dc7:1000::1", "1.1.1.1", "8.8.8.8");
    public static String baseDomain = baseDomains.get(0);
    public static JSONObject serverAddrs;
    public static String serverAddr;
    public static boolean ipGot;
    public static boolean ipv6Support;
    public static final int socketTimeout = 6000;
    public static final int connectTimeout = 6000;
    public static String version;
    public static List<Device> plainDevices = new ArrayList<>();
    public static List<Device> directDevices = new ArrayList<>();
    public static boolean agree;
    public static String neverShowMessageDialog;
    public static String neverShowVersionDialog;
    public static float scale = 2f;
    public static int quality = 5;
    public static long wait;
    public static float maxSpeed = 1024*1024;
    public static boolean speedLimited = false;
    public static int userId;
    public static String connectId;
    public static String connectPin;
    public static boolean controlled;
    public static int controlId;
    public static boolean direct;
    public static boolean remotePlainEnabled;
    public static boolean remoteDirectEnabled;
    public static boolean serverDirect;
    public static long waitTimeout = 7000;
    public static String wsServer;
    public static User user;
    public static boolean autoLogin;
    public static String autoLoginUsername;
    public static String autoLoginPassword;
    public static boolean isExamining;
    public static String deviceId;
    public static int defaultPort = 11451;
    public static String temporaryId;
    public static String temporaryPin;
    public static long networkAvailableWaitTime = 2000;
    public static DisplayMetrics displayMetrics;
    public static String host;
    public static String savedGestures;
    public static String controlSavedGestures;
    public static boolean isControlled;

    public static boolean isSoftwareActivated;

    static {
        serverAddrs = new JSONObject();
        serverAddrs.put("ipv4", "http://home.mjczy.top/lp/");
        serverAddrs.put("ipv6", "http://ipv6.mjczy.top:2095/");
        serverAddrs.put("ipv4ws", "ws://home.mjczy.top/lp/websocket/");
        serverAddrs.put("ipv6ws", "ws://ipv6.mjczy.top:2095/websocket/");
        serverAddrs.put("host", "");
        serverAddr = serverAddrs.getString("ipv4");
        wsServer = serverAddrs.getString("ipv4ws");
        host = serverAddrs.getString("host");
        DataUtil.load();
        DataUtil.save();//In order to first save generated DEVICE ID
        getIp();
    }

    public static void getIp() {
        if(isDebug) {
            serverAddrs = new JSONObject();
            //serverInfo.put("ipv4", "http://192.168.1.99:2095/");
            //serverInfo.put("ipv6", "http://192.168.1.99:2095/");
            //serverInfo.put("ipv4", "http://home.mjczy.top/leapremote/");
            //serverInfo.put("ipv6", "http://home.mjczy.top/leapremote/");
            //serverInfo.put("ipv6-new", "http://ipv6.mjczy.top:2086/");
            //serverInfo.put("ipv4ws", "ws://192.168.1.99:2095/websocket/");
            //serverInfo.put("ipv6ws", "ws://192.168.1.99:2095/websocket/");
            //serverInfo.put("ipv4ws", "ws://home.mjczy.top/leapremote/websocket/");
            //serverInfo.put("ipv6ws", "ws://home.mjczy.top/leapremote/websocket/");
            //serverInfo.put("ipv6Url-new", "ws://ipv6.mjczy.top:2086/websocket/");

            serverAddrs.put("ipv4", "http://home.mjczy.top/lp/");
            serverAddrs.put("ipv6", "http://home.mjczy.top/lp/");
            serverAddrs.put("ipv4ws", "ws://home.mjczy.top/lp/websocket/");
            serverAddrs.put("ipv6ws", "ws://home.mjczy.top/lp/websocket/");

            //serverInfo.put("ipv4", "http://192.168.1.99:2095/");
            //serverInfo.put("ipv6", "http://192.168.1.99:2095/");
            //serverInfo.put("ipv4ws", "ws://192.168.1.99:2095/websocket/");
            //serverInfo.put("ipv6ws", "ws://192.168.1.99:2095/websocket/");
            serverAddrs.put("host", "");
            serverAddr = serverAddrs.getString("ipv4");
            wsServer = serverAddrs.getString("ipv4ws");
            host = serverAddrs.getString("host");
            System.out.println(serverAddrs);
            System.out.println(!Const.ipv6Support && Utils.stringIsEmpty(host));
            System.out.println("HOST: "+host);
            ipGot = true;
            return;
        }
        new Thread(() -> {
            ipGot = false;
            try {
                serverAddrs = ServerUtil.getAddress("lp");
                serverAddr = ipv6Support ? serverAddrs.getString("ipv6") : serverAddrs.getString("ipv4");
                wsServer = ipv6Support ? serverAddrs.getString("ipv6ws") : serverAddrs.getString("ipv4ws");
                host = serverAddrs.getString("host");
                System.out.println(serverAddrs);
                ipGot = true;
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
            getDomainAndIp();
        }).start();
    }

    public static void getDomainAndIp() {
        new Thread(() -> {
            ipGot = false;
            while (true) {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (String domain : baseDomains) {
                    baseDomain = domain;
                    new Thread(() -> ipv6Support = ServerUtil.ipv6Test()).start();
                    try {
                        serverAddrs = ServerUtil.getAddress("lp");
                        ipv6Support = ServerUtil.ipv6Test();
                        serverAddr = ipv6Support ? serverAddrs.getString("ipv6") : serverAddrs.getString("ipv4");
                        wsServer = ipv6Support ? serverAddrs.getString("ipv6ws") : serverAddrs.getString("ipv4ws");
                        host = serverAddrs.getString("host");
                        ipGot = true;
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
