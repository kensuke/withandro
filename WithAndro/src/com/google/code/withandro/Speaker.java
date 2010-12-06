package com.google.code.withandro;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class Speaker implements OnCompletionListener {

	
	private static Speaker self;
	Nisedrod activity;

	// コンストラクタ
	private Speaker() {
		
	}
	
	public static Speaker GetInstance() {
		if (self == null) {
			self = new Speaker();
		}
		
		return self;
	}
	
	
	class SoundInfo {
		double distance;
		boolean played;
		int resid;

		public SoundInfo(double d, int resid) {
			distance = d;
			played = false;
			this.resid = resid;
		}
	
	}
	
	
	SoundInfo[] sounds;
	SoundInfo[] hundreds_sounds;

	
	// 初期化
	public void Open(Activity act) {
		if (act == null) {
			return;
		}
		
		activity = (Nisedrod)act;
		
		hundreds_sounds = new SoundInfo[10];
		hundreds_sounds[0]  = new SoundInfo(000, R.raw.w100);
		hundreds_sounds[1]  = new SoundInfo(100, R.raw.w100);
		hundreds_sounds[2]  = new SoundInfo(200, R.raw.w200);
		hundreds_sounds[3]  = new SoundInfo(300, R.raw.w300);
		hundreds_sounds[4]  = new SoundInfo(400, R.raw.w400);
		hundreds_sounds[5]  = new SoundInfo(500, R.raw.w500);
		hundreds_sounds[6]  = new SoundInfo(600, R.raw.w600);
		hundreds_sounds[7]  = new SoundInfo(700, R.raw.w700);
		hundreds_sounds[8]  = new SoundInfo(800, R.raw.w800);
		hundreds_sounds[9]  = new SoundInfo(900, R.raw.w900);
		
		
		sounds = new SoundInfo[20];
		sounds[0]  = new SoundInfo( 1000, R.raw.w1000);
		sounds[1]  = new SoundInfo( 2000, R.raw.w2000);
		sounds[2]  = new SoundInfo( 3000, R.raw.w3000);
		sounds[3]  = new SoundInfo( 4000, R.raw.w4000);
		sounds[4]  = new SoundInfo( 5000, R.raw.w5000);
		sounds[5]  = new SoundInfo( 6000, R.raw.w6000);
		sounds[6]  = new SoundInfo( 7000, R.raw.w7000);
		sounds[7]  = new SoundInfo( 8000, R.raw.w8000);
		sounds[8]  = new SoundInfo( 9000, R.raw.w9000);
		sounds[9]  = new SoundInfo(10000, R.raw.w10000);
		sounds[10] = new SoundInfo(11000, R.raw.w11000);
		sounds[11] = new SoundInfo(12000, R.raw.w12000);
		sounds[12] = new SoundInfo(13000, R.raw.w13000);
		sounds[13] = new SoundInfo(14000, R.raw.w14000);
		sounds[14] = new SoundInfo(15000, R.raw.w15000);
		sounds[15] = new SoundInfo(16000, R.raw.w16000);
		sounds[16] = new SoundInfo(17000, R.raw.w17000);
		sounds[17] = new SoundInfo(18000, R.raw.w18000);
		sounds[18] = new SoundInfo(19000, R.raw.w19000);
		sounds[19] = new SoundInfo(20000, R.raw.w20000);
	}
	
	
	// 後始末
	public void Close() {
		if (activity != null) {
			activity = null;
		}
		
		if (player != null) {
			if (player.isPlaying()) {
				player.stop();
			}
			player = null;
		}
	}
	
	
	// 百の位を取得
	public int ThreeRound(int num) {
		if (num < 100) {
			return 0;
		}
		else if (num < 1000) { // 100-999 // 1km未満
			return num/100;
		}
		else if (num < 10000) { // 1000-9999 // 1km-10km未満
			return (num - num/1000*1000)/100;
		}
		else if (num < 100000) { // 10000-999999 // 10km-99km未満
			int n = num - num/10000*10000;
			return (n - n/1000*1000)/100;
		}		
		// ここにはこない
		
		return 0;
	}

	public void PlaySoundOverDistance(double distance) {

		// １００メートル単位
		int idx = ThreeRound((int)distance);
		if (idx > 0 && idx < hundreds_sounds.length) {		
			if (!hundreds_sounds[idx].played) {
				hundreds_sounds[idx].played = true;
				PlaySound(hundreds_sounds[idx].resid);
			}
		}
		
		
		// １キロ単位
		for (int i = 0; i < sounds.length; i++) {
			if (distance > sounds[i].distance && !sounds[i].played) {
				// １００メートル単位をリセット
				for (int j = 1; j < hundreds_sounds.length; j++) {
					hundreds_sounds[j].played = false;
				}			
				
				sounds[i].played = true;
				PlaySound(sounds[i].resid);
				if (i+1 < sounds.length && distance < sounds[i+1].distance) {
					break;
				}
			}
		}
	}
	
	MediaPlayer player;
	
    public void PlaySound(int resid) {
    	if (player != null && player.isPlaying()) {
    		while (player.isPlaying())
    			;
    	}
    	
    	try {
			player = MediaPlayer.create(activity, resid);
			player.setOnCompletionListener(this);
			player.start();
		} catch (IllegalStateException e) {
			SDLog.put(e.toString(), true, SDLog.SDFILE);
		}
    }

	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO 自動生成されたメソッド・スタブ

		SDLog.put("", true, SDLog.SDFILE);
	}	
	
}
