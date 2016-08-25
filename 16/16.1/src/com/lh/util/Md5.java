锘縫ackage com.lh.util;

/**
 * 瀹夊叏鍔犲瘑绫�(MD5)锛�
 * @author LH 
 */
import java.security.*;
import java.security.spec.*;

public class Md5 {
	
	public final static String getMD5(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");//鍒涘缓鍏锋湁鎸囧畾绠楁硶鍚嶇О鐨勬憳瑕�
			md.update(str.getBytes());                    //浣跨敤鎸囧畾鐨勫瓧鑺傛暟缁勬洿鏂版憳瑕�
			byte mdBytes[] = md.digest();                 //杩涜鍝堝笇璁＄畻骞惰繑鍥炰竴涓瓧鑺傛暟缁�
			
			String hash = "";
			for(int i= 0;i<mdBytes.length;i++){           //寰幆瀛楄妭鏁扮粍
				int temp;
				if(mdBytes[i]<0)                          //濡傛灉鏈夊皬浜�0鐨勫瓧鑺�,鍒欒浆鎹负姝ｆ暟
					temp =256+mdBytes[i];
				else
					temp=mdBytes[i];
				if(temp<16)
					hash+= "0";
				hash+=Integer.toString(temp,16);         //灏嗗瓧鑺傝浆鎹负16杩涘埗鍚庯紝杞崲涓哄瓧绗︿覆
			}
			return hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(Md5.getMD5("111").length());
	}
}