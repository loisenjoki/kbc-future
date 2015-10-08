package loise.liveo.ui.activity;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.models.User;

import retrofit.http.GET;
import retrofit.http.Query;



/**
 * Created by muli on 9/30/2015.
 */
public class MyTwitterApiClient extends TwitterApiClient {
    public MyTwitterApiClient(Session session) {
        super(session);
    }
    public CustomService getCustomService() {
        return getService(CustomService.class);
    }

}
interface CustomService {
    @GET("/1.1/users/show.json")
    void show(@Query("muli_faith") long id, Callback<User> cb);
}
