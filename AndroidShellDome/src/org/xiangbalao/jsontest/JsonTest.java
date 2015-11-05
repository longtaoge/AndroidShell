package org.xiangbalao.jsontest;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.xiangbalao.androidshell.R;
import org.xiangbalao.jsontest.bean.JsonData;
import org.xiangbalao.jsontest.utils.FileOperate;
import org.xiangbalao.jsontest.utils.GsonTools;
import org.xiangbalao.jsontest.utils.LogUtil;

import android.test.AndroidTestCase;

import com.google.gson.Gson;

public class JsonTest extends AndroidTestCase {

	public void testa() {

		LogUtil.i("JSON", getData().getData().getCuxiao());

		LogUtil.i("JSON", getJsonData().getData().getCount());

	}

	// 从资源文件中读取json
	private JsonData getData() {
		// 从
		InputStream is = getContext().getResources()
				.openRawResource(R.raw.json);
		InputStreamReader isr = new InputStreamReader(is);
		Gson gson = new Gson();
		JsonData mJson = gson.fromJson(isr, JsonData.class);

		return mJson;
	}

	@SuppressWarnings("static-access")
	private JsonData getJsonData() {

		String jString;
		JsonData mJson = null;

		FileOperate mFileOperate = new FileOperate(getContext());

		try {

			// assets 文件中读文件
			jString = mFileOperate.readFromAsset("json.txt");

			GsonTools mGsonTools = new GsonTools();

			mJson = mGsonTools.changeGsonToBean(jString, JsonData.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mJson;
	}

}
