package com.music.android.sensilence.common.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.music.android.sensilence.R;
import io.github.turskyi.domain.entities.pojo.Song;

public class SongAdapter extends ArrayAdapter<Song> {
    /**
     * Resource ID for the background color for this list of songs
     */
    private final int colorResourceId;

    public SongAdapter(Activity context, int colorResourceId) {
        /* Here, we initialize the ArrayAdapter's internal storage for the context and the list.
         * The second argument is used when the ArrayAdapter is populating a single TextView.
         * Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
         * going to use this second argument, so it can be any value. Here, we used "0". */
        super(context, 0);
        this.colorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_list, parent, false);
        }
        // Get the {@link Song} object located at this position in the list
        Song currentSong = getItem(position);
        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView bandTextView = listItemView.findViewById(R.id.sensilence_text_view);
        /* Get the version name from the current AndroidFlavor object and
         * set this text on the name TextView */
        assert currentSong != null;
        bandTextView.setText(currentSong.getNameOfTheBand());
        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);
        /* Get the version number from the current Song object and
         * set this text on the number TextView */
        defaultTextView.setText(currentSong.getNameOfTheSong());
        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = listItemView.findViewById(R.id.image_view);
        // Set the ImageView to the image resource specified in the current song.
        imageView.setImageResource(currentSong.getImageResourceId());
        if (currentSong.hasImage()) {
            // Set the ImageView to the image resource specified in the current Song.
            imageView.setImageResource(currentSong.getImageResourceId());
        } else {
            imageView.setVisibility(View.GONE);
        }
        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), colorResourceId);
        //Set the background color of the text container View
        textContainer.setBackgroundColor(color);
        /* Returns the whole list item layout (containing 2 TextViews and an ImageView)
         * so that it can be shown in the ListView */
        return listItemView;
    }
}
