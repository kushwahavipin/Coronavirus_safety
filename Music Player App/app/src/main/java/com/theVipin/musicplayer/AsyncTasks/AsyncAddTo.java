package com.theVipin.musicplayer.AsyncTasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.theVipin.musicplayer.Common;
import com.theVipin.musicplayer.Models.Song;
import com.theVipin.musicplayer.R;
import com.theVipin.musicplayer.Utils.PreferencesHelper;

import java.util.ArrayList;


public class AsyncAddTo extends AsyncTask<Void, Void, Boolean> {

    private boolean mAddToQueue = false;
    private ArrayList<Song> mSongs;
    private Song song;
    private Common mApp;
    private String mName;

    public AsyncAddTo(String name, boolean addToQueue, Song song) {
        mAddToQueue = addToQueue;
        this.song = song;
        mName = name;

    }


    public AsyncAddTo(String name, boolean addToQueue, ArrayList<Song> songs) {
        mAddToQueue = addToQueue;
        mSongs = songs;
        mName = name;
    }


    @Override
    protected Boolean doInBackground(Void... params) {
        mApp = (Common) Common.getInstance().getApplicationContext();
        if (mApp.isServiceRunning()) {
            if (mAddToQueue) {
                if (song != null) {
                    mApp.getService().getSongList().add(song);
                } else {
                    mApp.getService().getSongList().addAll(mSongs);
                }
            } else {
                if (song != null) {
                    mApp.getService().getSongList().add(mApp.getService().getCurrentSongIndex() + 1, song);
                } else {
                    mApp.getService().getSongList().addAll(mApp.getService().getCurrentSongIndex() + 1, mSongs);
                }
            }
        } else {
            ArrayList<Song> songs = mApp.getDBAccessHelper().getQueue();
            int pos = PreferencesHelper.getInstance().getInt(PreferencesHelper.Key.CURRENT_SONG_POSITION, 0);
            if (songs.size() == 0) {
                return false;
            }
            if (mAddToQueue) {
                if (song != null) {
                    songs.add(song);
                } else {
                    songs.addAll(mSongs);
                }
            } else {
                if (song != null) {
                    songs.add(pos + 1, song);
                } else {
                    songs.addAll(pos + 1, mSongs);
                }
            }
            mApp.getDBAccessHelper().saveQueue(songs);
        }
        return true;
    }


    @Override
    protected void onPostExecute(Boolean aVoid) {
        super.onPostExecute(aVoid);
        if (aVoid) {
            if (mAddToQueue) {
                String message = Common.getInstance().getString(R.string.added_to_queue);
                message = mName + " " + message;
                Toast.makeText(Common.getInstance(), message, Toast.LENGTH_SHORT).show();
            } else {
                String message = Common.getInstance().getString(R.string.will_be_played_next);
                message = mName + " " + message;
                Toast.makeText(Common.getInstance(), message, Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(Common.getInstance(), R.string.queue_is_empty, Toast.LENGTH_SHORT).show();
        }
    }
}
