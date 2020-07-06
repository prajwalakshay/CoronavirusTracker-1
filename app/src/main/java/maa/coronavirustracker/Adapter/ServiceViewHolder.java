package maa.coronavirustracker.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import maa.coronavirustracker.R;

public class ServiceViewHolder extends RecyclerView.ViewHolder {
    TextView ServiceName,ServiceCategory,ServiceDesc,ServiceState,ServiceDistrict,ServiceNumber,ServiceWebsite;
    public ServiceViewHolder(@NonNull View itemView) {
        super(itemView);
    ServiceName = itemView.findViewById(R.id.NameOfService);
    ServiceCategory = itemView.findViewById(R.id.Category);
    ServiceDesc = itemView.findViewById(R.id.DescOfService);
    ServiceState = itemView.findViewById(R.id.StateCardService);
    ServiceDistrict = itemView.findViewById(R.id.DistrictCardService);
    ServiceNumber = itemView.findViewById(R.id.Call);
    ServiceWebsite = itemView.findViewById(R.id.source);
    }
}
