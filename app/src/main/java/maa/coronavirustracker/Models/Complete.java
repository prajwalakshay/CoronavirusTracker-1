package maa.coronavirustracker.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Complete {
    @SerializedName("country")
    private String country;
    @SerializedName("recovered")
    private String recovered;
    @SerializedName("cases")
    private String cases;
    @SerializedName("critical")
    private String critical;
    @SerializedName("deaths")
    private String deaths;
    @SerializedName("todayCases")
    private String todayCases;
    @SerializedName("todayDeaths")
    private String todayDeaths;
    @SerializedName("active")
    private String active;

    public Complete(String country, String recovered, String cases, String critical, String deaths, String todayCases, String todayDeaths, String active) {
        this.country = country;
        this.recovered = recovered;
        this.cases = cases;
        this.critical = critical;
        this.deaths = deaths;
        this.todayCases = todayCases;
        this.todayDeaths = todayDeaths;
        this.active = active;
    }

    public String getCountry() {
        return country;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getCases() {
        return cases;
    }

    public String getCritical() {
        return critical;
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
