package com.example.administrator.yibit.util;

import android.util.Log;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class ECCUtil {
    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        for (String s : Security.getAlgorithms("KeyPairGenerator")) {
            Log.i("RxAndroid", s);
        }
    }

    //生成秘钥对
    public static KeyPair getKeyPair() throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
        keyPairGenerator.initialize(256, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    //获取公钥(Base64编码)
    public static String getPublicKey(KeyPair keyPair) {
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return Base58.encode(bytes);
//        return new String(bytes);
//        return AESUtil.byte2Base64(bytes);
    }


    //获取私钥(Base64编码)
    public static String getPrivateKey(KeyPair keyPair) {
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return Base58.encode(bytes);
//        return new String(bytes);
//        return AESUtil.byte2Base64(bytes);
    }

    //将Base64编码后的公钥转换成PublicKey对象
    public static ECPublicKey string2PublicKey(String pubStr) throws Exception {
        byte[] keyBytes = AESUtil.base642Byte(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
        ECPublicKey publicKey = (ECPublicKey) keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    //将Base64编码后的私钥转换成PrivateKey对象
    public static ECPrivateKey string2PrivateKey(String priStr) throws Exception {
        byte[] keyBytes = AESUtil.base642Byte(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
        ECPrivateKey privateKey = (ECPrivateKey) keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    //公钥加密
    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("ECIES", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    //私钥解密
    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("ECIES", "BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }
}

