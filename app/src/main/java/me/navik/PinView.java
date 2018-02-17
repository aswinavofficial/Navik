package me.navik;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.ArrayList;

import me.navik.R.drawable;
/**
 * Created by Aswin on 03-02-2018.
 */

public class PinView extends SubsamplingScaleImageView{

    private ArrayList<PointF> sPin = new ArrayList<>();
    private ArrayList<String> pinNames = new ArrayList<>();
    private Bitmap pin;

    public PinView(Context context) {
        this(context, null);
    }

    public PinView(Context context, AttributeSet attr) {
        super(context, attr);
       // initialise("0");
    }

    public boolean setPin(PointF sPin, String name) {
        if (pinNames.contains(name)){
            return false;
        } else {
            this.sPin.add(sPin);
            pinNames.add(name);
            initialise(name);
            invalidate();
            return true;
        }
    }

    public PointF getPin(String name) {

        return sPin.get(pinNames.indexOf(name));
    }

    public boolean removePin(String name){
        if (pinNames.contains(name)){
            sPin.remove(pinNames.indexOf(name));
            pinNames.remove(name);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> getPinNames(){
        return pinNames;
    }

    private void initialise(String name) {
        float density = getResources().getDisplayMetrics().densityDpi;
        if(name.equals("live"))
        pin = BitmapFactory.decodeResource(this.getResources(), drawable.pushpin_blue);
        if(name.equals("dest"))
           pin = BitmapFactory.decodeResource(this.getResources(), drawable.pushpin_blue);
        float w = (density/420f) * pin.getWidth();
        float h = (density/420f) * pin.getHeight();
        pin = Bitmap.createScaledBitmap(pin, (int)w, (int)h, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Don't draw pin before image is ready so it doesn't move around during setup.
        if (!isReady()) {
            return;
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        for (PointF point : sPin){
            if (point != null && pin != null) {
                PointF vPin = sourceToViewCoord(point);
                float vX = vPin.x - (pin.getWidth()/2);
                float vY = vPin.y - pin.getHeight();
                canvas.drawBitmap(pin, vX, vY, paint);
            }
        }
    }
}
