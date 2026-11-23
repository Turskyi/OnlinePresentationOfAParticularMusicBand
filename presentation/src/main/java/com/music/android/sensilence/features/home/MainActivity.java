package com.music.android.sensilence.features.home;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import com.google.android.material.tabs.TabLayoutMediator;
import com.music.android.sensilence.R;
import com.music.android.sensilence.databinding.ActivityMainBinding;
import com.music.android.sensilence.features.widget.AlbumDiscoveryWidgetProvider;

import dagger.hilt.android.AndroidEntryPoint;
import io.github.turskyi.domain.entities.enums.Band;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        Log.d("LanguageDebug", "Current app locales: " + AppCompatDelegate.getApplicationLocales());

        Log.d("LanguageDebug", "Album zombi string: " + getString(R.string.album_zombi));

        //  Setting the content of the activity to use the activity_main.xml layout file
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(
                    new NotificationChannel(
                            channelId,
                            channelName,
                            NotificationManager.IMPORTANCE_LOW
                    )
            );
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem languageItem = menu.findItem(R.id.action_language);
        if (languageItem != null) {
            LocaleListCompat locales = AppCompatDelegate.getApplicationLocales();
            if (locales.isEmpty()) {
                languageItem.setIcon(R.drawable.ic_language);
            } else {
                String tags = locales.toLanguageTags();
                if (tags.contains("uk")) {
                    languageItem.setIcon(R.drawable.ic_flag_ua);
                } else if (tags.contains("en")) {
                    languageItem.setIcon(R.drawable.ic_flag_en);
                } else {
                    languageItem.setIcon(R.drawable.ic_language);
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_language) {
            showLanguageDialog();
            return true;
        }
        if (item.getItemId() == R.id.action_pin_widget) {
            requestWidgetPin();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestWidgetPin() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AppWidgetManager appWidgetManager = getSystemService(AppWidgetManager.class);
            if (appWidgetManager != null && appWidgetManager.isRequestPinAppWidgetSupported()) {
                appWidgetManager.requestPinAppWidget(
                        new ComponentName(this, AlbumDiscoveryWidgetProvider.class),
                        null,
                        null
                );
                return;
            }
        }
        Toast.makeText(this, R.string.pin_widget_unavailable, Toast.LENGTH_LONG).show();
    }

    private void showLanguageDialog() {
        String[] languages = {
                getString(R.string.language_system),
                getString(R.string.language_ukrainian),
                getString(R.string.language_english)
        };

        LocaleListCompat locales = AppCompatDelegate.getApplicationLocales();
        int initialSelection = 0;
        if (!locales.isEmpty()) {
            String tags = locales.toLanguageTags();
            if (tags.contains("uk")) {
                initialSelection = 1;
            } else if (tags.contains("en")) {
                initialSelection = 2;
            }
        }

        final int[] chosenIndex = {initialSelection};

        new AlertDialog.Builder(this)
                .setTitle(R.string.choose_language)
                .setSingleChoiceItems(languages, initialSelection, (dialog, which) -> chosenIndex[0] = which)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    if (chosenIndex[0] == 0) {
                        AppCompatDelegate.setApplicationLocales(LocaleListCompat.getEmptyLocaleList());
                    } else if (chosenIndex[0] == 1) {
                        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("uk"));
                    } else if (chosenIndex[0] == 2) {
                        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"));
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }
}
