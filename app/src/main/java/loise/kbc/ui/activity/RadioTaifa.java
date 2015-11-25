package loise.kbc.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.ivbaranov.mli.MaterialLetterIcon;

import loise.kbc.adapter.CustomList;
import loise.kbc.navigationviewpagerliveo.R;

public class RadioTaifa extends AppCompatActivity {

    ListView list;
    String[] web = {
            "Listen Live",
            "Blog",
            "Presenters Profile",
            "Item 4"
    } ;
    Integer[] imageId = {
            R.mipmap.listen,
            R.mipmap.blog,
            R.drawable.ic_action_stat_reply,
            R.drawable.ic_action_stat_reply

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_taifa);




            CustomList adapter = new
                    CustomList(RadioTaifa.this, web, imageId);
            list=(ListView)findViewById(R.id.list);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    switch (position){
                        case 0:
                            startActivity(new Intent(getApplicationContext(), EnglishServiceRadio.class));
                            break;
                    }
                    Toast.makeText(RadioTaifa.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();

                }
            });

        }

    }