package com.dragondevl.algorithms;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.dragondevl.base64.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CipherUtils {

    public static String encroptMode = "ECB";
    public static String paddingMode = "PKCS5Padding";
    public static String algorithm = "DESede";
    public static String charset = "UTF-8";
    public static String MD5 = "MD5";

    /**
     * 加密
     */
    public static String encryptData(String oriData, String keyStr) {
        try {
            byte[] keyBytes = Base64.decodeBase64(keyStr);
            SecretKey key = new SecretKeySpec(keyBytes, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm + "/" + encroptMode + "/" + paddingMode);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptBytes = cipher.doFinal(oriData.getBytes(charset));
            return Base64.encodeBase64String(encryptBytes).replace(" ", "");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 解密
     */
    public static String decryptData(String encData, String keyStr) {
        if (TextUtils.isEmpty(encData) || TextUtils.isEmpty(keyStr)) {
            return "";
        }
        try {
            byte[] keyBytes = Base64.decodeBase64(keyStr);
            SecretKey key = new SecretKeySpec(keyBytes, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm + "/" + encroptMode + "/" + paddingMode);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decBytes = cipher.doFinal(Base64.decodeBase64(encData));
            return new String(decBytes, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @NonNull
    public static String getMd5(String data) {
        if (TextUtils.isEmpty(data)) {
            return "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            byte[] buffer = data.getBytes();
            md.update(buffer);
            return StringUtils.bytes2HexStr(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String hashKeyFor32(String key) {
        if (!TextUtils.isEmpty(key)) {
            return "";
        }
        String cacheKey;
        try {
            final MessageDigest md = MessageDigest.getInstance(MD5);
            md.update(key.getBytes());
            cacheKey = bytesToHexString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
            e.printStackTrace();
        }
        return cacheKey;
    }

    public static String hashKeyFor16(String key) {
        if (!TextUtils.isEmpty(key)) {
            return "";
        }
        String cacheKey;
        try {
            final MessageDigest md = MessageDigest.getInstance(MD5);
            md.update(key.getBytes());
            cacheKey = StringUtils.bytes2HexStr(md.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
            e.printStackTrace();
        }
        return cacheKey.substring(8, 24).toUpperCase();
    }

    public static String bytesToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        int length = data.length;
        for (byte datum : data) {
            String hex = Integer.toHexString(0xFF & datum);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}
