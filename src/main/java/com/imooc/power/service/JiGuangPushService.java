package com.imooc.power.service;

import com.imooc.power.entity.PushBean;

/**
 * 封装业务功能相关
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/8/22 21:27
 */
public interface JiGuangPushService {

    boolean pushAll(PushBean pushBean);

    boolean pushIos(PushBean pushBean);

    boolean pushIos(PushBean pushBean, String... registids);

    boolean pushAndroid(PushBean pushBean);

    boolean pushAndroid(PushBean pushBean, String... registids);

    String[] checkRegistids(String[] registids);
}
