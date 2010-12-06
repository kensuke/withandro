package com.example;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

public class FlickableActivity extends Activity {

	
	// フリッカブル～

	
	// directionとorientationの(方向の意としての)使い分け
	// http://oshiete.goo.ne.jp/qa/2434794.html
    // ^^;
	
	// 水平、垂直
    private enum Orientation {
    	HORIZONTAL,
    	VERTICAL
    }
    
    // 上下左右
    private enum Direction {
    	NULL,
    	UP,
    	DOWN,
    	LEFT,
    	RIGHT
    };
    
    
    protected ArrayList<Class> activities;
    
    
    protected float lastTouchX;
    protected float lastTouchY;
	

    /**
     * コンストラクタ
     * */
	public FlickableActivity() {
		super();

		activities = new ArrayList<Class>();
		
		// on～での初期化は必要？
		// タッチイベントで更新される＝イベントハンドラで処理すれば十分？？
		lastTouchX = 0;
		lastTouchY = 0;
	}


	
	
    
    @Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction() & 0xff; // MotionEvent.ACTION_MASK
		
		switch (action)  {
		case MotionEvent.ACTION_DOWN:   //モーション開始
			lastTouchX = event.getX();
			lastTouchY = event.getY();
			break;
			
		case MotionEvent.ACTION_CANCEL: //モーション失敗
			return true;
			
		case MotionEvent.ACTION_UP:     //モーションアップ
			//Log.d("viewFlipper", "++ otionEvent.ACTION_CANCEL ++ " + event.toString());
			float currentX = event.getX();
			float currentY = event.getY();
			
			System.out.println(String.format("LX,LY[%3.2f, %3.2f] CX,CY[%3.2f, %3.2f]", lastTouchX, lastTouchY, currentX, currentY));
			
			this.flickOrTap(currentX, currentX - lastTouchX
					, currentY, currentY - lastTouchY);
			break;
		}
		
		/*
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		
		//return true;
		return super.onTouchEvent(event);
	}


	private boolean flickOrTap(float currentX, float velocityX, float currentY,
			float velocityY) {
        //基準の移動距離はViewConfigurationを使用する
        int minimumFlingVelocity =  ViewConfiguration.getMinimumFlingVelocity();

        velocityX = Math.abs(velocityX);
        velocityY = Math.abs(velocityY);
        
        if ( velocityX < minimumFlingVelocity && 
             velocityY < minimumFlingVelocity  ) {
            //変量 < 閾値の場合タップ        	
        	View view = getCurrentFocus();
        	if (view != null) {
        		return view.performClick();
        	}

        	/*
            MonthlyCalendarView currentView = 
                (MonthlyCalendarView)viewFlipper.getCurrentView();
            if ( currentView != null ) {
                boolean result = currentView.performClick(); //対象ビューのクリックを発生させる
                performSelectionCalendar(currentView);
                return result;
            }
            */

        } else {
            //変量 >= 閾値の場合フリック

            //X軸とY軸の変量の大きい方にアニメーション
        	Orientation ori = ( velocityY >= velocityX )
        		? Orientation.VERTICAL
        		: Orientation.HORIZONTAL;
            
        	Direction dir = Direction.NULL;


           	if (ori == Orientation.HORIZONTAL) {
        		dir = (lastTouchX < currentX)
    			? Direction.RIGHT
    			: Direction.LEFT;
        	} else if (ori == Orientation.VERTICAL) {
        		dir = (lastTouchY < currentY)
    			? Direction.DOWN
    			: Direction.UP;
        	}
           	
           	int acts = activities.size();
           	if (acts < dir.ordinal()) {
           		return false;
           	}

           	
           	/*
           	viewFlipper = new ViewFlipper(this);
           	viewFlipper.addView(getCurrentFocus());

           	
           	if (dir == Direction.UP) {
           		viewFlipper.addView(new FlickUP());
           	} else if (dir == Direction.DOWN) {
           		
           	} else if (dir == Direction.LEFT) {
           		
           	} else if (dir == Direction.RIGHT) {
           		
           	}
           	

           	View v = getCurrentFocus();
           	((ViewFlipper)v).setInAnimation(inFromTop);
           	((ViewFlipper)v).setOutAnimation(outToBottom);
*/
           	
           	Class cls = activities.get(dir.ordinal());
           	Intent intent = new Intent(this, cls);
           	startActivity(intent);
           	finish();

        }
            
        return true;		
	}

	 protected ViewFlipper viewFlipper;
	 
    private static final Animation inFromLeft = AnimationHelper.inFromLeftAnimation();
    private static final Animation outToRight = AnimationHelper.outToRightAnimation();
    private static final Animation inFromRight = AnimationHelper.inFromRightAnimation();
    private static final Animation outToLeft = AnimationHelper.outToLeftAnimation();

    private static final Animation inFromTop = AnimationHelper.inFromTopAnimation();
    private static final Animation outToBottom = AnimationHelper.outToBottomAnimation();
    private static final Animation inFromBottom = AnimationHelper.inFromBottomAnimation();
    private static final Animation outToTop = AnimationHelper.outToTopAnimation();
    
    
    
	/*
    private void showPreviousMonth(int direction) {

    	int index = this.viewFlipper.getDisplayedChild() == 0 ? 1 : 0;
        MonthlyCalendarView calendarView = 
            (MonthlyCalendarView)this.viewFlipper.getChildAt(index);
    	
        if ( calendarView != null ) {
            calendarView.setCalendar(this.calendar);
            if ( direction == DIRECTION_VERTICAL ) {
                this.viewFlipper.setInAnimation(inFromTop);
                this.viewFlipper.setOutAnimation(outToBottom);
            } else {
                this.viewFlipper.setInAnimation(inFromLeft);
                this.viewFlipper.setOutAnimation(outToRight);
            }

            if ( direction == DIRECTION_VERTICAL ) {
                this.viewFlipper.setInAnimation(inFromBottom);
                this.viewFlipper.setOutAnimation(outToTop);
            } else {
                this.viewFlipper.setInAnimation(inFromRight);
                this.viewFlipper.setOutAnimation(outToLeft);
            }

            this.viewFlipper.showPrevious();
        }
    }
        */

    	
}
