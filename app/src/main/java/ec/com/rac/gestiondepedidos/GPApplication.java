/*
 * Copyright (c) 2015. Property of Rafael Ambruster
 */

package ec.com.rac.gestiondepedidos;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import io.fabric.sdk.android.Fabric;

public class GPApplication extends Application {

    private static GPApplication instance;
    public static String TAG = "Demo";

    public GPApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Logger.init("Demo Application").hideThreadInfo().setMethodCount(3).setMethodOffset(2);

        DrawerImageLoader.init(new DrawerImageLoader.IDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx) {
                return null;
            }
        });


    }


}
