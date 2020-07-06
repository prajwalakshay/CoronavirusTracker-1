package maa.coronavirustracker.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import maa.coronavirustracker.R;

public class NewsViewHolder extends ViewHolder {

    TextView newstext,hoursagotext;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        newstext=itemView.findViewById(R.id.newsforu);
        hoursagotext = itemView.findViewById(R.id.HoursAgo);
    }
}
