package org.xiangbalao.shellui;

import org.xiangbalao.shell.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ShellActivity extends Activity {

	private Button button1;
	private ImageView imageView1;
	private boolean flag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shell);

		button1 = (Button) findViewById(R.id.button1);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (flag) {
					flag = false;
					button1.setText("返回");
					imageView1.setImageDrawable(getResources().getDrawable(
							R.drawable.tortoise_shell1));
				} else {
					flag = true;
					button1.setText("下一页");
					imageView1.setImageDrawable(getResources().getDrawable(
							R.drawable.tortoise_shell));

				}

			}
		});
	}

}
