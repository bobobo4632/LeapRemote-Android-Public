package org.mj.leapremote.util;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.mj.leapremote.Const;
import org.mj.leapremote.cs.direct.NettyClientDirect;

public class SendCommandHelper {
    private Context context;
    public SendCommandHelper(Context context) {
        this.context = context;
    }
    public void doSendAction(JSONArray points) {
        //System.out.println(points);
        //points = Utils.simplifyPoints(points);
        //System.out.println(points);
        if(Const.direct) {
            if (NettyClientDirect.INSTANCE == null)
                return;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "action");
            jsonObject.put("points", points);
            NettyClientDirect.INSTANCE.sendMessage(jsonObject.toJSONString());
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "action");
        jsonObject.put("points", points);
        JSONObject request = new JSONObject();
        request.put("type", "send");
        request.put("controlled", false);
        request.put("controlId", Const.controlId);
        request.put("data", jsonObject);
        ClientHelper.sendMessage(context, request.toJSONString());
    }

    public void doSendGestures(JSONArray gestures) {
        if(Const.direct) {
            if (NettyClientDirect.INSTANCE == null)
                return;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "gestures");
            jsonObject.put("gestures", gestures);
            NettyClientDirect.INSTANCE.sendMessage(jsonObject.toJSONString());
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "gestures");
        jsonObject.put("gestures", gestures);
        JSONObject request = new JSONObject();
        request.put("type", "send");
        request.put("controlled", false);
        request.put("controlId", Const.controlId);
        request.put("data", jsonObject);
        ClientHelper.sendMessage(context, request.toJSONString());
    }

    public void doSendKey(String key) {
        if(Const.direct) {
            if (NettyClientDirect.INSTANCE == null)
                return;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "key");
            jsonObject.put("key", key);
            NettyClientDirect.INSTANCE.sendMessage(jsonObject.toJSONString());
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "key");
        jsonObject.put("key", key);
        JSONObject request = new JSONObject();
        request.put("type", "send");
        request.put("controlled", false);
        request.put("controlId", Const.controlId);
        request.put("data", jsonObject);
        ClientHelper.sendMessage(context, request.toJSONString());
    }

    public void doSendButton(int button) {
        if(Const.direct) {
            if (NettyClientDirect.INSTANCE == null)
                return;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "button");
            jsonObject.put("button", button);
            NettyClientDirect.INSTANCE.sendMessage(jsonObject.toJSONString());
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "button");
        jsonObject.put("button", button);
        JSONObject request = new JSONObject();
        request.put("type", "send");
        request.put("controlled", false);
        request.put("controlId", Const.controlId);
        request.put("data", jsonObject);
        ClientHelper.sendMessage(context, request.toJSONString());
    }

    public void doSendQuality(int progress, int quality) {
        if(Const.direct) {
            if (NettyClientDirect.INSTANCE == null)
                return;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "quality");
            jsonObject.put("scale", progress);
            jsonObject.put("quality", 100 - quality);
            NettyClientDirect.INSTANCE.sendMessage(jsonObject.toJSONString());
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "quality");
        jsonObject.put("scale", progress);
        jsonObject.put("quality", 100 - quality);
        JSONObject request = new JSONObject();
        request.put("type", "send");
        request.put("controlled", false);
        request.put("controlId", Const.controlId);
        request.put("data", jsonObject);
        ClientHelper.sendMessage(context, request.toJSONString());
    }

    public void doSendRestartMedia() {
        if(Const.direct) {
            if (NettyClientDirect.INSTANCE == null)
                return;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "restartMedia");
            //jsonObject.put("scale", progress);
            //jsonObject.put("quality", 100 - quality);
            NettyClientDirect.INSTANCE.sendMessage(jsonObject.toJSONString());
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "restartMedia");
        //jsonObject.put("scale", progress);
        //jsonObject.put("quality", 100 - quality);
        JSONObject request = new JSONObject();
        request.put("type", "send");
        request.put("controlled", false);
        request.put("controlId", Const.controlId);
        request.put("data", jsonObject);
        ClientHelper.sendMessage(context, request.toJSONString());
    }

    public void doSendResolution(int progress) {
        if(Const.direct) {
            if (NettyClientDirect.INSTANCE == null)
                return;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "resolution");
            jsonObject.put("resolution", progress);
            //jsonObject.put("quality", 100 - quality);
            NettyClientDirect.INSTANCE.sendMessage(jsonObject.toJSONString());
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "resolution");
        jsonObject.put("resolution", progress);
        //jsonObject.put("scale", progress);
        //jsonObject.put("quality", 100 - quality);
        JSONObject request = new JSONObject();
        request.put("type", "send");
        request.put("controlled", false);
        request.put("controlId", Const.controlId);
        request.put("data", jsonObject);
        ClientHelper.sendMessage(context, request.toJSONString());
    }

    public void doSendFirstReceived() {
        if(Const.direct) {
            if (NettyClientDirect.INSTANCE == null)
                return;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "firstReceived");
            NettyClientDirect.INSTANCE.sendMessage(jsonObject.toJSONString());
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "firstReceived");
        JSONObject request = new JSONObject();
        request.put("type", "send");
        request.put("controlled", false);
        request.put("controlId", Const.controlId);
        request.put("data", jsonObject);
        ClientHelper.sendMessage(context, request.toJSONString());
    }
}
