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

// http://androidforums.com/application-development/330687-faster-surfaceview-drawing.html

public class DrawingView extends SurfaceView  implements Callback{


	public static final float STROKEWIDTH = 3f;
	public static final int MOVETOLERANCE = 10;
	
	private Bitmap signatureBitmap;
	private Canvas signatureCanvas;
	
	class FPSTimer {  
        private int mFPS;  
        private double mSecPerFrame;  
        private double mSecTiming;  
        private long mCur;  
        public FPSTimer(int fps) {  
            mFPS = fps;  
            reset();  
        }  
        public void reset() {  
            mSecPerFrame = 1.0 / mFPS;  
            mCur = System.currentTimeMillis();  
            mSecTiming = 0.0;  
        }  
        public boolean elapsed() {  
            long next = System.currentTimeMillis();  
            long passage_time = next - mCur;  
            mCur = next;  
            mSecTiming += (passage_time/1000.0);  
            mSecTiming -= mSecPerFrame;  
            if (mSecTiming > 0) {  
                if (mSecTiming > mSecPerFrame) {  
                    reset();  
                    return true; // force redraw  
                }  
                return false;  
            }  
            try {  
                Thread.sleep((long)(-mSecTiming * 1000.0));  
            } catch (InterruptedException e) {  
            }  
            return true;  
        }  
    }  
	
	class DrawingThread extends Thread {  

        private SurfaceHolder mSurfaceHolder;  
        private boolean mRun = false;  
        public DrawingThread(SurfaceHolder holder, Context context, Handler handler) {  
            mSurfaceHolder = holder;  
        }  
        public void setRunning(boolean b) {  
            mRun = b;  
        }  
        public void run() {  
            int fps = 0;  
            long cur = System.currentTimeMillis();  
            boolean isdraw = true;  
            FPSTimer timer = new FPSTimer(120);  
            while (mRun) {  
                Canvas c = null;  
                if (isdraw) {  
                    try {  
                        c = mSurfaceHolder.lockCanvas(null);  
                        synchronized (mSurfaceHolder) {  
                           drawMe(c);
                        }  
                        fps++;  
                    } finally {  
                        if (c != null)  
                            mSurfaceHolder.unlockCanvasAndPost(c);  
                    }  
                }  
                isdraw = timer.elapsed();  
                long now = System.currentTimeMillis();  
                if (now - cur > 1000) {  
                    Log.d("KZK", "FPS=" + (fps * 1000 / ((double)now - cur)));  
                    fps = 0;  
                    cur = now;  
                }  
            }  
        }  
    }  
	
	
	private Hashtable<Integer, Path> pathTable = new Hashtable<Integer, Path>();
	private Hashtable<Integer, Integer> pathUpdateTable = new Hashtable<Integer, Integer>();
	private int pathID=0;
	
	//Bulk Paint
	private Paint mPaint;
	private Paint aPaint;
	
	private Paint penPaint;
	private String msg1="";
	private String msg2="";
	private String msg3="";
	
	private int[] color = {0xFFFFFFFF,0xFFFFFFFF ,0xFFFFFEFF};
	
	private DrawingThread mThread = null;
	
	
	private long maxRefreshTime = 0;
	
	public DrawingView(Context context, AttributeSet attr)
	{
		super(context, attr);
		SurfaceHolder holder = getHolder();  
	    holder.addCallback(this);
	    mThread = new DrawingThread(holder, context, new Handler());
	    mThread.setPriority(10);
	    setupView();
	}
	
	public DrawingView(Context context) {
		super(context);
        //Setting Pen
		SurfaceHolder holder = getHolder();  
	    holder.addCallback(this);
	    mThread = new DrawingThread(holder, context, new Handler());
	    mThread.setPriority(10);
		
//		mBitmap = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);
//        mCanvas = new Canvas(mBitmap);
		
		
		setupView();
	}
	
	
	@SuppressLint("WrongCall")
	void writeToFile(FileOutputStream f) {
		
		boolean retry = true;  
        mThread.setRunning(false);  
        while (retry) {  
            try {  
                mThread.join();  
                retry = false;  
            } catch (InterruptedException e) {  
          }  
        }
		
    	signatureBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
	    signatureCanvas = new Canvas(signatureBitmap);
	   // signatureCanvas.drawRect(0, 0, width, height, backgroundPaint);
	    
	    final Canvas c = getHolder().lockCanvas();
        if (c != null) {
            c.drawBitmap(signatureBitmap, 0, 0, null);
            getHolder().unlockCanvasAndPost(c);
        }
        
		//Bitmap screenshot;
		//setDrawingCacheEnabled(true);
		
		//Bitmap b = getDrawingCache();
		//buildDrawingCache();
		//layout( 0, 0, getWidth(), getHeight());
		//screenshot = Bitmap.createBitmap(getDrawingCache());
		//screenshot.compress(CompressFormat.JPEG, 95,f);
		//setDrawingCacheEnabled(false);
		//destroyDrawingCache();
        signatureBitmap.compress(CompressFormat.JPEG, 95,f);
	
	}
	
	
	@SuppressLint("WrongCall")
	public void setupView()
	{
		//Load Image to canvas
		
//		ImageView imageview = new ImageView(this.getContext()); 
		penPaint = new Paint();
		penPaint.setAntiAlias(true);
			// 그리는 색깔 초기 값
		penPaint.setColor(0xFFFFFFFF);
		penPaint.setStyle(Paint.Style.STROKE);
		penPaint.setStrokeJoin(Paint.Join.ROUND);
		penPaint.setStrokeCap(Paint.Cap.ROUND);
		penPaint.setStrokeWidth(1);
        
        mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		// 그리는 색깔 초기 값
		mPaint.setColor(color[1]);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(STROKEWIDTH);
		mPaint.setTextSize(15f);
		
		aPaint = new Paint();
		aPaint.setAntiAlias(true);
		aPaint.setDither(true);
		// 그리는 색깔 초기 값
		aPaint.setColor(color[2]);
		aPaint.setStyle(Paint.Style.STROKE);
		aPaint.setStrokeJoin(Paint.Join.ROUND);
		aPaint.setStrokeCap(Paint.Cap.ROUND);
		aPaint.setStrokeWidth(20);
		
		
		
	}
	
//	private Bitmap background = BitmapFactory.decodeResource(this.getResources(), R.drawable.background);
	
	public void drawMe(Canvas canvas) {
		canvas.drawColor(0x00000);
//		canvas.drawBitmap(background, 0,0,null);
//       canvas.setBitmap(new BitmapDrawable(this.getContext().getResources().get)));
       
		

		/*msg1 = "Refresh Rate : " + (System.currentTimeMillis()-timegap_draw) + "ms    max refresh rate : "+maxRefreshTime;
		
		if( (maxRefreshTime < System.currentTimeMillis()-timegap_draw) && (System.currentTimeMillis()-timegap_draw < 100))
			maxRefreshTime = System.currentTimeMillis()-timegap_draw;
		timegap_draw = System.currentTimeMillis();
        canvas.drawText(msg1, 10, 10, penPaint);*/

		
        //if(	isUpdate )
		if(true)
        {
        	long drawing_start = System.currentTimeMillis();
        	Enumeration<Integer> e = pathTable.keys();
            
            while(e.hasMoreElements())
            {
            	int k = e.nextElement();
            	
            	mPaint.setColor(color[k]);
            	
            	canvas.drawPath(pathTable.get(k), mPaint);
//            	canvas.drawCircle(xHolder, yHolder, 5, mPaint);
            }
        	
        	long drawing_end = System.currentTimeMillis();
        	
        	/*msg3 = "Drawing Time : " + (drawing_end - drawing_start) + "ms";
            canvas.drawText(msg2, 10, 25, penPaint);
            canvas.drawText(msg3, 10, 40, penPaint);*/
            
        }
		
    }
	
	long timegap_draw=0;	
	
	
	long timegap_touch = 0;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//Acquire ID of pen and its action
		final int action=event.getAction();
		//final int action=event.getActionMasked();
		//final int actionindex = event.getActionIndex();
		//final int id = event.getPointerId(actionindex);
		
		final int id = 0;
		
		/*if(System.currentTimeMillis()-timegap_touch< 100)
		{
			msg2 = "Touch Refresh Rate : " + (System.currentTimeMillis()-timegap_touch) + "ms";
		}*/
		
		timegap_touch = System.currentTimeMillis();
		
		//Log.d("action", ""+action+" action index : " + actionindex);
		
		//Distribute events

				
		float x = event.getX();
		float y = event.getY();
		
		switch(action)
		{
		case MotionEvent.ACTION_POINTER_DOWN:
		case MotionEvent.ACTION_DOWN:
			penDown(id , x, y);
			break;
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_UP:
			penUp(id , x, y);
			break;
			
		case MotionEvent.ACTION_MOVE:
			for(int i = 0 ; i < event.getPointerCount() ; i ++)
			{
				int idMove = event.getPointerId(i);
				x = event.getX(i);
				y = event.getY(i);
				
				penMove(idMove , x , y);
			}
			break;
		}
		invalidate();
		return true;
	}
	
	
	private void penDown(int id, float x , float y)
	{		
		pathUpdateTable.put(id, pathID);
		
		//if(!pathTable.containsKey(id))
			pathTable.put(id, new Path());
		pathTable.get(id).moveTo(x,y);
		
		
		
		Log.d("PEN","PEN"+id+" DOWN at x: "+x+" y: "+y);
	}
	
	private void penUp(int id, float x , float y)
	{	
		pathTable.get(id).rewind();	
//		Log.d("PEN","PEN"+id+" UP at x: "+x+" y: "+y);
	}

	
	
	
	private void penMove(int id, float x , float y)
	{
		if(!pathTable.containsKey(id))
			pathTable.put(id, new Path());
	
		
		pathTable.get(id).lineTo(x,y);

//		Log.d("PEN","PEN"+id+" MOVE at x: "+x+" y: "+y);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	
	    Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLACK);
		// TODO Auto-generated method stub
		signatureBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
	    signatureCanvas = new Canvas(signatureBitmap);
	    signatureCanvas.drawRect(0, 0, width, height, backgroundPaint);
	    
	    final Canvas c = getHolder().lockCanvas();
        if (c != null) {
            c.drawBitmap(signatureBitmap, 0, 0, null);
            getHolder().unlockCanvasAndPost(c);
        }
    }
    
  

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mThread.setRunning(true);
		mThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;  
        mThread.setRunning(false);  
        while (retry) {  
            try {  
                mThread.join();  
                retry = false;  
            } catch (InterruptedException e) {  
            }  
        }
		
	}
}