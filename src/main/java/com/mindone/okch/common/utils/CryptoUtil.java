package com.mindone.okch.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;

public class CryptoUtil {
	/**
	 * 비밀번호를 SHA256 방식으로 암호화한다.
	 * 
	 * @param password 비밀번호
	 * @return 비밀번호 문자열
	 */
	public static String encryptSHA256(String password) {

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

			// 초기화
			messageDigest.reset();
			// 비밀번호 입력

			byte[] stringBytes = password.getBytes();

			int stringBytesLength = stringBytes.length;

			byte[] dataBytes = new byte[1024];
			// 암호화가능한 해시코드 생성
			for (int i = 0; i < stringBytesLength; i++) {
				dataBytes[i] = stringBytes[i];
			}

			messageDigest.update(dataBytes, 0, stringBytesLength);

			byte[] encrypted = messageDigest.digest();

			// base64 인코딩
			byte[] base64Encoded = Base64.encodeBase64(encrypted);

			// 결과
			String result = new String(base64Encoded, CharEncoding.UTF_8);

			return result;

		} catch (NoSuchAlgorithmException e) {
			return e.getLocalizedMessage();
		} catch (Exception e) {
			return e.getLocalizedMessage();
		}
	}
}
