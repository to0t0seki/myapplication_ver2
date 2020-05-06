package com.example.myapplication_ver2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelA extends ViewModel {
    private MutableLiveData<Map<String,String>> liveData;
    private String url = "https://papimo.jp/h/00041817/hit/index_machine/1-20-260540/";

    public MutableLiveData<Map<String,String>> getList(){
        if(liveData==null){
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }

    public void upDateMachineList(Context context){

        new LoadTask(liveData).execute(context);
    }

    private static class LoadTask extends AsyncTask<Context, Void,Void> {
        private  MutableLiveData<Map<String,String>> mutableLiveData;
        private String url = "https://papimo.jp/h/00041817/hit/index_machine/1-20-260540/";

        LoadTask(MutableLiveData<Map<String,String>> mutableLiveData){
            this.mutableLiveData = mutableLiveData;
        }

        @Override
        protected Void doInBackground(Context... contexts) {
            try {
                DatabaseHelper databaseHelper = new DatabaseHelper(contexts[0], "slotdb3", null, 1);
                SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
                Document document = Jsoup.connect(this.url).timeout(10000).get();
                String maxpage = document.select("#max_page").val();
                for (int i = 0; i < Integer.parseInt(maxpage); i++) {
                    document = Jsoup.connect(this.url + "?page=" + String.valueOf((i + 1))).timeout(10000).get();
                    Elements elements = document.select(".item li a");
                    for (Element element : elements) {
                        int indexname = element.select(".name").text().indexOf("台") + 1;
                        int indexurl = element.attr("href").indexOf("index_sort") + 11;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("name", element.select(".name").text().substring(indexname));
                        contentValues.put("URL", element.attr("href").substring(indexurl, indexurl + 9));
                        //String s= element.attr("href").substring(indexurl,indexurl+9);
                        sqLiteDatabase.insert("minoasamachine", null, contentValues);
//                    this.machinehashmap.put(element.select(".name").text().substring(index),element.attr("href"));
                    }
                }
                Cursor cursor = queryDatabase(sqLiteDatabase);
                int indexName = cursor.getColumnIndex("name");
                int indexURL = cursor.getColumnIndex("URL");
                Map<String,String> map = new TreeMap<>();
                while (cursor.moveToNext()){
                    map.put(cursor.getString(indexName),cursor.getString(indexURL));
                }
                mutableLiveData.postValue(map);
            } catch (IOException e) {
                System.out.println("error");
            }
            return null;
        }

        public Cursor queryDatabase(SQLiteDatabase sqLiteDatabaseu){
            Cursor cursor = null;
            cursor = sqLiteDatabaseu.query("minoasamachine",
                    new String[]{"name","URL"},
                    null,
                    null,
                    null,
                    null,
                    null);
            return cursor;
        }
    }


}
