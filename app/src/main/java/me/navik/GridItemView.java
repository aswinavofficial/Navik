package me.navik;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;



public class GridItemView extends FrameLayout {

    private TextView textView;
    private ImageView imageView;
    private ImageView tickImage;
    private HashMap images;

    public GridItemView(Context context) {
        super(context);
        images = new HashMap<String, Integer>();

        images.put("food", R.drawable.pic2);
        images.put("tech", R.drawable.pic5);
        images.put("fashion", R.drawable.pic7);
        images.put("homeapp", R.drawable.pic3);


        LayoutInflater.from(context).inflate(R.layout.item_grid, this);
        textView = (TextView) getRootView().findViewById(R.id.text);
        imageView = (ImageView) getRootView().findViewById(R.id.image);
        tickImage = (ImageView) getRootView().findViewById(R.id.tick);
    }

    public void display(String text, boolean isSelected) {
        textView.setText(text);
        imageView.setBackgroundResource((int)images.get(text));
        tickImage.setBackgroundResource(R.drawable.tick);
        display(isSelected);
    }

    public void display(boolean isSelected) {
        tickImage.setVisibility((isSelected)? View.VISIBLE: View.INVISIBLE);
    }


}