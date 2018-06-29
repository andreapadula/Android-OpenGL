package com.example.andrea.myapplication;

/**
 * Created by andrea on 3/25/17.
 */

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * A view container where OpenGL ES graphics can be drawn on screen.
 * This view can also be used to capture touch events, such as a user
 * interacting with drawn objects.
 */
public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.  CHANGED to 3.0  but using 3.2  JW.
        setEGLContextClientVersion(3);  //won't take real number, so 3.1 doesn't work.  left at 3.
        //fix for error No Config chosen, but I don't know what this does.
        super.setEGLConfigChooser(8 , 8, 8, 8, 16, 0);
        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.
//
//        float x = e.getX();
//        float y = e.getY();
////
////        switch (e.getAction()) {
////            case MotionEvent.ACTION_MOVE:
////
////                float dx = x - mPreviousX;
////                float dy = y - mPreviousY;
////
////                // reverse direction of rotation above the mid-line
////                if (y > getHeight() / 2) {
////                    dx = dx * -1 ;
////                }
////
////                // reverse direction of rotation to left of the mid-line
////                if (x < getWidth() / 2) {
////                    dy = dy * -1 ;
////                }
////
////                mRenderer.setAngle(
////                        mRenderer.getAngle() +
////                        ((dx + dy) * TOUCH_SCALE_FACTOR));  // = 180.0f / 320
//        //requestRender();
////        }
////
//        mPreviousX = x;
//        mPreviousY = y;
//
//        mRenderer.setVert(e.getRawX()/1000,e.getY()/getHeight() / 2);
        float x = e.getX();
        float y = e.getY();
        float screenWidth=getWidth();
        float screenHeight=getHeight();

        float sceneX = (x/screenWidth)*2.0f - 1.0f;
        float sceneY = (y/screenHeight)*-2.0f + 1.0f;
        mRenderer.setVert(sceneX,sceneY);
        requestRender();
        return true;
    }

}