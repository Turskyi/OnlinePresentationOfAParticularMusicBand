package com.music.android.sensilence.home;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayoutMediator;
import com.music.android.sensilence.R;
import com.music.android.sensilence.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;
import io.github.turskyi.domain.entities.enums.Band;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        //  Setting the content of the activity to use the activity_main.xml layout file
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Create an adapter that knows which fragment should be shown on each page
        MusicBandPageAdapter adapter = new MusicBandPageAdapter(
                getSupportFragmentManager(),
                getLifecycle()
        );

        // Set the adapter onto the view pager
        binding.viewPager.setAdapter(adapter);

        /* Connect the tab layout with the view pager.
         This will:
          1. Update the tab layout when the view pager is swiped
          2. Update the view pager when a tab is selected
          3. Set the tab layout's tab names with the view pager's adapter's titles */
        new TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                (tab, position) -> tab.setText(Band.values()[position].name)
        ).attach();
    }
}

