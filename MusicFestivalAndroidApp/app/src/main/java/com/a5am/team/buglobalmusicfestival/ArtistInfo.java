package com.a5am.team.buglobalmusicfestival;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ruochen on 3/31/18.
 */

public class ArtistInfo extends BaseNavActivity{
    private ListView listView_of_artists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artistinfo);
        initializeToolbar();
        listView_of_artists =(ListView) findViewById(R.id.list_of_artists);
        String[] value = new String[]{
                "Jupiter & Okwess",
                "Ladama",
                "Dina Elwedidi",
                "Orquesta El Macabeo",
                "Zhou Family",
                "Kaumakaiwa Kanaka'ole"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,
                value);

        listView_of_artists.setAdapter(adapter);

        listView_of_artists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemposition = position;
                String itemValue = (String) listView_of_artists.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Position: " + itemposition + " ListItem: " +
                itemValue, Toast.LENGTH_LONG).show();
            }
        });

    }
}
