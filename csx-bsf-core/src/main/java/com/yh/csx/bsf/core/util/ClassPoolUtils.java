package com.yh.csx.bsf.core.util;

import javassist.ClassPool;
import javassist.LoaderClassPath;

public class ClassPoolUtils {

	private static volatile ClassPool instance;

	private ClassPoolUtils() {
	}

	public static ClassPool getInstance() {
		if (instance == null) {
			synchronized (ClassPoolUtils.class) {
				if (instance == null) {
					instance = ClassPool.getDefault();
					instance.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
				}
			}
		}
		return instance;
	}

}
