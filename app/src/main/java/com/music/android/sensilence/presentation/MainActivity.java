package com.music.android.sensilence.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.music.android.sensilence.R;
import com.music.android.sensilence.presentation.common.adapters.MusicBandPageAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Setting the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager2 viewPager = findViewById(R.id.view_pager);

        // Create an adapter that knows which fragment should be shown on each page
        MusicBandPageAdapter adapter = new MusicBandPageAdapter(getSupportFragmentManager(), getLifecycle());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Find the tab layout that shows the tabs
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        /* Connect the tab layout with the view pager.
         This will:
          1. Update the tab layout when the view pager is swiped
          2. Update the view pager when a tab is selected
          3. Set the tab layout's tab names with the view pager's adapter's titles */
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText(getString(R.string.band_sense_of_silence));
            } else {
                tab.setText(getString(R.string.band_zigmund_afraid));
            }
        }).attach();
    }
}

