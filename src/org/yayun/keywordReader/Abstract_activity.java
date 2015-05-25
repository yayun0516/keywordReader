package org.yayun.keywordReader;

import java.util.Locale;

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
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Abstract_activity extends Activity {
	private TextToSpeech tts;
	private ImageButton ImageButton01;
	private WebView webView = null;
	private GestureDetector detector;
	private TextView textView = null;
	private TextView yinbiaoTextView = null;
	private int mWidth;

	public boolean onCreateOptionsMenu(Menu menu) {// ！！！！！！！！！！！！�?
		menu.add(Menu.NONE, Menu.FIRST + 1, 0, "退出").setIcon(null);
		menu.add(Menu.NONE, Menu.FIRST + 2, 1, "关于我们").setIcon(null);
		menu.add(Menu.NONE, Menu.FIRST + 3, 2, "检测更新").setIcon(null);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {// ！！！！！！！！！！！！�?
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			finish();
			break;
		case Menu.FIRST + 2:

			break;
		case Menu.FIRST + 3:

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
		AdManager.getInstance(this).init("c5fbddafb37a478e",
				"eee1729b466a9d36", false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 填充标题�?
		super.setContentView(R.layout.abstract_layout); // 设置要使用的布局管理�?

		AdView adView = new AdView(this, AdSize.FIT_SCREEN);// 加入广告
		LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);// 加入广告
		adLayout.addView(adView);// 加入广告

		tts = new TextToSpeech(this, ttsInitListener);
		ImageButton01 = (ImageButton) this.findViewById(R.id.ImageButton01);

		detector = new GestureDetector(new GestureListener());
		this.textView = (TextView) super.findViewById(R.id.mytext);
		this.yinbiaoTextView = (TextView) super.findViewById(R.id.yinbiao);
		Typeface mFace = Typeface.createFromAsset(getAssets(),
				"font/segoeui.ttf");

		yinbiaoTextView.setTypeface(mFace);
		yinbiaoTextView.setText("[ˈæbˌstrækt]");

		this.textView.setText("Abstract");
		this.webView = (WebView) super.findViewById(R.id.myview);
		webView.loadUrl("file:///android_asset/abstract.html");
		this.webView.setOnTouchListener(new TouchListener());
		this.webView.setLongClickable(true);
		ImageButton01.setOnClickListener(new ImageButton.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				/* 传入要说的字符串 */
				tts.speak("abstract", TextToSpeech.QUEUE_FLUSH, null);

			}

		});
		mWidth = Abstract_activity.getScreenWidth(this);
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
			
			return detector.onTouchEvent(event);
		}
	}



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
			if (e1.getX() - e2.getX() > mWidth / 3
					&& Math.abs(velocityX) > minVelocity) {

				// 切换Activity
				Log.d("11", "verticalMinDistance ");
				Intent intent = new Intent(Abstract_activity.this,
						Assert_activity.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);

				// Toast.makeText(Abstract_activity.this, "向左手势",
				// Toast.LENGTH_SHORT).show();

			} else if (e2.getX() - e1.getX() > mWidth / 3
					&& Math.abs(velocityX) > minVelocity) {
				
				Toast.makeText(Abstract_activity.this, "第一页！",
						Toast.LENGTH_SHORT).show();
			}

			return false;
		}

	}
}
