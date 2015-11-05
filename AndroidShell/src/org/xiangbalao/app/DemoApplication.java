package org.xiangbalao.app;

import android.app.Application;
import android.util.Log;

/**
 * 
 * 本类是被加壳应用的 Application ，主要用于调试，调试成功后删除本类即可
 * 
 * @author longtaoge
 * 
 */
public class DemoApplication extends Application {

	private static DemoApplication context;

	public static DemoApplication getApplication() {
		return context;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("Dome", "DomeApplication");
	}

	public DemoApplication() {
		super();
		context = this;
	}

}