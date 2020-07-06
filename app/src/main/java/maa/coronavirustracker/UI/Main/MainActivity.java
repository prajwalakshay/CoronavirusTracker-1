package maa.coronavirustracker.UI.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.appbar.MaterialToolbar;

import org.w3c.dom.Text;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import maa.coronavirustracker.Adapter.CoronaAdapter;
import maa.coronavirustracker.Models.Complete;

import maa.coronavirustracker.Models.IndiaData;
import maa.coronavirustracker.Models.NewsData;
import maa.coronavirustracker.R;
import maa.coronavirustracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    RecyclerView.LayoutManager mLayout;
    CoronaAdapter mCoronaAdapter;
    private ArrayList<Complete> mDataList = new ArrayList<>();

    CoronaViewModel mCoronaViewModel;
    ActivityMainBinding mainBinding;
    androidx.appcompat.widget.SearchView countries;
    CardView changecard;
    public static TextView activecasesinindia, worldactivecases;
    MaterialToolbar googletoolbar;
    RelativeLayout noInternetView;
    ProgressBar LoadingBar;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCoronaViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CoronaViewModel.class);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        countries = findViewById(R.id.searchcountry);
        changecard = findViewById(R.id.cardIndia_world);
        activecasesinindia = findViewById(R.id.arrowactiveworldIndia);
        worldactivecases = findViewById(R.id.arrowactivecaseworld);
        googletoolbar = findViewById(R.id.toolbar);
        noInternetView = findViewById(R.id.internetLayout);
        LoadingBar = findViewById(R.id.Loading);
        isOnline();

        googletoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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
                        NewsDialog newsDialog = new NewsDialog(MainActivity.this);
                        newsDialog.getWorldNews();
                        break;
                    case R.id.helpingHand:
                        ServiceDialog serviceDialog = new ServiceDialog(MainActivity.this);
                        serviceDialog.getWorldServiceDetails();
                        break;
                }
                return false;
            }
        });


        changecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent indiaintent = new Intent(MainActivity.this, IndiaAcivity.class);
                indiaintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(indiaintent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        setupRecyclerView(mainBinding.RecyclerViewData);
        mCoronaViewModel.getCoronaResumeInformation();
        mCoronaViewModel.mutableResumeLiveData.observe(this, resume -> {
            displayViews(true);
            mainBinding.Confirmed.setText(String.format("%,d", (Integer.parseInt(resume.getCases()))));
            mainBinding.Recovered.setText(String.format("%,d", (Integer.parseInt(resume.getRecovered()))));
            mainBinding.Deaths.setText(String.format("%,d", (Integer.parseInt(resume.getDeaths()))));
            mainBinding.arrowcasesworld.setText("[+" + String.format("%,d", (Integer.parseInt(resume.getTodayCases()))) + "]");
            mainBinding.arrowdeathworld.setText("[+" + String.format("%,d", (Integer.parseInt(resume.getTodayDeaths()))) + "]");
            mainBinding.arrowactivecaseworld.setText("Active= " + String.format("%,d", (Integer.parseInt(resume.getActive()))));
        });
        mCoronaViewModel.getCoronaCompleteInformation();
        mCoronaViewModel.mutableCompleteLiveData.observe(this, completes -> {
            mDataList = new ArrayList<>(completes);

            mCoronaAdapter = new CoronaAdapter(getApplicationContext(), mDataList, position -> sendToDetails(mDataList.get(position).getCountry(),
                    mDataList.get(position).getCases(),
                    mDataList.get(position).getTodayCases(),
                    mDataList.get(position).getDeaths(),
                    mDataList.get(position).getTodayDeaths(),
                    mDataList.get(position).getRecovered(),
                    mDataList.get(position).getCritical()

            ));


            mainBinding.RecyclerViewData.setAdapter(mCoronaAdapter);
        });

        countries.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Complete> mysearchlist = new ArrayList<>();
                for (Complete compDatalist : mDataList) {
                    if (compDatalist.getCountry().toLowerCase().startsWith(newText.toLowerCase())) {
                        mysearchlist.add(compDatalist);
                    }
                }
                mCoronaAdapter = new CoronaAdapter(getApplicationContext(), mysearchlist, position -> sendToDetails(mysearchlist.get(position).getCountry(),
                        mysearchlist.get(position).getCases(),
                        mysearchlist.get(position).getTodayCases(),
                        mysearchlist.get(position).getDeaths(),
                        mysearchlist.get(position).getTodayDeaths(),
                        mysearchlist.get(position).getRecovered(),
                        mysearchlist.get(position).getCritical()

                ));
                mainBinding.RecyclerViewData.setAdapter(mCoronaAdapter);


                return false;
            }
        });
    }


    private void displayViews(boolean visibility) {
        if (visibility == true) {
            mainBinding.Status.setVisibility(View.VISIBLE);
            mainBinding.Loading.setVisibility(View.GONE);
        } else {
            mainBinding.Status.setVisibility(View.GONE);
            mainBinding.Loading.setVisibility(View.VISIBLE);
        }
    }


    public void setupRecyclerView(RecyclerView mRecyclerView) {
        mLayout = new GridLayoutManager(getApplicationContext(), 1);
        mRecyclerView.setLayoutManager(mLayout);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    public void sendToDetails(String country, String cases, String todayCases, String deaths, String todayDeaths, String recovered, String critical) {

        Intent Go = new Intent(MainActivity.this, Details.class);
        Go.putExtra("Country", country);
        Go.putExtra("Cases", cases);
        Go.putExtra("TodayCases", todayCases);
        Go.putExtra("Deaths", deaths);
        Go.putExtra("TodayDeaths", todayDeaths);
        Go.putExtra("Recovered", recovered);
        Go.putExtra("Critical", critical);
        startActivity(Go);
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

    public void isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            noInternetView.setVisibility(View.GONE);
        } else {
            LoadingBar.setVisibility(View.GONE);

            mainBinding.RecyclerViewData.setVisibility(View.GONE);
            noInternetView.setVisibility(View.VISIBLE);

        }
    }
}
