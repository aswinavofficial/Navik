package me.navik;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {
    FindClient findClient;
    Button b;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */}
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();
        b=(Button)findViewById(R.id.button);
        t=(TextView)findViewById(R.id.textView2);
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
                        t.setText(location);
                    }
                });

    }

    public void refresh(View view)
    {


    }

    public void track(View view)
    {
        Intent i = new Intent(getApplicationContext(),Track.class);
        startActivity(i);
    }
}
