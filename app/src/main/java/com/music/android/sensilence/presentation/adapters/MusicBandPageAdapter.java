package com.music.android.sensilence.presentation.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.music.android.sensilence.R;
import com.music.android.sensilence.presentation.vidchuttiatyshi.VidchuttiaTyshiFragment;
import com.music.android.sensilence.presentation.zigmundafraid.ZigmundAfraidFragment;

/**
 * {@link MusicBandPageAdapter} is a {@link FragmentPagerAdapter} that can provide the layout for
 * each list item based on a data source which is a list of { Song} objects.
 */
public class MusicBandPageAdapter extends FragmentPagerAdapter {

    /**
     * Context of the app
     */
    private final Context aContext;

    /**
     * Create a new {@link MusicBandPageAdapter} object.
     *
     * @param fragmentManager is the fragment manager that will keep each fragment's state in the adapter
     *                        across swipes.
     */
    public MusicBandPageAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        aContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new VidchuttiaTyshiFragment();
        } else {
            return new ZigmundAfraidFragment();
        }
    }

    /**
     * Returns the total number of pages.
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return aContext.getString(R.string.category_sense_of_silence);
        } else {
            return aContext.getString(R.string.category_zigmund_afraid);
        }
    }
}

