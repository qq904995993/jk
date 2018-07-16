package baseDao;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class Aes {
	
	private static final String KEY = "1234567890123456";

	//Aes加密    Hex编码
	static public String aesEncrypt(String encryptContant){
		try {
			byte[] raw = KEY.getBytes("utf-8");      			//加密密码16位
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");			//"算法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(encryptContant.getBytes("utf-8"));
			 
			return Hex.encodeHexString(encrypted);    							//此处使用Hex做转码功能，同时能起到2次加密的作用。
		}catch (Exception e) {
	        e.printStackTrace();
	    }
			
		return null;
	}
		
	//Hex解码    Aes解密
	static public String aesDecrypt(String encrypyContent){
		try{
			byte[] raw = KEY.getBytes("utf-8");
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	        byte[] encrypted1 = Hex.decodeHex(encrypyContent.toCharArray());	//先用base64解密
	                
	        byte[] original = cipher.doFinal(encrypted1);
	        String originalString = new String(original,"utf-8");
	        return originalString;
		}catch (Exception e) {
		    e.printStackTrace();
		}
			
		return null;    
	}
	
	
	public static void main(String[] args) {
		String a = "nfrc";
		String enc = aesEncrypt(a);
		System.out.println("加密:" + enc);
		System.out.println("解密:" + aesDecrypt(enc));
	}
	
}
