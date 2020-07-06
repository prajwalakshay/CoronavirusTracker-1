package maa.coronavirustracker.Models;

import com.google.gson.annotations.SerializedName;

public class Resume {
    @SerializedName("recovered")
    private String recovered;
    @SerializedName("cases")
    private String cases;
    @SerializedName("deaths")
    private String deaths;
    @SerializedName("todayCases")
    private String todayCases;
    @SerializedName("todayDeaths")
    private String todayDeaths;
    @SerializedName("active")
    private String active;

    public Resume(String recovered, String cases, String deaths,String todayCases,String todayDeaths,String active) {
        this.recovered = recovered;
        this.cases = cases;
        this.deaths = deaths;
        this.todayCases =todayCases;
        this.todayDeaths = todayDeaths;
        this.active = active;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getCases() {
        return cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getActive() {
        return active;
    }



}
