package loise.kbc.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import loise.kbc.adapter.CustomListViewAdapter;
import loise.kbc.adapter.Data;
import loise.kbc.adapter.ProductsData;
import loise.kbc.navigationviewpagerliveo.R;

public class MoreNews extends AppCompatActivity {

    ListView listView;
    List<ProductsData> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_news);

        rowItems = new ArrayList<ProductsData>();
        for (int i = 0; i < Data.drawableArray.length; i++) {
            rowItems.add(new ProductsData(
                    Data.nameArray[i],
                    Data.descriptionArray[i],
                    Data.moredesc[i],
                    Data.priceArray[i],
                    Data.drawableArray[i],
                    Data.id_[i]

            ));
            listView = (ListView) findViewById(R.id.list);
            CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                    R.layout.morenews_items, rowItems);
            listView.setAdapter(adapter);
           // listView.setOnItemClickListener(this);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_more_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
