package com.music.android.sensilence.common.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import com.music.android.sensilence.vidchuttiatyshi.VidchuttiaTyshiFragment;
import com.music.android.sensilence.zigmundafraid.ZigmundAfraidFragment;

/**
 * {@link MusicBandPageAdapter} is a {@link androidx.viewpager2.adapter.FragmentStateAdapter} that can provide the layout for
 * each list item based on a data source which is a list of { Song} objects.
 */
public class MusicBandPageAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {

    /**
     * Create a new {@link MusicBandPageAdapter} object.
     *
     * @param fragmentManager is the fragment manager that will keep each fragment's state in the adapter
     *                        across swipes.
     */
    public MusicBandPageAdapter(
            @NonNull FragmentManager fragmentManager,
            @NonNull Lifecycle lifecycle
    ) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
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
    public int getItemCount() {
        return 2;
    }
}

