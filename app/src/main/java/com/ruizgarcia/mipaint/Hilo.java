package com.ruizgarcia.mipaint;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class Hilo extends Thread
{
    private SurfaceHolder sh;
    private Lienzo l;
    private boolean run;

    public Hilo(SurfaceHolder sh, Lienzo l)
    {
        this.sh = sh;
        this.l = l;
        run = false;
    }

    public void setRunning(boolean run)
    {
        this.run = run;
    }

    public void run()
    {
        Canvas canvas = null;
        while(run)
        {
            try
            {
                canvas = l.getHolder().lockCanvas(null);
                synchronized(sh)
                {
                    // En cada iteracción repintamos todo el lienzo con la información que hay en el vector
                    l.repintar(canvas);
                }
            }
            finally
            {
                if(canvas != null)
                {
                    l.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
