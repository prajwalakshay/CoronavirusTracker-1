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
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import maa.coronavirustracker.Adapter.CoronaAdapter;
import maa.coronavirustracker.Adapter.StateAdapter;

import maa.coronavirustracker.UI.Main.IndiaAcivity;


import static maa.coronavirustracker.UI.Main.IndiaAcivity.IndiaStatescycle;
import static maa.coronavirustracker.UI.Main.IndiaAcivity.loadingstate;


public class IndiaData extends AsyncTask<Object, String, String> {
    String data = "";
    public String active, confirmed, deaths, recovred, todayscases, todaysdeaths, todaysrecovered, lasttimeupdated, states, lasttimeupdatedforstates;
    private Context context;
    public ArrayList<StateHelper> stateHelperslist = new ArrayList<>();
    StateAdapter stateAdapter;
    String day, month, year, timeinstring, monthinwords, finaldate, perfecttime, ago, curTime;
    long total;
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;
    long oldMillis, nowMillis;

    public IndiaData(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... objects) {


        try {
            URL url = new URL("https://api.covid19india.org/data.json");
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
        super.onPostExecute(s);

        IndiaStatescycle.setLayoutManager(new LinearLayoutManager(context));
        IndiaStatescycle.hasFixedSize();
        stateAdapter = new StateAdapter(context, stateHelperslist);
        IndiaStatescycle.setAdapter(stateAdapter);


        try {
            JSONObject parentObject = new JSONObject(data);
            JSONArray statewisearray = parentObject.getJSONArray("statewise");

            //Nation wise data
            for (int i = 0; i < 1; i++) {
                JSONObject jsonObject = statewisearray.getJSONObject(i);
                for (int j = 0; j < jsonObject.length(); j++) {
                    active = jsonObject.getString("active");
                    confirmed = jsonObject.getString("confirmed");
                    deaths = jsonObject.getString("deaths");
                    recovred = jsonObject.getString("recovered");
                    todayscases = jsonObject.getString("deltaconfirmed");
                    todaysdeaths = jsonObject.getString("deltadeaths");
                    todaysrecovered = jsonObject.getString("deltarecovered");
                    lasttimeupdated = jsonObject.getString("lastupdatedtime");
                    states = jsonObject.getString("state");
                }
                IndiaAcivity.confirmedcasestext.setText(String.format("%,d", (Integer.parseInt(confirmed))));
                IndiaAcivity.recoveredtext.setText(String.format("%,d", (Integer.parseInt(recovred))));
                IndiaAcivity.deathtext.setText(String.format("%,d", (Integer.parseInt(deaths))));
                if (todayscases.equals("0")) {
                    IndiaAcivity.deltaconfirmedcases.setText("");
                } else {
                    IndiaAcivity.deltaconfirmedcases.setText("[+" + todayscases + "]");
                }
                if (todaysrecovered.equals("0")) {
                    IndiaAcivity.deltarecovered.setText("");
                } else {
                    IndiaAcivity.deltarecovered.setText("[+" + todaysrecovered + "]");
                }
                if (todaysdeaths.equals("0")) {
                    IndiaAcivity.deltadeath.setText("0");
                } else {
                    IndiaAcivity.deltadeath.setText("[+" + todaysdeaths + "]");
                }
                IndiaAcivity.activecasesindia.setText("Active Cases= " + String.format("%,d", (Integer.parseInt(active))));
                getTimings();
                IndiaAcivity.uptime.setText("Updated at " + finaldate);

            }

            //State wise data

            for (int i = 1; i < statewisearray.length(); i++) {
                JSONObject jsonObject = statewisearray.getJSONObject(i);
                for (int j = 0; j < jsonObject.length(); j++) {
                    active = jsonObject.getString("active");
                    confirmed = jsonObject.getString("confirmed");
                    deaths = jsonObject.getString("deaths");
                    recovred = jsonObject.getString("recovered");
                    todayscases = jsonObject.getString("deltaconfirmed");
                    todaysdeaths = jsonObject.getString("deltadeaths");
                    todaysrecovered = jsonObject.getString("deltarecovered");
                    lasttimeupdatedforstates = jsonObject.getString("lastupdatedtime");
                    states = jsonObject.getString("state");
                    getTimeforStates();


                }
                StateHelper stateHelper = new StateHelper(states, confirmed, todayscases, active, recovred, todaysrecovered, deaths, todaysdeaths, ago);
                stateHelperslist.add(stateHelper);
            }
            if (stateHelperslist.isEmpty()) {
                loadingstate.setVisibility(View.VISIBLE);
            }
            loadingstate.setVisibility(View.GONE);
            stateAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getTimeforStates() {


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        formatter.setLenient(false);

        Date curDate = new Date();
        long curMillis = curDate.getTime();
        curTime = formatter.format(curDate);

        String oldTime = lasttimeupdatedforstates;
        Date oldDate = null;
        Date currdates = null;
        try {
            oldDate = formatter.parse(oldTime);
            curDate = formatter.parse(curTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        nowMillis = curDate.getTime();
        oldMillis = oldDate.getTime();

        total = nowMillis - oldMillis;

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
            ago = "yesterday";
        } else {
            ago = total / DAY_MILLIS + " days ago";
        }

    }

    public void getTimings() {

        day = lasttimeupdated.substring(0, 2);
        month = lasttimeupdated.substring(3, 5);
        year = lasttimeupdated.substring(6, 9);
        timeinstring = lasttimeupdated.substring(11, 19);

        if (month.equals("01")) {
            monthinwords = "Jan";
        }
        if (month.equals("02")) {
            monthinwords = "Feb";
        }
        if (month.equals("03")) {
            monthinwords = "Mar";
        }
        if (month.equals("04")) {
            monthinwords = "Apr";
        }
        if (month.equals("05")) {
            monthinwords = "May";
        }
        if (month.equals("06")) {
            monthinwords = "Jun";
        }
        if (month.equals("07")) {
            monthinwords = "Jul";
        }
        if (month.equals("08")) {
            monthinwords = "Aug";
        }
        if (month.equals("09")) {
            monthinwords = "Sep";
        }
        if (month.equals("10")) {
            monthinwords = "Oct";
        }
        if (month.equals("11")) {
            monthinwords = "Nov";
        }
        if (month.equals("12")) {
            monthinwords = "Dec";
        }

        perfecttime = timeinstring.substring(0, 5);
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            final Date dateObj;
            dateObj = sdf.parse(perfecttime);
            perfecttime = new SimpleDateFormat("hh:mm aa").format(dateObj);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        finaldate = day + " " + monthinwords + ", " + perfecttime;

    }


}
