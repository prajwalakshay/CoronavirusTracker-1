package maa.coronavirustracker.Models;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import maa.coronavirustracker.Adapter.NewsAdapter;
import maa.coronavirustracker.UI.Main.NewsDialog;

public class NewsData extends AsyncTask<Object, String, String> {

    String data = " ";
    String news, timestamp, ago;
    Context context;
    long timeinmillis, nowMillis;
    public ArrayList<LogData> LogList = new ArrayList<>();

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


    public NewsData(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... objects) {

        try {
            URL url = new URL("https://api.covid19india.org/updatelog/log.json");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        NewsDialog.newscycles.setLayoutManager(new LinearLayoutManager(context));
        NewsDialog.newscycles.setHasFixedSize(false);
        NewsAdapter newsAdapter = new NewsAdapter(context, LogList);
        NewsDialog.newscycles.setAdapter(newsAdapter);
        try {

            JSONArray parent = new JSONArray(data);
            for (int i = 0; i < parent.length(); i++) {

                JSONObject logobject = parent.getJSONObject(i);

                news = logobject.getString("update");
                timestamp = logobject.getString("timestamp");

                timeinmillis = Long.parseLong(timestamp);

                getTimeForNews();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
                String NewsOfTheDay = dateFormat.format(timeinmillis);
                String TodayDate = dateFormat.format(nowMillis);

                if (TodayDate.equals(NewsOfTheDay)) {
                    LogData logData = new LogData(news, ago);
                    LogList.add(logData);
                    NewsDialog.NewBar.setVisibility(View.GONE);
                    NewsDialog.NewsNoData.setVisibility(View.GONE);
                }
            }


            if(LogList.isEmpty()){
                NewsDialog.NewBar.setVisibility(View.GONE);
                NewsDialog.NewsNoData.setVisibility(View.VISIBLE);
            }

            Collections.reverse(LogList);
            newsAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("error:::::::::" + e.getMessage());
        }

        super.onPostExecute(s);
    }

    private void getTimeForNews() {

        nowMillis = System.currentTimeMillis();
        timeinmillis = timeinmillis * 1000;
        Long total = nowMillis - timeinmillis;

        if (total < MINUTE_MILLIS) {
            ago = "just now";
        } else if (total < 2 * MINUTE_MILLIS) {
            ago = "a minute ago";
        } else if (total < 50 * MINUTE_MILLIS) {
            ago = total / MINUTE_MILLIS + " minutes ago";
        } else if (total < 90 * MINUTE_MILLIS) {
            ago = "an hour ago";
        } else if (total < 24 * HOUR_MILLIS) {
            ago = total / HOUR_MILLIS + " hours ago";
        } else if (total < 48 * HOUR_MILLIS) {
            ago = "1 day ago";
        } else {
            ago = total / DAY_MILLIS + " days ago";
        }
    }

}
