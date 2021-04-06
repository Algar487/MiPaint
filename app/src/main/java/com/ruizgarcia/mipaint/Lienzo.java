package com.ruizgarcia.mipaint;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.graphics.*;

import java.util.*;

public class Lienzo extends SurfaceView implements SurfaceHolder.Callback
{
    private Hilo hilo;

    public Vector <Pintura> pv;

    private Paint p;

    public Lienzo(Context context)
    {
        super(context);
        getHolder().addCallback(this);

        // Uso un vector para almacenar las coordenadas por donde paso el dedo
        pv = new Vector <Pintura>();

        p = new Paint();

        // Añadimos al lienzo el evento de captura tactil
        this.setOnTouchListener(new OnTouchListener()
        {

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    // Conforme vamos desplazando el dedo, capturamos las coordenadas y las almacenamos en un vector
                    int posXi = (int)event.getX();
                    int posYi = (int)event.getY();

                    // Guardamos las coordenadas donde pintaremos
                    pv.addElement(new Pintura(posXi, posYi));
                }

                return true;
            }
        });

    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3)
    {

    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0)
    {
        // Lanzamos el hilo de ejecución secundario
        hilo = new Hilo(getHolder(), this);
        hilo.setRunning(true);
        hilo.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0)
    {
        // Detenemos el hilo de ejecución secundario
        boolean retry = true;
        hilo.setRunning(false);

        while(retry)
        {
            try
            {
                hilo.join();
                retry = false;
            }
            catch (InterruptedException e)
            {

            }
        }
    }

    // Este método se llamará desde el Hilo para repintar el lienzo en cada vuelta
    public void repintar(Canvas canvas)
    {
        // Primero borramos todo
        canvas.drawColor(Color.WHITE);

        // Despues repintamos lo dibujado
        for(int i=0; i<pv.size(); i++)
        {
            p.setColor(Color.RED);
            canvas.drawCircle(pv.elementAt(i).posX, pv.elementAt(i).posY, 20, p);
        }

    }
}