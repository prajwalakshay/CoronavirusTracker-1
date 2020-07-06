package maa.coronavirustracker.Models;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import maa.coronavirustracker.Adapter.DistrictAdapter;
import maa.coronavirustracker.UI.Main.DistrictDetails;

public class DistrictData extends AsyncTask<Object, String, String> {

    String data = "";
    Context context;
    String StateNames;
    public ArrayList<DistrictHelper> districtHelperArrayList = new ArrayList<>();

    public DistrictData(Context context, String StateNames) {
        this.context = context;
        this.StateNames = StateNames;
    }

    @Override
    protected String doInBackground(Object... objects) {

        try {
            URL url = new URL("https://api.covid19india.org/v2/state_district_wise.json");
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
                DistrictDetails.DisCycle.setLayoutManager(gridLayoutManager);
        DistrictAdapter districtAdapter = new DistrictAdapter(context, districtHelperArrayList);
        DistrictDetails.DisCycle.setAdapter(districtAdapter);
        try {
            JSONArray parentArray = new JSONArray(data);
            for (int i = 0; i < parentArray.length(); i++) {
                JSONObject statedata = parentArray.getJSONObject(i);

                if (StateNames.equals(statedata.getString("state"))) {
                    String StateNameFromJSON = statedata.getString("state");
                    JSONArray districtArray = statedata.getJSONArray("districtData");
                    for (int j = 0; j < districtArray.length(); j++) {
                        JSONObject districtname = districtArray.getJSONObject(j);

                        String DisName = districtname.getString("district");
                        String DisActive = districtname.getString("active");
                        String DisConfirmed = districtname.getString("confirmed");
                        String DisDeceased = districtname.getString("deceased");
                        String DisRecovered = districtname.getString("recovered");

                        JSONObject deltas = districtname.getJSONObject("delta");
                        String DisDeltaConfirmed = deltas.getString("confirmed");
                        String DisDeltaDeceased = deltas.getString("deceased");
                        String DisDeltaRecovered = deltas.getString("recovered");
                        DistrictHelper districtHelper = new DistrictHelper(DisName, DisActive, DisConfirmed, DisDeceased, DisRecovered, DisDeltaConfirmed, DisDeltaDeceased, DisDeltaRecovered);
                        districtHelperArrayList.add(districtHelper);


                     /*   Toast.makeText(context, "State::::" + StateNameFromJSON + "\n" +
                                "District:::: " + DisName + "\n" +
                                "Active:::: " + DisActive + "\n" +
                                "Confirmed:::: " + DisConfirmed + "\n" +
                                "Deaths:::: " + DisDeceased + "\n" +
                                "Recovered:::: " + DisRecovered + "\n" +
                                "Confirmed Today:::: " + DisDeltaConfirmed + "\n" +
                                "Deaths Today:::: " + DisDeltaDeceased + "\n" +
                                "Recovered Today:::: " + DisDeltaRecovered, Toast.LENGTH_SHORT).show();
*/
                    }
                }
            }
            if(districtHelperArrayList.isEmpty()){
                DistrictDetails.DisnoData.setVisibility(View.VISIBLE);
            }
            districtAdapter.notifyDataSetChanged();
            DistrictDetails.DisBar.setVisibility(View.GONE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
