package matthew.pecompass.components.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Sihua on 2016/11/24.
 */
public class NoCollisionViewPager extends ViewPager {

    private float mDownX;
    private float mDownY;
    public NoCollisionViewPager(Context context) {
        super(context);
    }

    public NoCollisionViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final float x=ev.getX();
        final float y=ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownX=x;
                mDownY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                float delX=Math.abs(x-mDownX);
                float delY=Math.abs(y-mDownY);
                if(delX<delY){
                    //left/right
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
