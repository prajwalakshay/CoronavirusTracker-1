package maa.coronavirustracker.Data;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import maa.coronavirustracker.Adapter.ServiceAdapter;
import maa.coronavirustracker.Constants.Constants;
import maa.coronavirustracker.Interfaces.RequestInterface;
import maa.coronavirustracker.Models.Resource;
import maa.coronavirustracker.Models.ServiceData;
import maa.coronavirustracker.UI.Main.ServiceDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResourcesClient {
    private Context context;
  public  static ArrayList<Resource> resources = new ArrayList<>();

    public ResourcesClient(Context context) {
        this.context = context;
    }

    public void getCallResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_RESOURCE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<ServiceData> resourceCall = requestInterface.getResources();
        resourceCall.enqueue(new Callback<ServiceData>() {
            @Override
            public void onResponse(Call<ServiceData> call, Response<ServiceData> response) {
                resources = new ArrayList<>(response.body().getResources());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
                ServiceDialog.ServiceCycleData.setLayoutManager(gridLayoutManager);
                ServiceAdapter serviceAdapter = new ServiceAdapter(context, resources);
                ServiceDialog.ServiceCycleData.setAdapter(serviceAdapter);
                serviceAdapter.notifyDataSetChanged();
                ServiceDialog.ServiceBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ServiceData> call, Throwable t) {
                ServiceDialog.Nohelp.setVisibility(View.VISIBLE);
            }
        });
    }


}
