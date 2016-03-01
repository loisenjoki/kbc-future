package loise.kbc.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import br.liveo.Model.HelpLiveo;
import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.navigationliveo.NavigationLiveo;
import loise.kbc.navigationviewpagerliveo.R;
import loise.kbc.ui.fragment.AdvertismentFragment;
import loise.kbc.ui.fragment.ContactsFragment;
import loise.kbc.ui.fragment.MainFragment;
import loise.kbc.ui.fragment.ViewPagerFragment1;
import loise.kbc.wordpressrreader.app.TabLayoutFragment;

public class MainActivity extends NavigationLiveo implements OnItemClickListener {

    private HelpLiveo mHelpLiveo;

    @Override
    public void onInt(Bundle bundle) {

        // User Information
       // this.userName.setText("KBC");
        //quitthis.userEmail.setText("Your Channel One TV");
        //this.userPhoto.setImageResource(R.drawable.kbclogo);
        this.userBackground.setImageResource(R.drawable.kbcover);

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.addSubHeader("Stream");
        mHelpLiveo.add(getString(R.string.inbox), R.mipmap.tv);
        mHelpLiveo.addSubHeader("News"); //Item subHeader
        // mHelpLiveo.add(getString(R.string.starred), R.mipmap.listen);
        //mHelpLiveo.add(getString(R.string.sent_mail), R.mipmap.news);
        //

        //mHelpLiveo.addSubHeader(getString(R.string.categories)); //Item subHeader

        mHelpLiveo.add(getString(R.string.local), R.mipmap.news);
        //mHelpLiveo.add(getString(R.string.inter), R.mipmap.news);
        //  mHelpLiveo.add(getString(R.string.business), R.mipmap.news);
        //mHelpLiveo.add(getString(R.string.sports), R.mipmap.news);
        //mHelpLiveo.add(getString(R.string.tech), R.mipmap.news);
        // mHelpLiveo.add(getString(R.string.lifestlye), R.mipmap.news);
        mHelpLiveo.addSubHeader(getString(R.string.Listen)); //Item subHeader
        // mHelpLiveo.addSeparator(); // Item separator

        mHelpLiveo.add(getString(R.string.RadioTaifa),R.mipmap.radiotaifalogo);
        mHelpLiveo.add(getString(R.string.Englishservice),R.mipmap.englishlogo);
        mHelpLiveo.add(getString(R.string.Iftiin),R.mipmap.ifitiniii);
        mHelpLiveo.add(getString(R.string.Mayienga), R.mipmap.maiye);
        mHelpLiveo.add(getString(R.string.Pwani), R.mipmap.pwani);
        mHelpLiveo.add(getString(R.string.Coro), R.mipmap.coroo);

        mHelpLiveo.addSubHeader(getString(R.string.Connect)); //Item subHeader
        mHelpLiveo.add(getString(R.string.Twitter),R.drawable.twitter);
        mHelpLiveo.add(getString(R.string.Instagram), R.drawable.instagram);
        mHelpLiveo.add(getString(R.string.Facebook), R.drawable.facebook);
        mHelpLiveo.add(getString(R.string.drafts), R.mipmap.conta);
        mHelpLiveo.add(getString(R.string.trash), R.mipmap.ad);

        mHelpLiveo.add(getString(R.string.sharing), R.mipmap.sharing);

        mHelpLiveo.add(getString(R.string.Rate), R.mipmap.rate);





        with(this).startingPosition( 1 ) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())
                .colorNameSubHeader(R.color.nliveo_blue_colorPrimary)
                .colorItemSelected(R.color.nliveo_blue_colorPrimary)
                .footerItem(R.string.settings, R.mipmap.ic_settings_black_24dp)
                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                .build();

        int position = this.getCurrentPosition();
        this.setElevationToolBar(position != 2 ? 15 : 0);
    }

    @Override
    public void onItemClick(int position) {
        Fragment mFragment = null;
        FragmentManager mFragmentManager = getSupportFragmentManager();

        switch (position) {
            case 1:
                mFragment = new ViewPagerFragment1();
                break;
            case 3:
                Intent intent = new Intent(getApplicationContext(), loise.kbc.wordpressrreader.app.MainActivity.class);
                startActivity(intent);
                break;
           /* case 3:
                mFragment = new TabLayoutFragment();
                break;*/
            case 5:
                mFragment = new RadioTaifa();
                break;
            case 6:
                mFragment = new EnglishServiceRadio();
                break;
            case 7:
                mFragment = new IftiinRadio();
                break;
            case 8:
                mFragment = new Mayienga();
                break;
            case 9:
                mFragment = new PwaniRadio();
                break;
            case 10:
                mFragment = new CoroRadio();
                break;
            case 12:
               mFragment =new TimelineKbc();
                break;
            case  13:
                Uri uri1 = Uri.parse("http://instagram.com/_u/kbckenya");
                Intent Instagram = new Intent(Intent.ACTION_VIEW, uri1);

                Instagram.setPackage("com.instagram.android");
                try {
                    startActivity(Instagram);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/kbckenya")));
                }
                break;
            case 14:
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.addCategory(Intent.CATEGORY_BROWSABLE);
                intent1.setData(Uri.parse("https://www.facebook.com/kbcchannel1/"));
                startActivity(intent1);
            case 15:
                mFragment =new ContactsFragment();
                break;
            case 16:
                mFragment=new AdvertismentFragment();
                break;

            case 17:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/search?q=bbc%20news&hl=en");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

            case 18:
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
                     try {
                    startActivity(myAppLinkToMarket);
                      } catch (ActivityNotFoundException e) {

                    Toast.makeText(this, "Sorry not able to open!", Toast.LENGTH_SHORT).show();

                     }
               break;



            default:
                mFragment = MainFragment.newInstance(mHelpLiveo.get(position).getName());
                break;
        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }

            setElevationToolBar(position != 2 ? 15 : 0);
        }


    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=true;
            }
        }, 3000);
    }
}

