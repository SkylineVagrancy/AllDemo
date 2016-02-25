package com.pzh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * Created by pzh on 16/1/12.
 */
public class SlideCuntListView extends ListView {
    private int slidePosition;
    private int downY;
    private int downX;
    private int screenWidth;
    private View itemView;
    private Scroller scroller;
    private static final int SNAP_VELOCITY = 600;
    private VelocityTracker velocityTracker;
    private boolean isSlide = false;
    private int mTouchSlop;

    public void setmRemoveListener(RemoveListener mRemoveListener) {
        this.mRemoveListener = mRemoveListener;
    }

    private RemoveListener mRemoveListener;

    public enum RemoveDirection {
        RIGHT, LEFT;
    }

    private RemoveDirection removeDirection;


    public SlideCuntListView(Context context) {
        this(context, null);
    }

    public SlideCuntListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideCuntListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        screenWidth = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        scroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                addvelocityTracker(ev);
                if (!scroller.isFinished()) {
                    return super.dispatchTouchEvent(ev);

                }
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                slidePosition = pointToPosition(downX, downY);
                if (slidePosition == AdapterView.INVALID_POSITION) {
                    return super.dispatchTouchEvent(ev);
                }

                itemView = getChildAt(slidePosition - getFirstVisiblePosition());


                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(getScrollVelocity()) > SNAP_VELOCITY || Math.abs(ev.getX() - downX) > mTouchSlop && Math.abs(ev.getY() - downY) < mTouchSlop) {
                    isSlide = true;
                }

                break;
            case MotionEvent.ACTION_UP:
                recycleVelocityTracker();
                break;

        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isSlide && slidePosition != AdapterView.INVALID_POSITION) {
            requestDisallowInterceptTouchEvent(true);
            addvelocityTracker(ev);
            final int action = ev.getAction();
            int x = (int) ev.getX();
            switch (action) {
                case MotionEvent.ACTION_DOWN:


                    return true;
                case MotionEvent.ACTION_MOVE:
                    MotionEvent canelEvent = MotionEvent.obtain(ev);
                    canelEvent.setAction(MotionEvent.ACTION_CANCEL | (ev.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                    onTouchEvent(canelEvent);
                    int delta = downX - x;
                    downX = x;
                    itemView.scrollBy(delta, 0);

                    break;
                case MotionEvent.ACTION_UP:
                    int velocityX = getScrollVelocity();
                    if (velocityX > SNAP_VELOCITY) {
                        scrollRight();
                    } else if (velocityX < -SNAP_VELOCITY) {
                        scrollLeft();
                    } else {
                        scrollByDistanceX();
                    }
                    recycleVelocityTracker();
                    isSlide = false;
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            itemView.scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
            if (scroller.isFinished()) {
                if(mRemoveListener == null){
                    throw new NullPointerException("RemoveListener is null ,we should called serRemoveListener()");
                }
            }
            itemView.scrollTo(0, 0);
            mRemoveListener.removeItem(removeDirection,slidePosition);
        }
    }

    private void scrollRight() {
        removeDirection = RemoveDirection.RIGHT;
        final int delta = (screenWidth + itemView.getScrollX());
        scroller.startScroll(itemView.getScrollX(), 0, -delta, 0, Math.abs(delta));
        postInvalidate();
    }

    private void scrollLeft() {
        removeDirection = RemoveDirection.LEFT;
        final int delta = (screenWidth - itemView.getScrollX());
        scroller.startScroll(itemView.getScrollX(), 0, delta, 0, Math.abs(delta));
        postInvalidate();
    }

    private void scrollByDistanceX() {
        if (itemView.getScrollX() >= screenWidth / 2) {
            scrollLeft();
        } else if (itemView.getScrollX() <= -screenWidth / 2) {
            scrollRight();
            ;
        } else {
            itemView.scrollTo(0, 0);
        }
    }

    private void addvelocityTracker(MotionEvent event) {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);

    }

    private void recycleVelocityTracker() {
        if (velocityTracker != null) {
            velocityTracker.recycle();
            velocityTracker = null;
        }
    }

    private int getScrollVelocity() {
        velocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) velocityTracker.getXVelocity();
        return velocity;
    }

    public interface RemoveListener {
        public void removeItem(RemoveDirection direction, int position);
    }

}
