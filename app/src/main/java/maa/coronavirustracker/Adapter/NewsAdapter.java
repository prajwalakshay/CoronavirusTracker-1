package maa.coronavirustracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import maa.coronavirustracker.Models.LogData;
import maa.coronavirustracker.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    List<LogData> myLogData;
    Context newsContext;

    public NewsAdapter(Context Newscontext, List<LogData> myLogdata) {
        newsContext = Newscontext;
        myLogData = myLogdata;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View NewsView = layoutInflater.inflate(R.layout.newitem, parent, false);

        return new NewsViewHolder(NewsView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.hoursagotext.setText(myLogData.get(position).getTime());
        holder.newstext.setText(myLogData.get(position).getNews());

    }

    @Override
    public int getItemCount() {
        return myLogData.size();
    }
}
