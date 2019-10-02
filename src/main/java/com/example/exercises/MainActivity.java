package com.example.exercises;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById (R.id.simpleListView);

        String[] values = new String[] { "Pillow SitUp",
                "Step Count", "Leg Flex"  };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0 ){  //Accelerometer
                    Intent intent = new Intent(MainActivity.this, PillowSitUp.class);
                    startActivity(intent);
                }
                else if (position == 1 ){  //Proximity
                    Intent intent = new Intent(MainActivity.this, StepCou.class);
                    //intent.putExtra("ListViewCl","ija"); //TempListViewClickedValue
                    startActivity(intent);
                }
                else if (position == 2 ){  //Proximity
                    Intent intent = new Intent(MainActivity.this, FlexLeg.class);
                    //intent.putExtra("ListViewCl","ija"); //TempListViewClickedValue
                    startActivity(intent);
                }

            }

        });

    }
}
