package org.xiangbalao.app;

import android.app.Application;
import android.util.Log;

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