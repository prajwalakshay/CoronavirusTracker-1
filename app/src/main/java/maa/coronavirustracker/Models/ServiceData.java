
package maa.coronavirustracker.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceData {

    @SerializedName("resources")
    @Expose
    private List<Resource> resources = null;

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

}
