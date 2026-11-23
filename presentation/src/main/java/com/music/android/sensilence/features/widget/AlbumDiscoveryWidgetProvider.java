package com.music.android.sensilence.features.widget;

import static com.music.android.sensilence.features.senseofsilence.SenseOfSilenceFragment.EXTRA_ALBUM;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.music.android.sensilence.R;
import com.music.android.sensilence.features.senseofsilence.SenseOfSilenceActivity;

import io.github.turskyi.domain.entities.enums.Album;

/**
 * A home-screen shortcut to the three main sense.of.silence albums.
 */
public class AlbumDiscoveryWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(
            Context context,
            AppWidgetManager appWidgetManager,
            int[] appWidgetIds
    ) {
        for (int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, createRemoteViews(context));
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (Intent.ACTION_LOCALE_CHANGED.equals(intent.getAction())) {
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            int[] widgetIds = manager.getAppWidgetIds(
                    new android.content.ComponentName(context, AlbumDiscoveryWidgetProvider.class)
            );
            onUpdate(context, manager, widgetIds);
        }
    }

    @Override
    public void onAppWidgetOptionsChanged(
            Context context,
            AppWidgetManager appWidgetManager,
            int appWidgetId,
            Bundle newOptions
    ) {
        appWidgetManager.updateAppWidget(appWidgetId, createRemoteViews(context));
    }

    private RemoteViews createRemoteViews(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_album_discovery);
        views.setOnClickPendingIntent(
                R.id.widget_album_sense_of_silence,
                albumPendingIntent(context, Album.SENSE_OF_SILENCE_LP, 1)
        );
        views.setOnClickPendingIntent(
                R.id.widget_album_crime,
                albumPendingIntent(context, Album.CRIME, 2)
        );
        views.setOnClickPendingIntent(
                R.id.widget_album_zombi,
                albumPendingIntent(context, Album.ZOMBI, 3)
        );
        return views;
    }

    private PendingIntent albumPendingIntent(Context context, Album album, int requestCode) {
        Intent intent = new Intent(context, SenseOfSilenceActivity.class)
                .putExtra(EXTRA_ALBUM, album)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return PendingIntent.getActivity(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
    }
}
