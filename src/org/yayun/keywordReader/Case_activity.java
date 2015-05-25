package org.yayun.keywordReader;

import java.util.Locale;

import org.yayun.keywordReader.R;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.spot.SpotManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Case_activity extends Activity {
	private TextToSpeech tts;
	private ImageButton ImageButton01;
	private WebView webView = null;
	private GestureDetector detector;
	private TextView textView = null;
	private TextView yinbiaoTextView = null;
	private int mWidth;

	public boolean onCreateOptionsMenu(Menu menu) {//！！！！！！！！！！！！�?
		menu.add(Menu.NONE, Menu.FIRST + 1, 0, "�?�?").setIcon(R.drawable.exit);
		menu.add(Menu.NONE, Menu.FIRST + 2, 1, "关于我们")
				.setIcon(R.drawable.about);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {//！！！！！！！！！！！！�?
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			finish();
			break;

		default:
			break;
		}
		return false;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // 生命周期方法
		SpotManager.getInstance(this).loadSpotAds();
		SpotManager.getInstance(this).showSpotAds(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 填充标题�?
		super.setContentView(R.layout.abstract_layout); // 设置要使用的布局管理�?
		tts = new TextToSpeech(this, ttsInitListener);
		ImageButton01 = (ImageButton) this.findViewById(R.id.ImageButton01);

		AdManager.getInstance(this).init("c5fbddafb37a478e", "eee1729b466a9d36",false);
		AdView adView=new AdView(this,AdSize.FIT_SCREEN);//加入广告
		LinearLayout adLayout=(LinearLayout)findViewById(R.id.adLayout);//加入广告
		adLayout.addView(adView);//加入广告
		detector = new GestureDetector(new GestureListener());
		this.textView = (TextView) super.findViewById(R.id.mytext);
		this.yinbiaoTextView = (TextView) super.findViewById(R.id.yinbiao);
		Typeface mFace = Typeface.createFromAsset(getAssets(),
				"font/segoeui.ttf");

		yinbiaoTextView.setTypeface(mFace);
		yinbiaoTextView.setText("[kes]");// !!!!!!!!!!!!!!!!!!!!!!!

		this.textView.setText("Case");// !!!!!!!!!!!!!!!!!!!!!!!
		this.webView = (WebView) super.findViewById(R.id.myview);
		webView.loadUrl("file:///android_asset/case.html");// !!!!!!!!!!!!!!!!!!!!!!!
		this.webView.setOnTouchListener(new TouchListener());
		this.webView.setLongClickable(true);
		ImageButton01.setOnClickListener(new ImageButton.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				/* 传入要说的字符串 */
				tts.speak("case", TextToSpeech.QUEUE_FLUSH, null);// !!!!!!!!!!!!!!!!!!!!!!!

			}

		});
		mWidth = Case_activity.getScreenWidth(this);
	}

	// 获取屏幕

	private TextToSpeech.OnInitListener ttsInitListener = new TextToSpeech.OnInitListener() {

		public void onInit(int status) {
			// TODO Auto-generated method stub
			/* 使用美国时区目前不支持中�? */
			Locale loc = new Locale("us", "", "");
			/* �?查是否支持输入的时区 */
			if (tts.isLanguageAvailable(loc) == TextToSpeech.LANG_AVAILABLE) {
				/* 设定语言 */
				tts.setLanguage(loc);
			}
			tts.setOnUtteranceCompletedListener(ttsUtteranceCompletedListener);

		}

	};
	private TextToSpeech.OnUtteranceCompletedListener ttsUtteranceCompletedListener = new TextToSpeech.OnUtteranceCompletedListener() {
		public void onUtteranceCompleted(String utteranceId) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		/* 释放TextToSpeech的资�? */
		tts.shutdown();

		super.onDestroy();
	}

	public static int getScreenWidth(Context context) {
		WindowManager manager = null;
		try {
			manager = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("11", "cuowu1 ");
		}
		Display display = manager.getDefaultDisplay();
		return display.getWidth();

	}

	private class TouchListener implements OnTouchListener {
		public boolean onTouch(View v, MotionEvent event) {
			// Toast.makeText(getApplicationContext(), "----?",
			// Toast.LENGTH_SHORT).show();
			return detector.onTouchEvent(event);
		}
	}

	// private int verticalMinDistance =(int)mWidth/2;

	private int minVelocity = 0;

	public class GestureListener implements OnGestureListener {

		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub

		}

		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {

			return false;
		}

		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub

		}

		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if (e1.getX() - e2.getX() > mWidth / 3// 下一�?
					&& Math.abs(velocityX) > minVelocity) {

				// 切换Activity
				Log.d("11", "verticalMinDistance ");
				Intent intent = new Intent(Case_activity.this,// !!!!!!!!!!!!!!!!!!!!!!!
						Catch_activity.class);
				// UpdateStatusActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);

				// Toast.makeText(Abstract_activity.this, "向左手势",
				// Toast.LENGTH_SHORT).show();

			} else if (e2.getX() - e1.getX() > mWidth / 3// 上一�?
					&& Math.abs(velocityX) > minVelocity) {
				// 切换Activity
				Intent intent = new Intent(Case_activity.this,// !!!!!!!!!!!!!!!!!!!!!!!
						Byte_activity.class);
				startActivity(intent);
				// Toast.makeText(Assert_activity.this, "第一页！",
				// Toast.LENGTH_SHORT).show();
			}

			return false;
		}

	}
}
