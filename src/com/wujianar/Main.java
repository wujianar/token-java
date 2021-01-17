package com.wujianar;

import com.wujianar.helper.TokenHelper;

/**
 * token生成示例
 * <p>
 * 开发者中心: https://portal.wujianar.com
 * </p>
 */
public class Main {

    public static void main(String[] args) {
        // 基本信息
        final String accessKey = "6e59b44afb904ffa9d628e69b18c14a0";
        final String accessSecret = "b60e17e39dca45bf9e2d5b393ee823c45b21218456494cf19cd12fe4fd036e5e";

        TokenHelper tokenHelper = new TokenHelper(accessKey, accessSecret);

        // 设置token有效期为3600秒,在有效期内无需重新生成
        String token = tokenHelper.getToken(3600);

        System.out.println(token);
    }
}