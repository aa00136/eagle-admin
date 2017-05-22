package com.eagle.utils;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CouponCodeGenerateUtil {
	private static int lastSecond;
	private static AtomicInteger counter = new AtomicInteger(0);
	private static Random rd = new Random();
	private static final Object locker = new Object();

	public static String generateCouponCode() {
		int currentSecond = 0;
		synchronized (locker) {
			currentSecond = (int) (System.currentTimeMillis() / 1000);
			if (currentSecond != lastSecond) {
				lastSecond = currentSecond;
				counter.set(0);
			}
		}
		int currentCounter = counter.getAndAdd(1);

		while (currentCounter > 9999) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			return generateCouponCode();
		}
		long onlyId = rd.nextInt(100) * 100000000000000000L + currentCounter
				* 10000000000000L + currentSecond * 1000 + rd.nextInt(1000);
		return bytesToHexString(longToBytes(onlyId));
	}

	private static byte[] longToBytes(long value) {
		int byteSize = 8;
		byte[] bytes = new byte[byteSize];
		// for (int i = 0; i < byteSize; i++) {
		// bytes[i] = (byte) ((value >> (byteSize - i - 1) * 8) & 0xFF);
		// }
		bytes[0] = (byte) ((value >> (2) * 8) & 0xFF);
		bytes[1] = (byte) ((value >> (6) * 8) & 0xFF);
		bytes[2] = (byte) ((value >> (3) * 8) & 0xFF);
		bytes[3] = (byte) ((value >> (5) * 8) & 0xFF);
		bytes[4] = (byte) ((value >> (7) * 8) & 0xFF);
		bytes[5] = (byte) ((value >> (1) * 8) & 0xFF);
		bytes[6] = (byte) ((value >> (4) * 8) & 0xFF);
		bytes[7] = (byte) ((value >> (0) * 8) & 0xFF);
		return bytes;
	}

	private static String bytesToHexString(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (bytes == null || bytes.length <= 0) {
			return null;
		}
		for (int i = 0; i < bytes.length; i++) {
			// if (i > 0) {
			// stringBuilder.append(" ");
			// }
			int v = bytes[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().toUpperCase();
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			System.out.println(generateCouponCode());
		}
		long end = System.currentTimeMillis();
		System.out.println("完成:" + (end - start));
	}
}
