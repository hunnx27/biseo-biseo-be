package com.onz.modules.auth.application.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class PasswordTest {

	public static void main(String[] args) {
		
		try {
			
			String rs = MysqlAESUtil.encrypto("key", "hello");
			System.out.println(rs);
			
			String rs2 = MysqlSHA2Util.getSHA256(rs);
			System.out.println(rs2);
			
			String rs3 = MysqlSHA2Util.getSHA512(rs);
			System.out.println(rs3);
			
			
			byte[] encode = MysqlAESUtil.encryptoByte("ONZ!@#", "hello");
			String rs4 = MysqlSHA2Util.getSHA512(encode);
			System.out.println(rs4);
			//563e20e114427e68e234d19220f054bedf9ca287d6cb8b1237b2f3b7cd63532d7ea866a7b456470fab964a31c503e3a7099d424a2dddf10f54183a2835163829
			//563e20e114427e68e234d19220f054bedf9ca287d6cb8b1237b2f3b7cd63532d7ea866a7b456470fab964a31c503e3a7099d424a2dddf10f54183a2835163829
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
