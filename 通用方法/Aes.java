package baseDao;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class Aes {
	
	private static final String KEY = "1234567890123456";

	//Aes����    Hex����
	static public String aesEncrypt(String encryptContant){
		try {
			byte[] raw = KEY.getBytes("utf-8");      			//��������16λ
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");			//"�㷨/ģʽ/���뷽ʽ"
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(encryptContant.getBytes("utf-8"));
			 
			return Hex.encodeHexString(encrypted);    							//�˴�ʹ��Hex��ת�빦�ܣ�ͬʱ����2�μ��ܵ����á�
		}catch (Exception e) {
	        e.printStackTrace();
	    }
			
		return null;
	}
		
	//Hex����    Aes����
	static public String aesDecrypt(String encrypyContent){
		try{
			byte[] raw = KEY.getBytes("utf-8");
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	        byte[] encrypted1 = Hex.decodeHex(encrypyContent.toCharArray());	//����base64����
	                
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
		System.out.println("����:" + enc);
		System.out.println("����:" + aesDecrypt(enc));
	}
	
}
