package ml.bimdev.graphmagic.graphview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ml.bimdev.graphmagic.graph.Graph;
import ml.bimdev.graphmagic.graph.UndirectedGraph;
import ml.bimdev.graphmagic.graph.Vertex;
import ml.bimdev.graphmagic.graphview.graphs.VisibleUndirectedGraph;


public class GraphView extends View {
    private Paint gridPaint;
    private float canvasSize;
    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleDetector;
    private float mScaleFactor = 1f;
    private int gridSize = 30;
    private int xOffset = 0, yOffset = 0;

    private Visible visible;


    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);

        gridPaint = new Paint();
        gridPaint.setAntiAlias(true);
        gridPaint.setDither(true);
        gridPaint.setColor(Color.argb(100, 100, 100, 100));
        gridPaint.setStrokeWidth(0.5f);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeJoin(Paint.Join.BEVEL);
        gridPaint.setStrokeCap(Paint.Cap.SQUARE);

        canvasSize = this.getWidth();

        gestureDetector = new GestureDetector(getContext(), new MyGestureListener());
        scaleDetector = new ScaleGestureDetector(getContext(), new MyScaleListener());
        visible = new VisibleUndirectedGraph();
        ((VisibleUndirectedGraph) visible).addVertex(new Vertex());

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(xOffset, yOffset);
        canvas.scale(mScaleFactor, mScaleFactor);
        drawGrid(canvas);

        visible.onDraw(canvas);


        canvas.restore();
    }

    private void drawGrid(Canvas canvas) {
        int w = canvas.getWidth(), h = canvas.getHeight();

        for (int x = 0; x < w; x += gridSize)
            canvas.drawLine((float) x, 0, (float) x, h, gridPaint);
        for (int y = 0; y < h; y += gridSize)
            canvas.drawLine(0, (float) y, w, (float) y, gridPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return gestureDetector.onTouchEvent(event) | scaleDetector.onTouchEvent(event);
    }

    //переводим dp в пиксели
    public float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onDown(MotionEvent event) {
            Log.d("TAG","onDown: ");

            // don't return false here or else none of the other
            // gestures will work
            return true;
        }

        @Override
        public boolean onScroll (MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){
            xOffset -= distanceX;
            yOffset -= distanceY;
            invalidate();
            return true;
        }
    }

    private class MyScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            Log.d("GESTURES", "scale: " + Float.toString(mScaleFactor));
            return true;
        }
    }


}

