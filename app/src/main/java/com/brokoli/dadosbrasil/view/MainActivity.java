package com.brokoli.dadosbrasil.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.brokoli.dadosbrasil.R;
import com.brokoli.dadosbrasil.persistence.UserPreferences;
import com.brokoli.dadosbrasil.util.ViewUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    // Interface elements
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.setHomeAsUpEnabled(this);
        ButterKnife.bind(this);
        setupDrawer();
        setupInitialFragment();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(mDrawerToggle != null) {
            // Sync the toggle state after onRestoreInstanceState has occurred.
            mDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void openDrawer(){
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @OnClick(R.id.desmatamento_amazonia_drawer_text_view)
    public void onClickDesmatamentoAmazonia(View view) {
        setTitle(R.string.desmatamento_amazonia);
        openFragment(DesmatamentoAmazoniaFragment.newInstance());
    }

    private void openFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null && !fragments.isEmpty()){
            Fragment currentFragment = fragments.get(0);
            if(currentFragment.getClass().equals(fragment.getClass())){
                // We are already showing this fragment. Just close navigation drawer.
                mDrawerLayout.closeDrawers();
                return;
            }
        }
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        mDrawerLayout.closeDrawers();
    }

    // Helper methods

    private void setupDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,               /*The toolbar to use if you have an independent Toolbar.*/
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        );

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (UserPreferences.readShouldShowDrawer()) {
            openDrawer();
            UserPreferences.writeShouldShowDrawer(false);
        }
    }
    private void setupInitialFragment() {
        onClickDesmatamentoAmazonia(null);
    }
}
