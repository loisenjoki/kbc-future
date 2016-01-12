package loise.kbc.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import loise.kbc.navigationviewpagerliveo.R;

public class InstagramFeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_feed);

        Uri uri = Uri.parse("http://instagram.com/_u/kbckenya");
        Intent Instagram = new Intent(Intent.ACTION_VIEW, uri);

        Instagram.setPackage("com.instagram.android");

        try {
            startActivity(Instagram);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/kbckenya")));
        }
    }
}
