package com.thomas.surfaceviewbackground;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private SurfaceView surfaceview;
	private Button btnGo;
	private MediaPlayer mediaPlayer;

	private int postion = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById();
		initView();
	}

	protected void findViewById() {
		// TODO Auto-generated method stub
		surfaceview = (SurfaceView) findViewById(R.id.surfaceView);
		btnGo = (Button) findViewById(R.id.btn_goto);

	}

	protected void initView() {
		// TODO Auto-generated method stub
		mediaPlayer = new MediaPlayer();
		surfaceview.getHolder().setKeepScreenOn(true);
		surfaceview.getHolder().addCallback(new SurfaceViewLis());
		btnGo.setOnClickListener(this);
	}

	private class SurfaceViewLis implements SurfaceHolder.Callback {

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {

		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			if (postion == 0) {
				try {
					play(); 
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {

		}

	}

	public void play() throws IllegalArgumentException, SecurityException,
			IllegalStateException, IOException { 
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		AssetFileDescriptor fd = this.getAssets().openFd("start.mp4");
		mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(),
				fd.getLength());
		mediaPlayer.setLooping(true);
		mediaPlayer.setDisplay(surfaceview.getHolder());
		// 通过异步的方式装载媒体资源
		mediaPlayer.prepareAsync();
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				// 装载完毕回调
				mediaPlayer.start();
			}
		}); 
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_goto:
			Toast.makeText(this, "进入应用", 1*1000).show();
			break;
		default:
			break;
		}
	}
}
