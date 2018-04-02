package com.a5am.team.buglobalmusicfestival;

import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by sophiahon on 4/1/18.
 */

public class PersonalPlanner extends BaseNavActivity {
    //Boolean checked =

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_planner);

        Bundle bundle = getIntent().getExtras();
        int event = bundle.getInt("event");
        Toast.makeText(PersonalPlanner.this,event,Toast.LENGTH_LONG).show();

    }

}
