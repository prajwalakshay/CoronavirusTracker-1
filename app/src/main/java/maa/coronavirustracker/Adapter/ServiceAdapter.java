package maa.coronavirustracker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import maa.coronavirustracker.Models.Resource;
import maa.coronavirustracker.R;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceViewHolder> {
    private Context ServiceContext;
    List<Resource> resourceArrayList;
    String Number, Link;

    public ServiceAdapter(Context ServiceContext, List<Resource> resourcesArrayList) {
        this.ServiceContext = ServiceContext;
        this.resourceArrayList = resourcesArrayList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View ServiceView = layoutInflater.inflate(R.layout.service_item, parent, false);
        return new ServiceViewHolder(ServiceView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {

        Number = resourceArrayList.get(position).getPhonenumber();
        Link = resourceArrayList.get(position).getContact();
        holder.ServiceName.setText(resourceArrayList.get(position).getNameoftheorganisation());
        holder.ServiceCategory.setText(resourceArrayList.get(position).getCategory());
        holder.ServiceDistrict.setText(resourceArrayList.get(position).getCity());
        holder.ServiceDesc.setText(resourceArrayList.get(position).getDescriptionandorserviceprovided());
        holder.ServiceState.setText(resourceArrayList.get(position).getState());

        holder.ServiceNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + resourceArrayList.get(position).getPhonenumber()));
                    ServiceContext.startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(ServiceContext, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.ServiceWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(resourceArrayList.get(position).getContact()));
                    ServiceContext.startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(ServiceContext, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return resourceArrayList.size();
    }
}
