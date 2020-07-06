package maa.coronavirustracker.Models;

/*
  String DisName = districtname.getString("district");
                        String DisActive = districtname.getString("active");
                        String DisConfirmed = districtname.getString("confirmed");
                        String DisDeceased = districtname.getString("deceased");
                        String DisRecovered = districtname.getString("recovered");

                        JSONObject deltas = districtname.getJSONObject("delta");
                        String DisDeltaConfirmed = deltas.getString("confirmed");
                        String DisDeltaDeceased = deltas.getString("deceased");
                        String DisDeltaRecovered = deltas.getString("recovered");
*/

public class DistrictHelper {

    String DistrictName;
    String DistrictActive;
    String DistrictConfirm;
    String DistrictDeceased;
    String DistrictRecovered;
    String DistrictDeltaConfirmed;
    String DistrictDeltaDeceased;
    String DistrictDeltaRecovered;

    public DistrictHelper(String districtName, String districtActive, String districtConfirm, String districtDeceased, String districtRecovered, String districtDeltaConfirmed, String districtDeltaDeceased, String districtDeltaRecovered) {
        DistrictName = districtName;
        DistrictActive = districtActive;
        DistrictConfirm = districtConfirm;
        DistrictDeceased = districtDeceased;
        DistrictRecovered = districtRecovered;
        DistrictDeltaConfirmed = districtDeltaConfirmed;
        DistrictDeltaDeceased = districtDeltaDeceased;
        DistrictDeltaRecovered = districtDeltaRecovered;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public String getDistrictActive() {
        return DistrictActive;
    }

    public String getDistrictConfirm() {
        return DistrictConfirm;
    }

    public String getDistrictDeceased() {
        return DistrictDeceased;
    }

    public String getDistrictRecovered() {
        return DistrictRecovered;
    }

    public String getDistrictDeltaConfirmed() {
        return DistrictDeltaConfirmed;
    }

    public String getDistrictDeltaDeceased() {
        return DistrictDeltaDeceased;
    }

    public String getDistrictDeltaRecovered() {
        return DistrictDeltaRecovered;
    }
}
