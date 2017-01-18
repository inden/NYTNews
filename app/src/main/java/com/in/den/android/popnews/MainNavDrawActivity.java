package com.in.den.android.popnews;

import android.os.Bundle;
import android.os.Handler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainNavDrawActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Handler  mHandler = new Handler();

    private static final String TAG_MOVIE="movie";
    private static final String TAG_NEWS="news";
    private static String CURRENT_TAG=TAG_NEWS;
    private static final String SECTION_NEWS_ARTS = "arts";
    private static final String SECTION_NEWS_WORLD = "world";
    private static final String SECTION_NEWS_ALL = "all-sections";
    private static String CURRENT_SECTION = SECTION_NEWS_ALL;
    private static String PREVIOUS_SECTION = SECTION_NEWS_ALL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav_draw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadFragment();
    }

    private void setActionBarTitle() {
        String title;
        if(CURRENT_TAG == TAG_NEWS) {
            title = "POPULAR NEWS    -   " + CURRENT_SECTION;
        }
        else {
            title = "MOVIE";
        }

        getSupportActionBar().setTitle(title);

    }

    private void loadFragment() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(CURRENT_TAG);

        if ( currentFragment!= null) {

            if(CURRENT_TAG == TAG_NEWS && PREVIOUS_SECTION != CURRENT_SECTION) {
                setActionBarTitle();

                NewsFragment newsFragment = (NewsFragment)currentFragment;
                newsFragment.setSection(CURRENT_SECTION);
                newsFragment.fetchNews();
            }
        }
        else {
            setActionBarTitle();

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // update the main content by replacing fragments
                    Fragment fragment = getSelectedFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                    fragmentTransaction.commitAllowingStateLoss();
                }
            });

        }

        drawer.closeDrawer(GravityCompat.START);
    }

    private Fragment getSelectedFragment() {
        if(CURRENT_TAG == TAG_MOVIE) {
            MovieFragment mvfragment = new MovieFragment();
            mvfragment.fetchMovie();
            return mvfragment;
        }
        else {
            NewsFragment newsfragment = new NewsFragment();
            newsfragment.setSection(CURRENT_SECTION);
            newsfragment.fetchNews();
            return newsfragment;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        PREVIOUS_SECTION = CURRENT_SECTION;

        if (id == R.id.news_all) {
            CURRENT_TAG = TAG_NEWS;
            CURRENT_SECTION = SECTION_NEWS_ALL;

        } else if (id == R.id.news_world) {
            CURRENT_TAG = TAG_NEWS;
            CURRENT_SECTION = SECTION_NEWS_WORLD;

        } else if (id == R.id.news_arts) {
            CURRENT_TAG = TAG_NEWS;
            CURRENT_SECTION = SECTION_NEWS_ARTS;
        }
        else if(id == R.id.movie_all) {
            CURRENT_TAG = TAG_MOVIE;

        }

        loadFragment();

        item.setChecked(true);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
