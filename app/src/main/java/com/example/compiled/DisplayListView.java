package com.example.compiled;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string;
    String input_name;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        listView=(ListView)findViewById(R.id.listview);


        contactAdapter=new ContactAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);

        json_string=getIntent().getExtras().getString("json_data");
        input_name=getIntent().getExtras().getString("name_input");


        try{
            jsonObject =new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;
            String name,age,height,weight,data;


          /*
            String input_name="manz";

            String input_name;



           */



            while(count<jsonArray.length())
            {
                JSONObject JO=jsonArray.getJSONObject(count);


                name=JO.getString("name");

                if (name.equals(input_name))
                {
                    age=JO.getString("age");
                    height=JO.getString("height");
                    weight=JO.getString("weight");
                    data=JO.getString("data");
                    Contacts contacts=new Contacts(name,age,height,weight,data);
                    contactAdapter.add(contacts);
                }
                count++;




            }



        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
