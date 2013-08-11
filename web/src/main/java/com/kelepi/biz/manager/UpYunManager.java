package com.kelepi.biz.manager;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.annotation.PostConstruct;

import com.kelepi.common.upyun.UpYun;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import sun.security.provider.MD5;



/**
 * @author Administrator
 *
 */
@Component("upYunManager")
public class UpYunManager { 

	private UpYun spaceKelepi;
	
	@PostConstruct
	public void init() {
		String spaceName = "kelepi";
		String userName = "kelepi";
		String password = "l19860224";

        spaceKelepi = new UpYun(spaceName, userName, password);
	}
	
	public void upload(String localPath, String remotePath) {
		try {
			File file = new File(localPath);
            spaceKelepi.writeFile(remotePath, file, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//UpYunManager upYunManager = new UpYunManager(); 
		//upYunManager.init();
		
		//upYunManager.upload("E:\\图片\\美女\\082.jpg", "/statics/abc.jpg");
/*		for (int i = 0 ; i < 100; i++) {
			Random random = new Random();
			int ran = random.nextInt(5);
			
			System.out.println(ran);
			
		}*/
		
		long t = System.currentTimeMillis();
		//System.out.println(toMD5("zhangbijin"));
		System.out.println(DigestUtils.md5Hex("zhangbijin"));
		//System.out.println(DigestUtils.sha1Hex("zhangbijin"));
		long e = System.currentTimeMillis();
		System.out.println(e-t);
	}
	
	public static String toMD5(String str){  
	    try {  
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        md.update(str.getBytes());  
	        byte[]byteDigest = md.digest();  
	        int i;  
	        StringBuffer buf = new StringBuffer("");  
	        for (int offset = 0; offset < byteDigest.length; offset++) {  
	            i = byteDigest[offset];  
	            if (i < 0)  
	                i += 256;  
	            if (i < 16)  
	                buf.append("0");  
	            buf.append(Integer.toHexString(i));  
	        }  
	        //32位加密  
	        return buf.toString();  
	        // 16位的加密  
	        //return buf.toString().substring(8, 24);   
	    } catch (NoSuchAlgorithmException e) {  
	        e.printStackTrace();  
	        return null;  
	    }  
	      
	}  
}
