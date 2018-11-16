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
    public boolean save(KeyPair keyPair, String password,String type,String id) {

        try {
            try(FileOutputStream priKeyOut = new FileOutputStream(getKeypairPath(id,type,PRIKEY_FILE))) {
                byte[] priKeyCode =  CipherUtil.encrypt(keyPair.getPrivate().getEncoded(), password);
                priKeyOut.write(priKeyCode);
            }

            try (FileOutputStream pubKeyOut = new FileOutputStream(getKeypairPath(id,type,PUBKEY_FILE))){
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
    public boolean isExist(String type,String id ) {
        return new File(getKeypairPath(id,type,PUBKEY_FILE)).exists() && new File(getKeypairPath(id,type,PRIKEY_FILE)).exists();
    }

    private static byte[] encoded(Key key) {
        return key.getEncoded();
    }

    /**
     *使用密码获得公私钥
     * @param password
     * @return
     */
    public KeyPair get(String password,String type,String id ) {

        try {
            Security.addProvider(new EdDSASecurityProvider());
            return new KeyPair(getPublicKey(id,type), getPrivateKey(password,type,id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从相应文件读取公钥
     * @param type
     * @return
     * @throws IOException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String type,String id)  {
        byte[] pubEncoded = new byte[0];
        try {
            pubEncoded = getPubEncoded(getKeypairPath(id,type, PUBKEY_FILE));
            return deserializePubKey(pubEncoded);
        }catch (Exception e){
            return null;
        }

    }

    /**
     * 从相应文件读取私钥
     * @param type
     * @param password
     * @return
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static PrivateKey getPrivateKey(String type,String password,String id)  {
        try {
            byte[] priEncoded = getPriEncoded(id ,password, type);
            return deserializePriKey(priEncoded);
        }catch (Exception e){
            return null;
        }

    }
    // protected for unit test
    protected static byte[] getPriEncoded(String password, String type,String id) throws GeneralSecurityException, IOException {
        byte[] priEncryptEncoded = getEncryptPriEncoded(getKeypairPath(id,type,PRIKEY_FILE));
        return CipherUtil.decrypt(priEncryptEncoded, password);///
    }

    // protected for unit test
    protected static byte[] getEncryptPriEncoded(String filePath) throws IOException {
        return readBytes(filePath);
    }

    // protected for unit test
    protected static byte[] getPubEncoded(String filePath) throws IOException {
        return readBytes(filePath);
    }

    private static byte[] readBytes(String filename) throws IOException {
        try (FileInputStream in = new FileInputStream(filename)) {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            return bytes;
        }
    }

    private static PrivateKey deserializePriKey(byte[] priEncoded) throws InvalidKeySpecException {
        PKCS8EncodedKeySpec encoded = new PKCS8EncodedKeySpec(priEncoded);
        return new EdDSAPrivateKey(encoded);
    }

    private static PublicKey deserializePubKey(byte[] pubEncoded) throws InvalidKeySpecException {
        X509EncodedKeySpec encoded = new X509EncodedKeySpec(pubEncoded);
        return new EdDSAPublicKey(encoded);
    }

    private static String getKeypairPath(String id,String type,String filename){
        if(type.equals("养殖场")){
            return "info/farm/"+id+"/"+filename;
        }else if(type.equals("屠宰场")){
            return "info/slaughter/"+id+"/"+filename;
        }else if(type.equals("物流")){
            return "info/logistics/"+id+"/"+filename;
        }else{
            return "info/market/"+id+"/"+filename;
        }
    }
}
