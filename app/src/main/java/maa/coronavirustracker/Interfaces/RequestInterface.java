package maa.coronavirustracker.Interfaces;

import maa.coronavirustracker.Models.Resource;
import maa.coronavirustracker.Models.ServiceData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("resources.json")
    Call<ServiceData> getResources();
}
