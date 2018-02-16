package me.navik;

import android.Manifest;
import android.content.Context;
import android.graphics.PointF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
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
        imageView.setPin(new PointF(1950, 259), "2");
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
        if (internet_connection()){
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
                            imageView.setPin(new PointF(par[0], par[1]), "1");

                        }
                    });


        }else{
            //create a snackbar telling the user there is no internet connection and issuing a chance to reconnect
            final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    "No internet connection.",
                    Snackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimary));
            snackbar.setAction(R.string.try_again, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //recheck internet connection and call DownloadJson if there is internet
                }
            }).show();
        }


    }


  /*  public void livelocation(View view)
    {
        if (internet_connection()){
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
                            imageView.setPin(new PointF(par[0], par[1]), "1");
                            imageView.setPin(new PointF(1950, 659), "2");
                        }
                    });


        }else{
            //create a snackbar telling the user there is no internet connection and issuing a chance to reconnect
            final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    "No internet connection.",
                    Snackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimary));
            snackbar.setAction(R.string.try_again, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //recheck internet connection and call DownloadJson if there is internet
                }
            }).show();
        }


    }  */


 /*   public void dest(View view)
    {

        imageView.setPin(new PointF(1950, 259), "3");

    } */

    boolean internet_connection(){
        //Check if connected to internet, output accordingly
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }


}
