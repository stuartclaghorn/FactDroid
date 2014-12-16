package com.example.factdroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ViewFlipper;
import java.util.*;


public class MainActivity extends Activity {
	private ViewFlipper viewFlipper;
	private float lastX;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
    }

    // handle screen swaps
    public boolean onTouchEvent(MotionEvent touchevent) {
    	switch(touchevent.getAction()) {
    	
    	case MotionEvent.ACTION_DOWN:
    		lastX = touchevent.getX();
    		break;
    	case MotionEvent.ACTION_UP:
    		float currentX = touchevent.getX();
    		// left to right screen swap
    		if (lastX < currentX) {
    			// If there aren't any other children - skip
    			if (viewFlipper.getDisplayedChild() == 0)
    				break;
    			
    			// Next screen comes from left
    			viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);
    			// Current screen goes to right
    			viewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);
    			
    			// Display next screen
    			viewFlipper.showNext();
    		}
    		
    		// right to left screen swap
    		if (lastX > currentX) {
    			// If there aren't any other children - skip
    			if (viewFlipper.getDisplayedChild() == 0)
    				break;
    			
    			// Next screen from right
    			viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);
    			// Current screen to left
    			viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);
    			
    			// Display previous screen
    			viewFlipper.showPrevious();
    		}
    		break;
    	}
    	return false;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
