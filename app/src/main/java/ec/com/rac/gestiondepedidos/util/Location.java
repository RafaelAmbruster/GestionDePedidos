package ec.com.rac.gestiondepedidos.util;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public class Location implements LocationListener {

    public static String lastLat = null;
    public static String lastLng = null;
    LocationManager locationManager = null;
    Context context;
    LocationUtilsCallBack callBack;

    public Location(Context context, LocationUtilsCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public void LoadLocation() {

        try {
            if (lastLat != null && lastLng != null) {
                callBack.onLocationFound(lastLat, lastLng);
            } else {
                locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                else
                    callBack.onGPSNotFound();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------LOCATION LISTENER---------------------
    @Override
    public void onLocationChanged(android.location.Location location) {
        lastLat = "" + location.getLatitude();
        lastLng = "" + location.getLongitude();

        try {
            if (callBack != null)
                callBack.onLocationFound(lastLat, lastLng);
        } catch (Exception e) {
            e.printStackTrace();
        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public interface LocationUtilsCallBack {
        public void onLocationFound(String lng, String lat);

        public void onGPSNotFound();
    }
}
