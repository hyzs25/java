package com.qihoo.FindRepeat;

import java.io.File;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;

public class MainProcess {

	public static String MD5(byte[] b){
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		
		try{
			byte[] strTmp = b;
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTmp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j*2];
			int k = 0;
			for(int i=0;i<j;i++){
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0>>>4&0xf];
				str[k++] = hexDigits[byte0&0xf];
			}
			return new String(str);
		}catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		String spath = "D:\\compare\\new\\res\\drawable-hdpi\\";
		File file = new File(spath);
		String[] fl = file.list();
		String[] stemp = new String[fl.length];
		
		for(String f : fl){
			File ffile = new File(spath + f);
			byte fb[] = new byte[(int) ffile.length()];
			for(int i=0;i < stemp.length; i++){
				System.out.println(stemp[i]);
				File sfile = new File(spath +stemp[i]);
				byte sb[] = new byte[(int) sfile.length()];
				if (fb.toString() == sb.toString()) {
					System.out.println(f);
				}
 			}
		
		}
//		byte b[] = new byte[(int) file.length()];
//		byte b1[] = new byte[(int) file1.length()];
//		System.out.println(MD5(b));
//		System.out.println(MD5(b1));
	}
}
