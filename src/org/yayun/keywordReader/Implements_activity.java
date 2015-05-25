package org.yayun.keywordReader;

import java.util.Locale;

import org.yayun.keywordReader.R;

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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

public class Implements_activity extends Activity {
	private TextToSpeech tts;
	private ImageButton ImageButton01;
	private WebView webView = null;
	private GestureDetector detector;
	private TextView textView = null;
	private TextView yinbiaoTextView = null;
	private int mWidth;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // 生命周期方法
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 填充标题�?
		super.setContentView(R.layout.abstract_layout); // 设置要使用的布局管理�?
		tts = new TextToSpeech(this, ttsInitListener);
		ImageButton01 = (ImageButton) this.findViewById(R.id.ImageButton01);

		detector = new GestureDetector(new GestureListener());
		this.textView = (TextView) super.findViewById(R.id.mytext);
		this.yinbiaoTextView = (TextView) super.findViewById(R.id.yinbiao);
		Typeface mFace = Typeface.createFromAsset(getAssets(),
				"font/segoeui.ttf");

		yinbiaoTextView.setTypeface(mFace);
		yinbiaoTextView.setText("['ɪmplɪmənts]");// !!!!!!!!!!!!!!!!!!!!!!!

		this.textView.setText("Implements");// !!!!!!!!!!!!!!!!!!!!!!!
		this.webView = (WebView) super.findViewById(R.id.myview);
		webView.loadUrl("file:///android_asset/implements.html");// !!!!!!!!!!!!!!!!!!!!!!!
		this.webView.setOnTouchListener(new TouchListener());
		this.webView.setLongClickable(true);
		ImageButton01.setOnClickListener(new ImageButton.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				/* 传入要说的字符串 */
				tts.speak("implements", TextToSpeech.QUEUE_FLUSH, null);// !!!!!!!!!!!!!!!!!!!!!!!

			}

		});
		mWidth = Implements_activity.getScreenWidth(this);
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
				Intent intent = new Intent(Implements_activity.this,// !!!!!!!!!!!!!!!!!!!!!!!
						Import_activity.class);
				// UpdateStatusActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);

				// Toast.makeText(Abstract_activity.this, "向左手势",
				// Toast.LENGTH_SHORT).show();

			} else if (e2.getX() - e1.getX() > mWidth / 3// 上一�?
					&& Math.abs(velocityX) > minVelocity) {
				// 切换Activity
				Intent intent = new Intent(Implements_activity.this,// !!!!!!!!!!!!!!!!!!!!!!!
						If_activity.class);
				startActivity(intent);
				// Toast.makeText(Assert_activity.this, "第一页！",
				// Toast.LENGTH_SHORT).show();
			}

			return false;
		}

	}
}
