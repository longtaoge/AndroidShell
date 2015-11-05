package org.xiangbalao.app;

import org.xiangbalao.androidshell.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button1 = (Button) findViewById(R.id.button1);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent(MainActivity.this,
						SecendActivity.class);

				startActivity(mIntent);
				finish();
				// 进场动画、出场动画
//				overridePendingTransition(R.anim.righttoleft_in,
//						R.anim.righttoleft_exit);
//				
				
				overridePendingTransition(R.anim.lefttoright_in,
						R.anim.lefttoright_exit);

			}
		});
	}

}
