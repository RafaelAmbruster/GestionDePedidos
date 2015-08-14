package ec.com.rac.gestiondepedidos.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import com.crashlytics.android.Crashlytics;
import ec.com.rac.gestiondepedidos.R;
import ec.com.rac.gestiondepedidos.util.Location;
import io.fabric.sdk.android.Fabric;

public class SplashScreenActivity extends Activity {

    boolean load = false;
    boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.splash_screen);
        new Location(this, null).LoadLocation();
        Run();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void Start() {
        Intent intent = new Intent(SplashScreenActivity.this, AppFragmentActivity.class);
        startActivity(intent);
        finish();
    }

    protected void Run() {
        int previousDegrees = 0;
        int degrees = 360;
        final RotateAnimation animation = new RotateAnimation(previousDegrees, degrees,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillEnabled(true);
        animation.setFillAfter(true);
        animation.setDuration(1500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (load)
                    Start();
                else {
                    if (!error)
                        findViewById(R.id.placeholder_image).startAnimation(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        findViewById(R.id.placeholder_image).startAnimation(animation);
    }
}
