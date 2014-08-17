package uk.co.section9.emfmedical;

import android.view.SurfaceView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;

import java.io.ByteArrayOutputStream;
// http://androidforums.com/application-development/330687-faster-surfaceview-drawing.html
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;


// Creates a view we can draw out our signature on

public class DrawingView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "DrawingView";
    
    private Paint foregroundPaint;
    private Paint backgroundPaint;
    private int width, height;
    private int lastTouchX, lastTouchY;
    private Bitmap pictureBitmap;
    private Canvas pictureCanvas;
    private final Context context;
    
    public DrawingView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    private void init() {
        setFocusableInTouchMode(true);
        getHolder().addCallback(this);
        foregroundPaint = new Paint();
        foregroundPaint.setColor(Color.WHITE);
        foregroundPaint.setStrokeWidth(4);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLACK);
        lastTouchX = -1;
        lastTouchY = -1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        if ((event.getAction() == MotionEvent.ACTION_DOWN) || (event.getAction() == MotionEvent.ACTION_MOVE)) {
            //Log.d(TAG, "Touched " + x + "," + y);
            if ((lastTouchX != -1) && (lastTouchY != -1)) {
                pictureCanvas.drawLine(lastTouchX, lastTouchY, x, y, foregroundPaint);
                draw();
            }
            lastTouchX = x;
            lastTouchY = y;
        } else {
            lastTouchX = -1;
            lastTouchY = -1;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU){
            clear();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
        this.width = width;
        this.height = height;
        if (pictureBitmap != null) {
            pictureBitmap.recycle();
        }
        pictureBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        pictureCanvas = new Canvas(pictureBitmap);
        clear();
        draw();
    }

    public void draw() {
        final Canvas c = getHolder().lockCanvas();
        if (c != null) {
            c.drawBitmap(pictureBitmap, 0, 0, null);
            getHolder().unlockCanvasAndPost(c);
        }
    }
    
    public void clear() {
        pictureCanvas.drawRect(0, 0, width, height, backgroundPaint);
        draw();
    }
    
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (pictureBitmap != null) {
            
            //saveFile(pictureBitmap,"MyImage");
           
            pictureBitmap.recycle();
        }
        pictureBitmap = null;
    }
    
    public ByteArrayOutputStream convertToByteArrayOutputStream()  { 
	    ByteArrayOutputStream os=new ByteArrayOutputStream(); 
	    pictureBitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 80, (OutputStream) os);
	    return os;
    } 

    void writeToFile( FileOutputStream f) {
        // String filename = String.valueOf(System.currentTimeMillis()) ;
     
    	pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 80, f);
                    
        Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show();

    }
}