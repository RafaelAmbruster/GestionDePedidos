package ec.com.rac.gestiondepedidos.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.mikepenz.materialdrawer.Drawer;
import ec.com.rac.gestiondepedidos.R;
import ec.com.rac.gestiondepedidos.view.fragment.CoreFragment;

public class AppFragmentActivity extends AppCompatActivity {

    private Drawer result = null;
    private CoreFragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public void addFragment(CoreFragment fragment) {

        if (activeFragment != null)
            activeFragment.onPause();

        activeFragment = fragment;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentLayout, fragment)
                .commitAllowingStateLoss();
    }

}
