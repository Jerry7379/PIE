package com.sjcl.zrsy.bigchaindb;


import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.EdDSASecurityProvider;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class KeyPairService {
    public static final String PRIKEY_FILE = "keystore_prikey.ks";
    public static final String PUBKEY_FILE = "keystore_pubkey.ks";


    public KeyPairService() {
    }

    public static PublicKey decodePublicKey(String pubKeyStr) throws InvalidKeySpecException {
        byte[] pubEncoded = Base64.getDecoder().decode(pubKeyStr);
        return deserializePubKey(pubEncoded);
    }

    /**
     *通过密钥对和密码转换为文件的形式
     * @param keyPair
     * @param password
     * @return
     */
    public boolean save(KeyPair keyPair, String password,String type) {

        try {
            try(FileOutputStream priKeyOut = new FileOutputStream(getKeypairPath(type,PRIKEY_FILE))) {
                byte[] priKeyCode =  CipherUtil.encrypt(keyPair.getPrivate().getEncoded(), password);
                priKeyOut.write(priKeyCode);
            }

            try (FileOutputStream pubKeyOut = new FileOutputStream(getKeypairPath(type,PUBKEY_FILE))){
                byte[] pubKeyCode = encoded(keyPair.getPublic());
                pubKeyOut.write(pubKeyCode);
            }
            return true;
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查看公私钥是否存在
     * @return
     */
    public boolean isExist(String type) {
        return new File(getKeypairPath(type,PUBKEY_FILE)).exists() && new File(getKeypairPath(type,PRIKEY_FILE)).exists();
    }

    private static byte[] encoded(Key key) {
        return key.getEncoded();
    }

    /**
     *使用密码获得公私钥
     * @param password
     * @return
     */
    public KeyPair get(String password,String type) {

        try {
            Security.addProvider(new EdDSASecurityProvider());

            byte[] priEncoded = getPriEncoded(password,type);
            PrivateKey privateKey = deserializePriKey(priEncoded);

            byte[] pubEncoded = getPubEncoded(getKeypairPath(type,PUBKEY_FILE));
            PublicKey publicKey = deserializePubKey(pubEncoded);

            return new KeyPair(publicKey, privateKey);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // protected for unit test
    protected byte[] getPriEncoded(String password,String type) throws GeneralSecurityException, IOException {
        byte[] priEncryptEncoded = getEncryptPriEncoded(getKeypairPath(type,PRIKEY_FILE));
        return CipherUtil.decrypt(priEncryptEncoded, password);///
    }

    // protected for unit test
    protected byte[] getEncryptPriEncoded(String filePath) throws IOException {
        return readBytes(filePath);
    }

    // protected for unit test
    protected byte[] getPubEncoded(String filePath) throws IOException {
        return readBytes(filePath);
    }

    private static byte[] readBytes(String filename) throws IOException {
        try (FileInputStream in = new FileInputStream(filename)) {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            return bytes;
        }
    }

    private PrivateKey deserializePriKey(byte[] priEncoded) throws InvalidKeySpecException {
        PKCS8EncodedKeySpec encoded = new PKCS8EncodedKeySpec(priEncoded);
        return new EdDSAPrivateKey(encoded);
    }

    private static PublicKey deserializePubKey(byte[] pubEncoded) throws InvalidKeySpecException {
        X509EncodedKeySpec encoded = new X509EncodedKeySpec(pubEncoded);
        return new EdDSAPublicKey(encoded);
    }

    private String getKeypairPath(String type,String filename){
        if(type.equals("养殖场")){
            return "info/farm/"+filename;
        }else if(type.equals("屠宰场")){
            return "info/slaughter/"+filename;
        }else if(type.equals("物流")){
            return "info/logistics/"+filename;
        }else{
            return "info/market/"+filename;
        }
    }
}
