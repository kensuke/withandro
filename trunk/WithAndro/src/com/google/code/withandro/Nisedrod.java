package com.google.code.withandro;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Nisedrod extends Activity {

	private Locater locater;
	private Speaker speaker;
		
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		SDLog.put("", true, SDLog.SDFILE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dddddd);    

        InitGUI();
    }
          
    public void onStart() {
		SDLog.put("", true, SDLog.SDFILE);
    	super.onStart();
    }
    
    public void onRestart() {
		SDLog.put("", true, SDLog.SDFILE);
    	super.onRestart();
    }
    
    public void onResume() {
		SDLog.put("", true, SDLog.SDFILE);
    	super.onResume();
    }
    
    public void onPause() {
		SDLog.put("", true, SDLog.SDFILE);
    	super.onPause();
    }

    public void onStop() {
		SDLog.put("", true, SDLog.SDFILE);
    	super.onStop();
    	//loc.Close();
    }
    
    public void onDestroy() {
		SDLog.put("", true, SDLog.SDFILE);
    	super.onDestroy();
    	//loc.Close();
    }
    
    
    // おしゃべり
    public void PlaySoundOverDistance(double distance) {
    	if (speaker == null) {
    		return;
    	}
 
    	speaker.PlaySoundOverDistance(distance);
    }


    // ディスプレイ更新
    public void UpdateDisplay(Location loc, double dist) {
    	GetEditTextAndSetText(R.id.EditText01, String.format("%.7f", loc.getLatitude()));
    	GetEditTextAndSetText(R.id.EditText02, String.format("%.7f", loc.getLongitude()));
    	GetEditTextAndSetText(R.id.EditText03, String.format("%.2f m", loc.getAltitude()));
    	GetEditTextAndSetText(R.id.EditText04, String.format("%.2f km/s", loc.getSpeed()));
    	GetEditTextAndSetText(R.id.EditText05, String.format("%.2f", loc.getBearing()));
    	GetEditTextAndSetText(R.id.EditText06, String.format("%.2f m", loc.getAccuracy()));
    	GetEditTextAndSetText(R.id.EditText07, new SimpleDateFormat("MM/dd HH:mm:ss").format(new Date(loc.getTime())));
    	GetEditTextAndSetText(R.id.EditText08, String.format("%.2f m", dist));			    
    }
    
    public boolean GetEditTextAndSetText(int rid, String text) {
    	if (text == null) {
    		return false;
    	}
    	
    	EditText edit = (EditText)this.findViewById(rid);    
    	if (edit == null) {
    		return false;
    	}
    	
    	edit.setText(text);
    	return true;
    }
    
    
    // ボタンのクリックハンドラ登録
    public void InitGUI() {
    	Button start = (Button)this.findViewById(R.id.button_start);
    	start.setOnClickListener(new StartButtonClickAdapter(this));
    	
    	Button stop = (Button)this.findViewById(R.id.button_stop);
    	stop.setOnClickListener(new StopButtonClickAdapter());
    }
    

    // 開始ボタンクリック
    class StartButtonClickAdapter implements OnClickListener {

    	Activity activity;
    	
    	public StartButtonClickAdapter(Activity act) {
    		activity = act;
    	}
    	
		@Override
		public void onClick(View view) {
			locater = Locater.GetInstance();
			if (locater == null) {
				return;
			}
			
			speaker = Speaker.GetInstance();
			if (speaker == null) {
				locater.Close();
				locater = null;
				return;
			}
			
			locater.Open(activity);
			speaker.Open(activity);
		}
    
    }
    
    // 終了ボタンクリック
    class StopButtonClickAdapter implements OnClickListener {

		@Override
		public void onClick(View view) {
			if (locater == null && speaker == null) {
				return;
			}
			if (locater != null) {
				locater.Close();
			}
			if (speaker != null) {
				speaker.Close();
			}
			
			locater = null;
			speaker = null;
		}
    
    }
    
    
    
    // コマンド実行
    public int ExecCommand(String cmd) {
    	if (cmd == null) {
    		return 0;
    	}

    	int ret = 0;
    	ProcessBuilder pb = new ProcessBuilder("/data/local/tmp/grabfb");
	    try {
	        Process p = pb.start();
	        ret = p.waitFor();
	        System.out.println("process exited with value : " + ret);
	    } catch (IOException e) {
	        // start()で例外が発生
	    	e.printStackTrace();
	    } catch (InterruptedException e) {
	        // waitFor()で例外が発生
	    	e.printStackTrace();
	    }

	    return ret;	    
    }
    
}
