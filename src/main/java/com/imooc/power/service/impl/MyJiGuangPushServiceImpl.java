package com.imooc.power.service.impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.imooc.power.config.JiGuangConfig;
import com.imooc.power.entity.PushBean;
import com.imooc.power.service.MyJiGuangPushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/8/22 21:28
 */
@Service
@Slf4j
public class MyJiGuangPushServiceImpl implements MyJiGuangPushService {

    @Autowired
    private JiGuangConfig jPushConfig;

    /**
     * 广播 (所有平台，所有设备, 不支持附加信息)
     *
     * @param pushBean 推送内容
     * @return
     */
    @Override
    public boolean pushAll(PushBean pushBean) {
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .setTimeToLive(90)
                        .build())
                .setNotification(Notification.alert(pushBean.getAlert()))
                .build());
    }

    /**
     * ios广播
     *
     * @param pushBean 推送内容
     * @return
     */
    @Override
    public boolean pushIos(PushBean pushBean) {
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setNotification(Notification.ios(pushBean.getAlert(), pushBean.getExtras()))
                .build());
    }

    /**
     * ios通过registid推送 (一次推送最多 1000 个)
     *
     * @param pushBean  推送内容
     * @param registids 推送id
     * @return
     */
    @Override
    public boolean pushIos(PushBean pushBean, String... registids) {
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.registrationId(registids))
                .setNotification(Notification.ios(pushBean.getAlert(), pushBean.getExtras()))
                .build());
    }

    /**
     * android广播
     *
     * @param pushBean 推送内容
     * @return
     */
    @Override
    public boolean pushAndroid(PushBean pushBean) {
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(pushBean.getAlert(), pushBean.getTitle(), pushBean.getExtras()))
                .build());
    }

    /**
     * android通过registid推送 (一次推送最多 1000 个)
     *
     * @param pushBean  推送内容
     * @param registids 推送id
     * @return
     */
    @Override
    public boolean pushAndroid(PushBean pushBean, String... registids) {
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(registids))
                .setNotification(Notification.android(pushBean.getAlert(), pushBean.getTitle(), pushBean.getExtras()))
                .build());
    }

    /**
     * 调用api推送
     *
     * @param pushPayload 推送实体
     * @return
     */
    @Override
    public boolean sendPush(PushPayload pushPayload) {
        log.info("发送极光推送请求: {}", pushPayload);
        PushResult result = null;
        try {
            result = jPushConfig.getJPushClient().sendPush(pushPayload);
        } catch (APIConnectionException e) {
            log.error("极光推送连接异常: ", e);
        } catch (APIRequestException e) {
            log.error("极光推送请求异常: ", e);
        }
        if (result != null && result.isResultOK()) {
            log.info("极光推送请求成功: {}", result);
            return true;
        } else {
            log.info("极光推送请求失败: {}", result);
            return false;
        }
    }

}
