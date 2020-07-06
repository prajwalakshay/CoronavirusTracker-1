package maa.coronavirustracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import maa.coronavirustracker.Models.DistrictHelper;
import maa.coronavirustracker.R;

public class DistrictAdapter extends RecyclerView.Adapter<DistricViewHolder> {
    Context DistrictContext;
    List<DistrictHelper> districtHelperList;

    public DistrictAdapter(Context DistrictContext, List<DistrictHelper> districtHelperList) {
        this.DistrictContext = DistrictContext;
        this.districtHelperList = districtHelperList;
    }

    @NonNull
    @Override
    public DistricViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View DistrictView = layoutInflater.inflate(R.layout.districtitem, parent, false);
        return new DistricViewHolder(DistrictView);
    }

    @Override
    public void onBindViewHolder(@NonNull DistricViewHolder holder, int position) {
        holder.DisName.setText(districtHelperList.get(position).getDistrictName());
        holder.DisConfirm.setText(districtHelperList.get(position).getDistrictConfirm());
        holder.DisRecovered.setText(districtHelperList.get(position).getDistrictRecovered());
        holder.DisDeceased.setText(districtHelperList.get(position).getDistrictDeceased());
        holder.DisActive.setText(districtHelperList.get(position).getDistrictActive());
        if(districtHelperList.get(position).getDistrictDeltaConfirmed().equals("0")){
            holder.DisDeltaConfirm.setText("");
        }else{
            holder.DisDeltaConfirm.setText("↑"+districtHelperList.get(position).getDistrictDeltaConfirmed());
        }
        if (districtHelperList.get(position).getDistrictDeltaDeceased().equals("0")){
            holder.DisDeltaDeceased.setText("");
        }else{
            holder.DisDeltaDeceased.setText("↑"+districtHelperList.get(position).getDistrictDeltaDeceased());
        }if(districtHelperList.get(position).getDistrictDeltaRecovered().equals("0")){
            holder.DisDeltaRecovered.setText("");
        }else{
            holder.DisDeltaRecovered.setText("↑"+districtHelperList.get(position).getDistrictDeltaRecovered());
        }
    }

    @Override
    public int getItemCount() {
        return districtHelperList.size();
    }
}
