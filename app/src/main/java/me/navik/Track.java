package me.navik;

import android.Manifest;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.orchestral.findsdk.FindClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class Track extends AppCompatActivity {
    FindClient findClient;
    PinView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        imageView = (PinView) findViewById(R.id.PinView);
        imageView.setImage(ImageSource.resource(R.drawable.plan1));
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */}
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();
        findClient = new FindClient.Builder(this)
                .baseUrl("http://iamaswin.me:18003")
                .group("ideathon")
                .username("gola")
                .build();

        findClient.track()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String location) {
                        Timber.d("Location: " + location);
                        String [] cor = location.split("_", 2);
                        Float[] par=new Float[cor.length];
                        int i=0;
                        for(String str:cor){
                            par[i]=Float.parseFloat(str);//Exception in this line
                            i++;
                        }
                        // imageView.setPin(new PointF(500f, 500f));
                        imageView.setPin(new PointF(par[0], par[1]));

                    }



                });

    }


    public void livelocation(View view)
    {

        findClient.track()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String location) {
                        Timber.d("Location: " + location);
                        String [] cor = location.split("_", 2);
                        Float[] par=new Float[cor.length];
                        int i=0;
                        for(String str:cor){
                            par[i]=Float.parseFloat(str);//Exception in this line
                            i++;
                        }
                        // imageView.setPin(new PointF(500f, 500f));
                        imageView.setPin(new PointF(par[0], par[1]));

                    }



                });

    }

    public void dest(View view)
    {
        imageView.setPin(new PointF(960,525));

    }


}
