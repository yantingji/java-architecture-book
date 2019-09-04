package com.itedu.main;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainTest {
	
	public static void main(String[ ]args) {
		try {
			String str = "路";
			byte [] gb2312= str.getBytes("GB2312");
			System.out.println(bytesToHexFun1(gb2312));
			String utf8 =URLEncoder.encode(str, "UTF-8").replaceAll("%", " ");
			System.out.println(utf8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	 public static String bytesToHexFun1(byte[] bytes) {
         char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', 
                 '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
         // 一个byte为8位，可用两个十六进制位标识
         char[] buf = new char[bytes.length * 2];
         int a = 0;
         int index = 0;
         for(byte b : bytes) { // 使用除与取余进行转换
             if(b < 0) {
                 a = 256 + b;
             } else {
                 a = b;
             }
             buf[index++] = HEX_CHAR[a / 16];
             buf[index++] = HEX_CHAR[a % 16];
         }
         return new String(buf);
     }

}
