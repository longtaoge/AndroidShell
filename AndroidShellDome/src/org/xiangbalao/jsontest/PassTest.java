package org.xiangbalao.jsontest;

import java.util.Arrays;

import org.xiangbalao.jsontest.pass.AES;
import org.xiangbalao.jsontest.utils.LogUtil;

import android.test.AndroidTestCase;

public class PassTest extends AndroidTestCase {

	public void testa() {

		try {
			LogUtil.i("PassTest", AES.encrypt(
					"45012e0d489dc0a48d27efc61c0af874", "ABCDEFGHIGKLMN"));
			int b = 1 ^ 8;

			LogUtil.i("PassTest1", String.valueOf(b));

			LogUtil.i("PassTest1", String.valueOf(b ^ 8));

			byte[] readBuffer = "中国".getBytes();
			LogUtil.i("PassTest___",  Arrays.toString(readBuffer)   );
			for (int i = 0; i < readBuffer.length; i++) {
				readBuffer[i]=   (byte) (readBuffer[i]^3);
			}
			
			LogUtil.i("PassTest___", Arrays.toString(readBuffer));
	
			
			for (int i = 0; i < readBuffer.length; i++) {
				readBuffer[i]=   (byte) (readBuffer[i]^3);
			}
			
			LogUtil.i("PassTest___", Arrays.toString(readBuffer));
			

		} catch (Exception e) {
			//
			e.printStackTrace();
		}

		// LogUtil.i("JSON", getJsonData().getData().getCount());

	}

}
