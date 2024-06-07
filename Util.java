
package crypto;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author new user
 */
public class Util {
    public static String calculateHash(String value,String algorithm) throws NoSuchAlgorithmException{
        
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(value.getBytes());
        return getHex(digest.digest());        
    }
    
    public static byte[] calculateHashByte(byte[] value,String algorithm){
        
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(value);        
            return digest.digest();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
           
        }
        return null;
    }
    
    public static byte[] calculateHash(InputStream is,String algorithm) throws IOException{        
        byte[] byteSig = new byte[is.available()];
        is.read(byteSig);  
        return calculateHashByte(byteSig,algorithm);
    }
               
    public static String getHex(byte[] digest){
        StringBuffer hexString = new StringBuffer();
      
        for (int i = 0;i<digest.length;i++) {
           hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        return hexString.toString();
    }
}
