package loise.kbc.ui.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import loise.kbc.navigationviewpagerliveo.R;

/**
 * Created by loise on 9/28/15.
 */
public class TvFragment extends Fragment {
    String TAG = "com.ebookfrenzy.videoplayer";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_video, container, false);

        final VideoView videoView = (VideoView)v.findViewById(R.id.videoView1);

        videoView.setVideoPath(
                "http://www.ebookfrenzy.com/android_book/movie.mp4");

        //adding the video controller
        MediaController mediaController = new
                MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //setting on preparational listener
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()  {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(TAG, "Duration = " +
                        videoView.getDuration());
            }
        });

        videoView.start();



        return v;
    }
}