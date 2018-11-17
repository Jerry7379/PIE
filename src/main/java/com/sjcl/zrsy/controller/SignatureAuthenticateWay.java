package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.bigchaindb.KeyPairService;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class SignatureAuthenticateWay implements Authenticator.AuthenticateWay {
    public static final String HEADER_AUTH_PUBKEY = "auth_pubkey";
    public static final String HEADER_AUTH_SIGNATURE = "auth_signature";

    private Signature signature;

    public SignatureAuthenticateWay() {
        try {
            signature = Signature.getInstance("");
        } catch (NoSuchAlgorithmException e) {

        }
    }

    /**
     *
     * @param request
     * @return
     */
    @Override
    public boolean authenticate(HttpServletRequest request) {
        if (!KeyPairHolder.isLogined()) {
            return false;
        }
        PublicKey publicKey = getPublicKey(request);
        byte[] sign = getSign(request);
        byte[] data = getData(request);
        try {
            signature.initVerify(publicKey);
            signature.update(data);
            //验签
            return signature.verify(sign);
        } catch (Exception e) {
            return false;
        }


    }

    /**
     * 从request中获得公钥
     * @param request
     * @return
     */
    private PublicKey getPublicKey(HttpServletRequest request) {
        String pubKeyStr = request.getHeader(HEADER_AUTH_PUBKEY);
        if (pubKeyStr == null) {
            return null;
        }
        try {
            return KeyPairService.decodePublicKey(pubKeyStr);
        } catch (InvalidKeySpecException e) {
            return null;
        }
    }

    /**
     * 通过request获得签名
     * @param request
     * @return
     */
    private byte[] getSign(HttpServletRequest request) {
        String base64Sign = request.getHeader(HEADER_AUTH_SIGNATURE);
        if (base64Sign == null) {
            return null;
        }
        return Base64.getDecoder().decode(base64Sign);
    }

    /**
     * 通过request获得request
     * @param request
     * @return
     */
    private byte[] getData(HttpServletRequest request) {
        byte[] requestBody = readRequestBody(request);
        return requestBody;
    }

    /**
     *
     * @param request
     * @return
     */
    private static byte[] readRequestBody(HttpServletRequest request) {
        InputStream br = null;

        try {
            br = request.getInputStream();
            return IOUtils.toByteArray(br);
        } catch (IOException e) {
            return new byte[0];
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
