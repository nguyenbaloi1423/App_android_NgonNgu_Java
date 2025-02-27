package com.example.asmandroidnangcaops22938;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.asmandroidnangcaops22938.helper.XMLDOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;

public class TinTucActivity extends AppCompatActivity {
    
    private ArrayList<String> arrTitle, arrLink;
    private ArrayAdapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);

        ListView lstTieuDe = findViewById(R.id.listViewNews);
        progressBar = findViewById(R.id.progressBar);

        arrLink = new ArrayList<>();
        arrTitle = new ArrayList<>();

        progressBar.setVisibility(View.VISIBLE);
        new ReadRSS().execute("https://thanhnien.vn/rss/video/thoi-su-333.rss");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrTitle);
        lstTieuDe.setAdapter(adapter);

        lstTieuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TinTucActivity.this,WedviewActivity.class);
                intent.putExtra("linkNews",arrLink.get(i));
                startActivity(intent);
            }
        });
    }

    private class ReadRSS extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try{
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);

            NodeList nodeList = document.getElementsByTagName("item");

            String title = "";
            for(int i = 0; i<nodeList.getLength();i++){
                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element,"title");
                arrTitle.add(title);
                arrLink.add(parser.getValue(element,"link"));
            }
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        }
    }
}