package loise.kbc.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;
import loise.kbc.navigationviewpagerliveo.R;
import loise.kbc.ui.activity.MainActivity;
import loise.kbc.ui.activity.SignUpActivity;

import static loise.kbc.navigationviewpagerliveo.R.layout.fragment_log_in;


public class LogInFragment extends android.support.v4.app.Fragment {
    private static final String TWITTER_KEY = "CtXW8KLLGFfQTqWlrdoK7oUbR";
    private static final String TWITTER_SECRET = "xq0mHEOlOpBVfr1uyvehFF1dt05YIAPxCHav0diBfr99QuBXrL";
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.

   private TwitterLoginButton loginButton;
    TextView Signup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(fragment_log_in, container, false);

       v.findViewById(R.id.txtsignup).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getActivity(), SignUpActivity.class));
          }
      });

       TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(getActivity(), new Twitter(authConfig));


       loginButton = (TwitterLoginButton) v.findViewById(R.id.login_button1);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                Twitter.getInstance().core.getSessionManager().getActiveSession();
                TwitterSession session = result.data;
                TwitterSession data = null;
                data.getUserName();
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getActivity(), data.getUserName(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void failure(TwitterException e) {
                Log.d("TwitterKit", "Login with Twitter failure");

            }




        });

            return v;


    }
   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }*/
   @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

       // Pass the activity result to the fragment, which will then pass the result to the login
       // button.
       Fragment fragment = getFragmentManager().findFragmentById(R.id.container);
       if (fragment != null) {
           fragment.onActivityResult(requestCode, resultCode, data);
           startActivity(new Intent(getActivity(), MainActivity.class));
       }

   }




    // private class LogInHandler extends Callback<TwitterSession> {



    

}
