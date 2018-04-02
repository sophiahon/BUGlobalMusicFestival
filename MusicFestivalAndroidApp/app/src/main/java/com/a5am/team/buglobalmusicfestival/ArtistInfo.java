package com.a5am.team.buglobalmusicfestival;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.a5am.team.buglobalmusicfestival.Database.Event;

import java.util.List;

/**
 * Created by ruochen on 3/31/18.
 */

public class ArtistInfo extends BaseNavActivity{

    private ListView listView_of_artists;

    private ArtistInfoAdapter listAdapter;

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
        String[] ytb_vids = new String[] {
                "UI6Dweisqj4",
                "SejIE1T55OU",
                "z3CxVE_fB4c",
                "3nSKbn3F0mY",
                "zTz1v8aJeKg",
                "_sKs8CNNO2A"
        };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,
//                value);

//        listView_of_artists.setAdapter(adapter);
        if (MainActivity.eventList != null){
            listAdapter = new ArtistInfoAdapter(this, MainActivity.eventList);
            listView_of_artists.setAdapter(listAdapter);
        }

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
