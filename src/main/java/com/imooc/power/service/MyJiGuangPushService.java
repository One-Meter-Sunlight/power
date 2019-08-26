package com.imooc.power.service;

import cn.jpush.api.push.model.PushPayload;
import com.imooc.power.entity.PushBean;

/**
 * 封装第三方api相关
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/8/22 21:26
 */
public interface MyJiGuangPushService {

    boolean pushAll(PushBean pushBean);

    boolean pushIos(PushBean pushBean);

    boolean pushIos(PushBean pushBean, String... registids);

    boolean pushAndroid(PushBean pushBean);

    boolean pushAndroid(PushBean pushBean, String... registids);

    boolean sendPush(PushPayload pushPayload);

}
