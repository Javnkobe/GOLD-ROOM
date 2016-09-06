package com.example.administrator.housekeeperapplication.util;

import android.util.Log;

/**
 * ��־������
 * 
 * @author yuanc
 * 
 */
public class LogUtil {

	public static boolean isOpenDebug = true; // �Ƿ���debug logcat

	/**
	 * �Ƽ�ʹ�á��õ�����������Ϊtagֵ��������־���
	 * 
	 * @param obj
	 *            ��һ�㴫�� this
	 * @param msg
	 *            ��־��Ϣ
	 * 
	 * @see {@link #isOpenDebug}
	 */
	public static void d(Object obj, String msg) {
		if (isOpenDebug) {
			Log.d(obj.getClass().getSimpleName(), msg);
		}
	}

	/**
	 * �Ƽ�ʹ�á��õ�����������Ϊtagֵ��������־���
	 * 
	 * @param obj
	 *            ��һ�㴫�� this
	 * @param msg
	 *            ��־��Ϣ
	 * @param throwable
	 *            �쳣����,û�пɴ���null, or {@link #d(Object, String)}
	 * 
	 * @see {@link #isOpenDebug}
	 */
	public static void d(Object obj, String msg, Throwable throwable) {
		if (isOpenDebug) {
			Log.d(obj.getClass().getSimpleName(), msg, throwable);
		}
	}
}
