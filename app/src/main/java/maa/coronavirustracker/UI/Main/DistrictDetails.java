package maa.coronavirustracker.UI.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import maa.coronavirustracker.Adapter.DistrictAdapter;
import maa.coronavirustracker.Models.DistrictData;
import maa.coronavirustracker.Models.DistrictHelper;
import maa.coronavirustracker.R;

public class DistrictDetails extends AppCompatActivity {

    public static String STATE_NAME = "";

    TextView DisConfirmed, DisDeltaConfirmed, DisRecovered, DisDeltaRecovered, DisDeath, DisDeltaDeaths, DisTIme;
    MaterialToolbar DisToolbar;
    public static RecyclerView DisCycle;
    public static ProgressBar DisBar;
    public static TextView DisnoData;
    public androidx.appcompat.widget.SearchView Dissearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_details);

        DisConfirmed = findViewById(R.id.ConfirmedStates);
        DisDeltaConfirmed = findViewById(R.id.arrowconfirmedStates);
        DisRecovered = findViewById(R.id.RecoveredStates);
        DisDeltaRecovered = findViewById(R.id.arrowrecoveredStates);
        DisDeath = findViewById(R.id.DeathsStates);
        DisDeltaDeaths = findViewById(R.id.arrowdeathStates);
        DisTIme = findViewById(R.id.updatetimeStates);
        DisToolbar = findViewById(R.id.toolbarDis);
        DisCycle = findViewById(R.id.RecyclerViewDataStates);
        DisBar = findViewById(R.id.DisLoading);
        DisnoData = findViewById(R.id.NoUpdates);
        Dissearch = findViewById(R.id.searchDis);


        Bundle mbundle = getIntent().getExtras();
        if (mbundle != null) {
            STATE_NAME = mbundle.getString("STATENAME");
            DisToolbar.setTitle(mbundle.getString("STATENAME"));
            DisConfirmed.setText(mbundle.getString("STATECONFIRMCASES"));
            String todaycases = mbundle.getString("STATEDELTACONFIRMCASES");
            if (todaycases.equals("0")) {
                DisDeltaConfirmed.setText("");
            } else {
                DisDeltaConfirmed.setText("[" + mbundle.getString("STATEDELTACONFIRMCASES") + "]");
            }
            DisRecovered.setText(mbundle.getString("STATERECOVEREDASES"));
            String todayrecovered = mbundle.getString("STATEDELTARECOVEREDCASES");
            if (todayrecovered.equals("0")) {
                DisDeltaRecovered.setText("");
            } else {
                DisDeltaRecovered.setText("[" + mbundle.getString("STATEDELTARECOVEREDCASES") + "]");
            }
            DisDeath.setText(mbundle.getString("STATEDEATHCASES"));

            String todaysdeath = mbundle.getString("STATEDELTADEATHCASES");
            if (todaysdeath.equals("0")) {
                DisDeltaDeaths.setText("");
            } else {
                DisDeltaDeaths.setText("[" + mbundle.getString("STATEDELTADEATHCASES" + "]"));
            }
            DisTIme.setText("Last Updated " + mbundle.getString("TIMEY"));

        }

        DistrictData districtData = new DistrictData(DistrictDetails.this, STATE_NAME);
        districtData.execute();


        Dissearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<DistrictHelper> mysearchlist = new ArrayList<>();
                for (DistrictHelper compDatalist : districtData.districtHelperArrayList) {
                    if (compDatalist.getDistrictName().toLowerCase().startsWith(newText.toLowerCase())) {
                        mysearchlist.add(compDatalist);
                    }
                }
                DistrictAdapter adapter = new DistrictAdapter(DistrictDetails.this, mysearchlist);
                DisCycle.setAdapter(adapter);
                return false;
            }
        });


        DisToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        DisToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.emailme:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        String[] recipients = {"utsavdevadiga10@gmail.com"};
                        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                        intent.putExtra(Intent.EXTRA_SUBJECT, "CoVID-19");
                        intent.setType("text/html");
                        intent.setPackage("com.google.android.gm");
                        startActivity(Intent.createChooser(intent, "Send mail"));
                        break;
                    case R.id.notification:
                        NewsDialog newsDialog = new NewsDialog(DistrictDetails.this);
                        newsDialog.getNews();
                        break;
                    case R.id.helpingHand:
                        ServiceDialog serviceDialog = new ServiceDialog(DistrictDetails.this);
                        serviceDialog.getServiceDetails();
                        break;
                }
                return false;
            }
        });




    }
}
