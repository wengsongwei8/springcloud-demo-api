package com.weng.demo.common.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author wengzhonghui
 * @date 2019/9/15 8:40
 */
public class AppConfig {

    // 默认
    public static String DEFAULT_PASSWORD = "";
    @Value("${app.default-password}")
    public void getDefaultPassword(final String defaultPassword){
        DEFAULT_PASSWORD = defaultPassword;
    }

}
