package loise.kbc.ui.fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.LicensesDialogFragment;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.License;
import de.psdev.licensesdialog.model.Notice;
import loise.kbc.navigationviewpagerliveo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TermsandAgreement extends android.support.v4.app.Fragment{

    private AlertDialog mAlertDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=  inflater.inflate(R.layout.fragment_termsand_agreement, container, false);
        return v;
    }
    /*private void displayLicensesAlertDialog() {
        WebView view = (WebView) LayoutInflater.from(this).inflate(R.layout.fragment_termsand_agreement, null);
        view.loadUrl("file:///android_asset/open_source_licenses.html");
        mAlertDialog = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setTitle(getString(R.string.action_licenses))
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }*/

                      /*  public void onSingleClick(final View view) {
                        final String name = "LicensesDialog";
                        final String url = "http://psdev.de";
                        final String copyright = "Copyright 2013 Philip Schiffer <admin@psdev.de>";
                        final License license = new ApacheSoftwareLicense20();
                        final Notice notice = new Notice(name, url, copyright, license);
                        new LicensesDialog.Builder(this)
                        .setNotices(notice)
                        .build()
                        .show();
                        }*/


}
