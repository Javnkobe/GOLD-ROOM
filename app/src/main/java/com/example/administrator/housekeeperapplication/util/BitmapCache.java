package com.example.administrator.housekeeperapplication.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

public class BitmapCache {
	static private BitmapCache cache;
	/** ����Chche���ݵĴ洢 */
	private Hashtable<Integer, MySoftRef> hashRefs;
	/** ����Reference�Ķ��У������õĶ����Ѿ������գ��򽫸����ô�������У� */
	private ReferenceQueue<Bitmap> q;

	/**
	 * �̳�SoftReference��ʹ��ÿһ��ʵ�������п�ʶ��ı�ʶ��
	 */
	private class MySoftRef extends SoftReference<Bitmap> {
		private Integer _key = 0;

		public MySoftRef(Bitmap bmp, ReferenceQueue<Bitmap> q, int key) {
			super(bmp, q);
			_key = key;
		}
	}

	private BitmapCache() {
		hashRefs = new Hashtable<Integer, MySoftRef>();
		q = new ReferenceQueue<Bitmap>();
	}

	/**
	 * ȡ�û�����ʵ��
	 */
	public static BitmapCache getInstance() {
		if (cache == null) {
			cache = new BitmapCache();
		}
		return cache;
	}

	/**
	 * �������õķ�ʽ��һ��Bitmap�����ʵ���������ò����������
	 */
	public  void addCacheBitmap(Bitmap bmp, Integer key) {
		cleanCache();// �����������
		MySoftRef ref = new MySoftRef(bmp, q, key);
		hashRefs.put(key, ref);
	}

	/**
	 * ������ָ����drawable�µ�ͼƬ��ԴID�ţ����Ը����Լ�����Ҫ������򱾵�path�»�ȡ�������»�ȡ��ӦBitmap�����ʵ��
	 */
	public Bitmap getBitmap(int resId, Context context) {
		Bitmap bmp = null;
		// �������Ƿ��и�Bitmapʵ���������ã�����У�����������ȡ�á�
		if (hashRefs.containsKey(resId)) {
			MySoftRef ref = (MySoftRef) hashRefs.get(resId);
			bmp = (Bitmap) ref.get();
		}
		// ���û�������ã����ߴ��������еõ���ʵ����null�����¹���һ��ʵ����
		// �����������½�ʵ����������
		if (bmp == null) {
			// ��˵decodeStreamֱ�ӵ���JNI>>nativeDecodeAsset()�����decode��
			// ������ʹ��java���createBitmap���Ӷ���ʡ��java��Ŀռ䡣
			bmp = BitmapFactory.decodeStream(context.getResources()
					.openRawResource(resId));
			this.addCacheBitmap(bmp, resId);
		}
		return bmp;
	}

	private void cleanCache() {
		MySoftRef ref = null;
		while ((ref = (MySoftRef) q.poll()) != null) {
			hashRefs.remove(ref._key);
		}
	}

	/**
	 * ���Cache�ڵ�ȫ������
	 */
	public void clearCache() {
		cleanCache();
		hashRefs.clear();
		System.gc();
		System.runFinalization();
	}
}
