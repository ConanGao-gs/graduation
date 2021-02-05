package com.example.demo;

import com.example.demo.utils.RsaUtils;
import org.junit.jupiter.api.Test;

public class RSATest {
    private String privateFilePath = "C:\\Users\\gaoshuang\\Desktop\\auth_key\\id_key_rsa";
    private String publicFilePath = "C:\\Users\\gaoshuang\\Desktop\\auth_key\\id_key_rsa.pub";

    @Test
    public void generateKey() throws Exception {
        RsaUtils.generateKey(publicFilePath, privateFilePath, "RobodLee", 2048);
    }

    @Test
    public void getPublicKey() throws Exception {
        System.out.println(RsaUtils.getPublicKey(publicFilePath));
    }

    @Test
    public void getPrivateKey() throws Exception {
        System.out.println(RsaUtils.getPrivateKey(privateFilePath));
    }

}
