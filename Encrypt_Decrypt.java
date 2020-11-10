import java.io.UnsupportedEncodingException;
import java.security.*;
import javax.crypto.*;

public class Encrypt_Decrypt extends atm{
    String[] encrypt = new String[5];
    String[] decrypt = new String[5];
    public byte[] input = new byte[0];

    void crypt(int i) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Signature sign = Signature.getInstance("SHA256withRSA");        //Creating a Signature object

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");      //Creating KeyPair generator object

        keyPairGen.initialize(2048);        //Initializing the key pair generator

        KeyPair pair = keyPairGen.generateKeyPair();        //Generate the pair of keys

        PublicKey publicKey = pair.getPublic();     //Getting the public key from the key pair

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");         //Creating a Cipher object

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);        //Initializing a Cipher object

        this.input = pins[i].getBytes();

        cipher.update(this.input);

        byte[] cipherText = cipher.doFinal();                   //encrypting the data

        cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());        //Initializing the same cipher for decryption

        byte[] decipheredText = cipher.doFinal(cipherText);         //Decrypting the text

        this.encrypt[i] = new String(cipherText, "UTF8");

        this.decrypt[i] = new String(decipheredText);
    }
    int getDecryptPin(int i) throws NoSuchPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        crypt(i);
        return Integer.parseInt(decrypt[i]);
    }
    void changePin(int i, int pin){
        pins[i] = Integer.toString(pin);
    }
}
