package com.wujianar.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TokenHelper {
    private String accessKey;
    private String accessSecret;

    public TokenHelper() {
    }

    public TokenHelper(String accessKey, String accessSecret) {
        this.accessKey = accessKey;
        this.accessSecret = accessSecret;
    }

    /**
     * 生成token
     *
     * @param expires (单位为秒)
     * @return
     */
    public String getToken(long expires) {
        String json = String.format("{\"accessKey\":\"%s\",\"expires\":%d}",
                this.accessKey,
                System.currentTimeMillis() + expires * 1000);
        String signature = this.sha256(json + this.accessSecret);
        String raw = signature + json;

        return Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }

    public String sha256(String str) {
        String rs = "";
        try {
            MessageDigest h = MessageDigest.getInstance("sha-256");
            rs = this.byteToHexString(h.digest(str.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            // todo 异常处理
        }

        return rs;
    }

    private String byteToHexString(byte[] data) {
        StringBuilder builder = new StringBuilder();
        for (byte b : data) {
            String hex = Integer.toHexString(b & 0XFF);
            builder.append(hex.length() == 1 ? "0" + hex : hex);
        }
        return builder.toString();
    }
}
