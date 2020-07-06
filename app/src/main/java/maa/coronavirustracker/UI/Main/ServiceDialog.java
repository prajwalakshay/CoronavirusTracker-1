package maa.coronavirustracker.UI.Main;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

import maa.coronavirustracker.Adapter.ServiceAdapter;
import maa.coronavirustracker.Data.ResourcesClient;
import maa.coronavirustracker.Models.Resource;
import maa.coronavirustracker.R;

public class ServiceDialog {
    private Activity activity;
    private AlertDialog dialog;
    TextView closeService;
    public static RecyclerView ServiceCycleData;
    public static ProgressBar ServiceBar;
    public static RelativeLayout Nohelp;
    public androidx.appcompat.widget.SearchView ServiceSearch;
    ArrayList<Resource> myResourceList = new ArrayList<>();
    CheckBox HospitalCheck, PoliceCheck, WomenCheck, FreeFoodCheck, GovtCheck, CovidCheck;
    Boolean SearchValue = false;
    Boolean SearchHospital = false;
    Boolean SearchPolice = false;
    Boolean SearchCovid = false;
    Boolean SearchFree = false;
    Boolean SearchWomen = false;
    Boolean SearchGovt = false;

    public ServiceDialog(Activity myActivity) {
        activity = myActivity;
    }

    public void getServiceDetails() {


        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.service_activity, null));
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
        closeService = dialog.findViewById(R.id.Close_Service);
        ServiceCycleData = dialog.findViewById(R.id.ServiceCycle);
        ServiceBar = dialog.findViewById(R.id.ServiceLoading);
        Nohelp = dialog.findViewById(R.id.NoService);
        ServiceSearch = dialog.findViewById(R.id.SearchServiceForYou);
        HospitalCheck = dialog.findViewById(R.id.Hospitals);
        PoliceCheck = dialog.findViewById(R.id.Police);
        WomenCheck = dialog.findViewById(R.id.WomenHelp);
        FreeFoodCheck = dialog.findViewById(R.id.FreeFood);
        GovtCheck = dialog.findViewById(R.id.Government);
        CovidCheck = dialog.findViewById(R.id.TestingLab);

        ResourcesClient resourcesClient = new ResourcesClient(activity);
        resourcesClient.getCallResponse();

        ArrayList<Resource> Universalrecourcelist = new ArrayList<>();
        /*for (Resource resource : ResourcesClient.resources){
            Universalrecourcelist.add(resource);
        }*/

        SearchValue = false;

        HospitalCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (HospitalCheck.isChecked()) {
                    SearchValue = true;
                    SearchHospital = true;
                    ServiceSearch.setQuery(null, false);
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getCategory().toLowerCase().contains("hospitals")) {
                            Universalrecourcelist.add(resource);
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, Universalrecourcelist);
                            ServiceCycleData.setAdapter(serviceAdapter);
                            serviceAdapter.notifyDataSetChanged();
                        }
                    }

                } else if (!HospitalCheck.isChecked()) {
                    SearchValue = false;
                    SearchHospital = false;
                    ServiceSearch.setQuery(null, false);
                    ArrayList<Resource> HospitalUnchecked = new ArrayList<>();
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getCategory().toLowerCase().contains("hospitals")) {
                            HospitalUnchecked.add(resource);
                            if (!Universalrecourcelist.isEmpty()) {
                                Universalrecourcelist.remove(resource);
                            }
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, Universalrecourcelist);
                            ServiceCycleData.setAdapter(serviceAdapter);
                            serviceAdapter.notifyDataSetChanged();
                        }
                    }
                    if (Universalrecourcelist.isEmpty()) {
                        ArrayList<Resource> defaultltist = new ArrayList<>(ResourcesClient.resources);
                        ServiceAdapter serviceAdapter = new ServiceAdapter(activity, defaultltist);
                        ServiceCycleData.setAdapter(serviceAdapter);
                        serviceAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        PoliceCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PoliceCheck.isChecked()) {
                    ;
                    SearchPolice = true;
                    SearchValue = true;
                    ServiceSearch.setQuery(null, false);
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getCategory().toLowerCase().contains("police")) {
                            Universalrecourcelist.add(resource);
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, Universalrecourcelist);
                            ServiceCycleData.setAdapter(serviceAdapter);
                            serviceAdapter.notifyDataSetChanged();
                        }
                    }

                } else if (!PoliceCheck.isChecked()) {
                    SearchPolice = false;
                    SearchValue = false;
                    ServiceSearch.setQuery(null, false);
                    ArrayList<Resource> PoliceUnchecked = new ArrayList<>();
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getCategory().toLowerCase().contains("police")) {
                            PoliceUnchecked.add(resource);
                            if (!Universalrecourcelist.isEmpty()) {
                                Universalrecourcelist.remove(resource);

                            }
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, Universalrecourcelist);
                            ServiceCycleData.setAdapter(serviceAdapter);
                            serviceAdapter.notifyDataSetChanged();
                        }
                    }
                    if (Universalrecourcelist.isEmpty()) {
                        ArrayList<Resource> defaultltist = new ArrayList<>(ResourcesClient.resources);
                        ServiceAdapter serviceAdapter = new ServiceAdapter(activity, defaultltist);
                        ServiceCycleData.setAdapter(serviceAdapter);
                        serviceAdapter.notifyDataSetChanged();
                    }

                }
            }
        });

        WomenCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (WomenCheck.isChecked()) {

                    SearchWomen = true;
                    SearchValue = true;
                    ServiceSearch.setQuery(null, false);
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getCategory().toLowerCase().contains("other")) {
                            Universalrecourcelist.add(resource);
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, Universalrecourcelist);
                            ServiceCycleData.setAdapter(serviceAdapter);
                            serviceAdapter.notifyDataSetChanged();
                        }
                    }


                } else if (!WomenCheck.isChecked()) {
                    SearchWomen = false;
                    SearchValue = false;
                    ServiceSearch.setQuery(null, false);
                    ArrayList<Resource> WomenUnchecked = new ArrayList<>();
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getCategory().toLowerCase().contains("other")) {
                            WomenUnchecked.add(resource);
                            if (!Universalrecourcelist.isEmpty()) {
                                Universalrecourcelist.remove(resource);


                            }
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, Universalrecourcelist);
                            ServiceCycleData.setAdapter(serviceAdapter);
                            serviceAdapter.notifyDataSetChanged();
                        }
                    }
                    if (Universalrecourcelist.isEmpty()) {
                        ArrayList<Resource> defaultltist = new ArrayList<>(ResourcesClient.resources);
                        ServiceAdapter serviceAdapter = new ServiceAdapter(activity, defaultltist);
                        ServiceCycleData.setAdapter(serviceAdapter);
                        serviceAdapter.notifyDataSetChanged();
                    }


                }
            }
        });

        GovtCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (GovtCheck.isChecked()) {

                    SearchGovt = true;
                    SearchValue = true;
                    ServiceSearch.setQuery(null, false);
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getCategory().toLowerCase().contains("government")) {
                            Universalrecourcelist.add(resource);
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, Universalrecourcelist);
                            ServiceCycleData.setAdapter(serviceAdapter);
                            serviceAdapter.notifyDataSetChanged();
                        }
                    }


                } else if (!GovtCheck.isChecked()) {
                    SearchGovt = false;
                    SearchValue = false;
                    ServiceSearch.setQuery(null, false);
                    ArrayList<Resource> GovtUnchecked = new ArrayList<>();
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getCategory().toLowerCase().contains("government")) {
                            GovtUnchecked.add(resource);
                            if (!Universalrecourcelist.isEmpty()) {
                                Universalrecourcelist.remove(resource);

                            }
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, Universalrecourcelist);
                            ServiceCycleData.setAdapter(serviceAdapter);
                            serviceAdapter.notifyDataSetChanged();
                        }
                    }
                    if (Universalrecourcelist.isEmpty()) {
                        ArrayList<Resource> defaultltist = new ArrayList<>(ResourcesClient.resources);
                        ServiceAdapter serviceAdapter = new ServiceAdapter(activity, defaultltist);
                        ServiceCycleData.setAdapter(serviceAdapter);
                        serviceAdapter.notifyDataSetChanged();
                    }

                }
            }
        });

        FreeFoodCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (FreeFoodCheck.isChecked()) {
                    SearchFree = true;
                    SearchValue = true;
                    ServiceSearch.setQuery(null, false);
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getCategory().toLowerCase().contains("free")) {
                            Universalrecourcelist.add(resource);
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, Universalrecourcelist);
                            ServiceCycleData.setAdapter(serviceAdapter);
                            serviceAdapter.notifyDataSetChanged();
                        }
                    }
                } else if (!FreeFoodCheck.isChecked()) {
                    SearchFree = false;
                    SearchValue = false;
                    ServiceSearch.setQuery(null, false);
                    ArrayList<Resource> FreeUnchecked = new ArrayList<>();
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getCategory().toLowerCase().contains("free")) {
                            FreeUnchecked.add(resource);
                            if (!Universalrecourcelist.isEmpty()) {
                                Universalrecourcelist.remove(resource);
                            }
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, Universalrecourcelist);
                            ServiceCycleData.setAdapter(serviceAdapter);
                            serviceAdapter.notifyDataSetChanged();
                        }
                    }
                    if (Universalrecourcelist.isEmpty()) {
                        ArrayList<Resource> defaultltist = new ArrayList<>(ResourcesClient.resources);
                        ServiceAdapter serviceAdapter = new ServiceAdapter(activity, defaultltist);
                        ServiceCycleData.setAdapter(serviceAdapter);
                        serviceAdapter.notifyDataSetChanged();
                    }


                }
            }
        });

        CovidCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (CovidCheck.isChecked()) {
                    SearchValue = true;
                    SearchCovid = true;
                    ServiceSearch.setQuery(null, false);
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getCategory().toLowerCase().contains("covid")) {
                            Universalrecourcelist.add(resource);
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, Universalrecourcelist);
                            ServiceCycleData.setAdapter(serviceAdapter);
                            serviceAdapter.notifyDataSetChanged();
                        }
                    }
                } else if (!CovidCheck.isChecked()) {
                    SearchValue = false;
                    SearchCovid = false;
                    ServiceSearch.setQuery(null, false);
                    ArrayList<Resource> CovidUnchecked = new ArrayList<>();
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getCategory().toLowerCase().contains("covid")) {
                            CovidUnchecked.add(resource);
                            if (!Universalrecourcelist.isEmpty()) {
                                Universalrecourcelist.remove(resource);
                            }
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, Universalrecourcelist);
                            ServiceCycleData.setAdapter(serviceAdapter);
                            serviceAdapter.notifyDataSetChanged();
                        }
                    }
                    if (Universalrecourcelist.isEmpty()) {
                        ArrayList<Resource> defaultltist = new ArrayList<>(ResourcesClient.resources);
                        ServiceAdapter serviceAdapter = new ServiceAdapter(activity, defaultltist);
                        ServiceCycleData.setAdapter(serviceAdapter);
                        serviceAdapter.notifyDataSetChanged();
                    }


                }
            }
        });


        ServiceSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!SearchHospital && !SearchPolice && !SearchCovid && !SearchFree && !SearchGovt && !SearchWomen ) {
                    ArrayList<Resource> myResourceListsearch = new ArrayList<>();
                    for (Resource resource : ResourcesClient.resources) {
                        if (resource.getNameoftheorganisation().toLowerCase().startsWith(newText.toLowerCase()) || resource.getState().toLowerCase().startsWith(newText.toLowerCase()) || resource.getCity().toLowerCase().startsWith(newText.toLowerCase()) || resource.getCategory().toLowerCase().startsWith(newText.toLowerCase())) {
                            myResourceListsearch.add(resource);
                        }
                    }
                    ServiceAdapter serviceAdaptersaerch = new ServiceAdapter(activity, myResourceListsearch);
                    ServiceCycleData.setAdapter(serviceAdaptersaerch);
                    serviceAdaptersaerch.notifyDataSetChanged();
                } else if (!Universalrecourcelist.isEmpty()) {
                    ArrayList<Resource> myResourceList = new ArrayList<>();
                    for (Resource resource : Universalrecourcelist) {
                        if (resource.getNameoftheorganisation().toLowerCase().startsWith(newText.toLowerCase()) || resource.getState().toLowerCase().startsWith(newText.toLowerCase()) || resource.getCity().toLowerCase().startsWith(newText.toLowerCase()) || resource.getCategory().toLowerCase().startsWith(newText.toLowerCase())) {
                            myResourceList.add(resource);
                        }
                    }
                    ServiceAdapter serviceAdapter = new ServiceAdapter(activity, myResourceList);
                    ServiceCycleData.setAdapter(serviceAdapter);
                    serviceAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });


        closeService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void getWorldServiceDetails() {


        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.service_activity, null));
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
        closeService = dialog.findViewById(R.id.Close_Service);
        ServiceCycleData = dialog.findViewById(R.id.ServiceCycle);
        ServiceBar = dialog.findViewById(R.id.ServiceLoading);
        Nohelp = dialog.findViewById(R.id.NoService);

        ServiceBar.setVisibility(View.GONE);
        Nohelp.setVisibility(View.VISIBLE);

        closeService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


}








