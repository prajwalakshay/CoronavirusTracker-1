package maa.coronavirustracker.UI.Main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import maa.coronavirustracker.Adapter.StateAdapter;
import maa.coronavirustracker.Data.ResourcesClient;
import maa.coronavirustracker.Models.IndiaData;
import maa.coronavirustracker.Models.NewsData;
import maa.coronavirustracker.Models.ServiceData;
import maa.coronavirustracker.Models.StateHelper;
import maa.coronavirustracker.R;

public class IndiaAcivity extends AppCompatActivity {

    CardView worldcardchange;
    public static TextView confirmedcasestext, recoveredtext, deltaconfirmedcases, deltarecovered, deathtext, deltadeath, activecasesindia, uptime;
    LinearLayout linearLayout;
    public static RecyclerView IndiaStatescycle;
    public static ProgressBar loadingstate;
    public androidx.appcompat.widget.SearchView states;
    MaterialToolbar materialtoolbarIndia;
RelativeLayout NointernetStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india_acivity);

        states = findViewById(R.id.searchState);
        uptime = findViewById(R.id.updatetime);
        activecasesindia = findViewById(R.id.arrowactive);
        deathtext = findViewById(R.id.DeathsIndia);
        deltadeath = findViewById(R.id.arrowdeath);
        recoveredtext = findViewById(R.id.RecoveredIndia);
        deltaconfirmedcases = findViewById(R.id.arrowconfirmed);
        deltarecovered = findViewById(R.id.arrowrecovered);
        confirmedcasestext = findViewById(R.id.ConfirmedIndia);
        worldcardchange = findViewById(R.id.cardWorld_india);
        linearLayout = findViewById(R.id.Status);
        IndiaStatescycle = findViewById(R.id.RecyclerViewDataIndia);
        loadingstate = findViewById(R.id.StateLoading);
        materialtoolbarIndia = findViewById(R.id.toolbarIndia);
        NointernetStateView = findViewById(R.id.noInternetStateLayout);

        Toast.makeText(IndiaAcivity.this, "Always wash your hands!!", Toast.LENGTH_SHORT).show();

        isOnlinestate();

        materialtoolbarIndia.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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
                        NewsDialog newsDialog = new NewsDialog(IndiaAcivity.this);
                        newsDialog.getNews();
                        break;
                    case R.id.helpingHand:
                        ServiceDialog serviceDialog = new ServiceDialog(IndiaAcivity.this);
                       serviceDialog.getServiceDetails();
                       // Toast.makeText(IndiaAcivity.this, "Service center details coming soon", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });


        worldcardchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent worldintent = new Intent(IndiaAcivity.this, MainActivity.class);
                worldintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(worldintent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        IndiaData indiaData = new IndiaData(IndiaAcivity.this);
        indiaData.execute();
        displayViews(true);

        states.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<StateHelper> mysearchlist = new ArrayList<>();
                for (StateHelper compDatalist : indiaData.stateHelperslist) {
                    if (compDatalist.getStateNames().toLowerCase().startsWith(newText.toLowerCase())) {
                        mysearchlist.add(compDatalist);
                    }
                }
                StateAdapter adapter = new StateAdapter(IndiaAcivity.this, mysearchlist);
                IndiaStatescycle.setAdapter(adapter);

                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Do you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void displayViews(boolean visibility) {
        if (visibility == true) {
            linearLayout.setVisibility(View.VISIBLE);

        } else {
            linearLayout.setVisibility(View.GONE);

        }
    }

    public void isOnlinestate() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
           NointernetStateView.setVisibility(View.GONE);
        } else {
            IndiaStatescycle.setVisibility(View.GONE);
            NointernetStateView.setVisibility(View.VISIBLE);

        }
    }
}
