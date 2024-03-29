package com.imooc.power.service.impl;

import com.imooc.power.entity.PushBean;
import com.imooc.power.service.JiGuangPushService;
import com.imooc.power.service.MyJiGuangPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/8/22 21:29
 */
@Service
public class JiGuangPushServiceImpl implements JiGuangPushService {
    /**
     * 一次推送最大数量 (极光限制1000)
     */
    private static final int max_size = 800;
    @Autowired
    private MyJiGuangPushService jPushService;

    /**
     * 推送全部, 不支持附加信息
     *
     * @return
     */
    @Override
    public boolean pushAll(PushBean pushBean) {
        return jPushService.pushAll(pushBean);
    }

    /**
     * 推送全部ios
     *
     * @return
     */
    @Override
    public boolean pushIos(PushBean pushBean) {
        return jPushService.pushIos(pushBean);
    }

    /**
     * 推送ios 指定id
     *
     * @return
     */
    @Override
    public boolean pushIos(PushBean pushBean, String... registids) {
        // 剔除无效registed
        registids = checkRegistids(registids);
        // 每次推送max_size个
        while (registids.length > max_size) {
            jPushService.pushIos(pushBean, Arrays.copyOfRange(registids, 0, max_size));
            registids = Arrays.copyOfRange(registids, max_size, registids.length);
        }
        return jPushService.pushIos(pushBean, registids);
    }

    /**
     * 推送全部android
     *
     * @return
     */
    @Override
    public boolean pushAndroid(PushBean pushBean) {
        return jPushService.pushAndroid(pushBean);
    }

    /**
     * 推送android 指定id
     *
     * @return
     */
    @Override
    public boolean pushAndroid(PushBean pushBean, String... registids) {
        registids = checkRegistids(registids);
        while (registids.length > max_size) {
            jPushService.pushAndroid(pushBean, Arrays.copyOfRange(registids, 0, max_size));
            registids = Arrays.copyOfRange(registids, max_size, registids.length);
        }
        return jPushService.pushAndroid(pushBean, registids);
    }

    /**
     * 剔除无效registed
     *
     * @param registids
     * @return
     */
    @Override
    public String[] checkRegistids(String[] registids) {
        List<String> regList = new ArrayList<String>(registids.length);
        for (String registid : registids) {
            if (registid != null && !"".equals(registid.trim())) {
                regList.add(registid);
            }
        }
        return regList.toArray(new String[0]);
    }
}
