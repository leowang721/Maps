package org.zarroboogs.maps;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;


/**
 * AMapV2地图中介绍定位三种模式的使用，包括定位，追随，旋转
 */
public class MapsMainActivity extends BaseActivity implements MapsFragment.OnFragmentInteractionListener,
        LeftDrawerFragment.OnFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private DrawerStateListener mDrawerStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps_drawer);

        mDrawerStateListener = getMapsFragment();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.maps_drawer_layout);

        mDrawerLayout.openDrawer(Gravity.START);

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (mDrawerStateListener != null){
                    mDrawerStateListener.onDrawerSlide(drawerView, slideOffset);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                if (mDrawerStateListener != null){
                    mDrawerStateListener.onDrawerOpened(drawerView);
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                if (mDrawerStateListener != null){
                    mDrawerStateListener.onDrawerClosed(drawerView);
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (mDrawerStateListener != null){
                    mDrawerStateListener.onDrawerStateChanged(newState);
                }
            }
        });


        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.center_frame_layout, getMapsFragment(), MapsFragment.class.getName());
            ft.replace(R.id.left_drawer_layout, getLeftDrawerFragment(), LeftDrawerFragment.class.getName());
            ft.commit();
        }

    }


    private MapsFragment getMapsFragment() {
        MapsFragment mapsFragment = (MapsFragment) getSupportFragmentManager().findFragmentByTag(MapsFragment.class.getName());
        if (mapsFragment == null) {
            mapsFragment = new MapsFragment();
        }
        return mapsFragment;
    }

    private LeftDrawerFragment getLeftDrawerFragment() {
        LeftDrawerFragment leftDrawerFragment = (LeftDrawerFragment) getSupportFragmentManager().findFragmentByTag(LeftDrawerFragment.class.getName());
        if (leftDrawerFragment == null) {
            leftDrawerFragment = new LeftDrawerFragment();
        }
        return leftDrawerFragment;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
