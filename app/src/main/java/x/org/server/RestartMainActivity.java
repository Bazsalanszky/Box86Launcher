// DO NOT EDIT THIS FILE - it is automatically generated, ALL YOUR CHANGES WILL BE OVERWRITTEN, edit the file under $JAVA_SRC_PATH dir
/*
Simple DirectMedia Layer
Java source code (C) 2009-2014 Sergii Pylypenko

This software is provided 'as-is', without any express or implied
warranty.  In no event will the authors be held liable for any damages
arising from the use of this software.

Permission is granted to anyone to use this software for any purpose,
including commercial applications, and to alter it and redistribute it
freely, subject to the following restrictions:

1. The origin of this software must not be misrepresented; you must not
   claim that you wrote the original software. If you use this software
   in a product, an acknowledgment in the product documentation would be
   appreciated but is not required. 
2. Altered source versions must be plainly marked as such, and must not be
   misrepresented as being the original software.
3. This notice may not be removed or altered from any source distribution.
*/

package x.org.server;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allplatform.box86.R;


public class RestartMainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.i("SDL", "Restarting main activity");
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		_layout = new LinearLayout(this);
		_layout.setOrientation(LinearLayout.VERTICAL);
		_layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
		
		//Get the display so we can know the screen size
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		_tv = new TextView(this);
		_tv.setMaxLines(2);
		_tv.setMinLines(2);
		_tv.setText(R.string.restarting_please_wait);
		_tv.setTextSize(30f);
		_tv.setPadding((int)(width * 0.1), (int)(height * 0.1), (int)(width * 0.1), 0);
		_layout.addView(_tv);

		_videoLayout = new FrameLayout(this);
		_videoLayout.addView(_layout);

		setContentView(_videoLayout);

		new Thread(new Runnable()
		{
			public void run()
			{
				try{
					Thread.sleep(2000);
				} catch (InterruptedException e) {}
				Intent intent = new Intent(RestartMainActivity.this, MainActivity.class);
				intent.putExtra(ACTIVITY_AUTODETECT_SCREEN_ORIENTATION, getIntent().getBooleanExtra(ACTIVITY_AUTODETECT_SCREEN_ORIENTATION, false));
				String restartParams = getIntent().getStringExtra(SDL_RESTART_PARAMS);
				intent.putExtra(SDL_RESTART_PARAMS, restartParams == null ? "" : restartParams);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				RestartMainActivity.this.startActivity(intent);
				try{
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
				System.exit(0);
			}
		}).start();
	}

	private TextView _tv = null;
	private LinearLayout _layout = null;
	private FrameLayout _videoLayout = null;

	public static final String ACTIVITY_AUTODETECT_SCREEN_ORIENTATION = "libsdl.org.ACTIVITY_AUTODETECT_SCREEN_ORIENTATION";
	public static final String SDL_RESTART_PARAMS = "SDL_RESTART_PARAMS";
}
