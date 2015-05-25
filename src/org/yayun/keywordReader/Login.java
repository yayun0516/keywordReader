package org.yayun.keywordReader;

import net.youmi.android.spot.SpotManager;

import org.yayun.keywordReader.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;

public class Login extends Activity {
	private ImageView imageView = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // 生命周期方法
		SpotManager.getInstance(this).loadSpotAds();
		SpotManager.getInstance(this).showSpotAds(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);// 填充标题�?
		super.setContentView(R.layout.login); // 设置要使用的布局管理�?
		this.imageView = (ImageView) super.findViewById(R.id.view);
		this.imageView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				Intent intent = new Intent(Login.this, Abstract_activity.class);

				startActivity(intent);

				return false;
			}
		});

	}

}
