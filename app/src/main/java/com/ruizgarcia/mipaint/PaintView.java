package com.ruizgarcia.mipaint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

//Clase para gestionar la funcionalidad de dibujo de la aplicación
public class PaintView extends View {

    public static int BRUSH_SIZE = 20;
    public int DEFAULT_COLOR = getResources().getColor(R.color.red);
    public int RED_COLOR = getResources().getColor(R.color.red);
    public final int BLUE_COLOR = getResources().getColor(R.color.blue);
    public final int ORANGE_COLOR = getResources().getColor(R.color.orange);
    public final int GREEN_COLOR = getResources().getColor(R.color.green);
    public static final int DEFAULT_BG_COLOR = Color.WHITE;
    private static final float TOUCH_TOLERANCE = 4;
    private float mX, mY;
    private Path mPath;
    private Paint mPaint;
    private ArrayList<FingerPath> paths = new ArrayList<>();
    private int currentColor;
    private int backgroundColor = DEFAULT_BG_COLOR;
    private int strokeWidth;
    private boolean star;
    private boolean face;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    private Bitmap bitmapFace = BitmapFactory.decodeResource(getResources(), R.drawable.alejandro_mini);
    private Bitmap bitmapStar = BitmapFactory.decodeResource(getResources(), R.drawable.star_blue_mini);
    private HashMap<Float, Float> posicionesCara = new HashMap<Float, Float>();
    private HashMap<Float, Float> posicionesEstrella = new HashMap<Float, Float>();

    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xff);
    }

    //inicializar dibujo
    public void init(DisplayMetrics metrics) {
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        currentColor = DEFAULT_COLOR;
        strokeWidth = BRUSH_SIZE;
    }

    //pincel de colores
    public void normal() {
        star = false;
        face = false;
        invalidate();
    }

    //pincel de estrella
    public void star() {
        star = true;
        face = false;
        posicionesEstrella.clear();
        posicionesCara.clear();
        paths.clear();
        invalidate();
    }

    //pincel de cara
    public void face() {
        star = false;
        face = true;
        posicionesEstrella.clear();
        posicionesCara.clear();
        paths.clear();
        invalidate();
    }

    //borrar dibujos
    public void clear() {
        backgroundColor = DEFAULT_BG_COLOR;
        posicionesEstrella.clear();
        posicionesCara.clear();
        paths.clear();
        normal();
        invalidate();
    }

    //cambiar color a rojo
    public void changeColorToRed() {
        normal();
        backgroundColor = DEFAULT_BG_COLOR;
        DEFAULT_COLOR = RED_COLOR;
        posicionesEstrella.clear();
        posicionesCara.clear();
        invalidate();
    }

    //cambiar color a azul
    public void changeColorToBlue() {
        normal();
        DEFAULT_COLOR = BLUE_COLOR;
        posicionesEstrella.clear();
        posicionesCara.clear();
        invalidate();
    }

    //cambiar color a naranja
    public void changeColorToOrange() {
        normal();
        DEFAULT_COLOR = ORANGE_COLOR;
        posicionesEstrella.clear();
        posicionesCara.clear();
        invalidate();
    }

    //cambiar color a verde
    public void changeColorToGreen() {
        normal();
        DEFAULT_COLOR = GREEN_COLOR;
        posicionesEstrella.clear();
        posicionesCara.clear();
        invalidate();
    }

    //sobreescribimos método OnDraw
    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas.drawColor(backgroundColor);
        for (FingerPath fp : paths) {
            mPaint.setColor(fp.color);
            mPaint.setStrokeWidth(fp.strokeWidth);
            mPaint.setMaskFilter(null);

            //Condiciones para elegir pincel

            if (fp.face) {
                posicionesEstrella.put(mX, mY);
                for (Float i : posicionesEstrella.keySet()) {
                    canvas.drawBitmap(bitmapStar, i, posicionesEstrella.get(i), null);
                }

            } else if (fp.star) {
                posicionesCara.put(mX, mY);
                for (Float i : posicionesCara.keySet()) {
                    canvas.drawBitmap(bitmapFace, i, posicionesCara.get(i), null);
                }

            } else {
                //pincel de colores
                canvas.drawPath(fp.path, mPaint);
                mCanvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
            }
        }
    }

    //Métodos para captar pulsaciones del usuario en la pantalla

    private void touchStart(float x, float y) {
        mPath = new Path();
        FingerPath fp = new FingerPath(currentColor, face, star, strokeWidth, mPath);
        paths.add(fp);
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp() {
        mPath.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                invalidate();
                break;
        }
        return true;
    }
}