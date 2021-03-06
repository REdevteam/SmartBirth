package com.futech.smartbirth;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ActionBar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {



        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    toolbar.setTitle(R.string.title_home);

                    //mTextMessage.setText(R.string.title_home);

                    return true;
                case R.id.navigation_news:

                    fragment = new BeritaFragment();
                    loadFragment(fragment);


                    toolbar.setTitle(R.string.title_news);
                    return true;

                case R.id.navigation_profile:


                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    toolbar.setTitle(R.string.title_profile);

                    return true;

                case R.id.navigation_stats:

                    fragment = new PerkembanganFragment();
                    loadFragment(fragment);
                    toolbar.setTitle(R.string.title_stats);
                    return true;

                case R.id.navigation_contact:

                    fragment = new KontakFragment();
                    loadFragment(fragment);
                    toolbar.setTitle(R.string.title_contact);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new HomeFragment());
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

}
