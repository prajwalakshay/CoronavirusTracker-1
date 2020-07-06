package maa.coronavirustracker.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import maa.coronavirustracker.Interfaces.onRecyclerViewItemClick;
import maa.coronavirustracker.Models.Complete;
import maa.coronavirustracker.R;

public class CoronaAdapter extends RecyclerView.Adapter<CoronaAdapter.CoronaViewHolder> {
    private Context mContext;
    private ArrayList<Complete> data = new ArrayList<>();
    private onRecyclerViewItemClick monRecyclerViewItemClick;


    public CoronaAdapter(Context mContext, ArrayList<Complete> data, onRecyclerViewItemClick monRecyclerViewItemClick) {
        this.mContext = mContext;
        this.data = data;

        this.monRecyclerViewItemClick = monRecyclerViewItemClick;
    }

    @NonNull
    @Override
    public CoronaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);


        return new CoronaViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoronaViewHolder holder, int position) {
        holder.onBindView(position);


    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class CoronaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mCountry, countryname, confirmedcases, deltaconfirmedcases, recoveredcases, deathcases, deltadeathcases, activecases, gridtextconfirmed, gridtextrecovered, gridtextdeath, gridtextactive;
        public ImageView flags;

        public CoronaViewHolder(@NonNull View itemView) {
            super(itemView);
            countryname = itemView.findViewById(R.id.CountryName);
            confirmedcases = itemView.findViewById(R.id.ConfirmedCasesCountry);
            deltaconfirmedcases = itemView.findViewById(R.id.DeltaConfirmedCasesCountry);
            recoveredcases = itemView.findViewById(R.id.RecoveredCasesCountry);
            deathcases = itemView.findViewById(R.id.DeathsCasesCountry);
            deltadeathcases = itemView.findViewById(R.id.DeltaDeathsCasesCountry);
            activecases = itemView.findViewById(R.id.ActiveCasesCountry);
            gridtextconfirmed = itemView.findViewById(R.id.gridconfiremedCountry);
            gridtextrecovered = itemView.findViewById(R.id.gridrecoveredCountry);
            gridtextactive = itemView.findViewById(R.id.gridactivetext);
            gridtextdeath = itemView.findViewById(R.id.gridDeathCountry);
            Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(), "amaranth.ttf");
            countryname.setTypeface(custom_font);
            confirmedcases.setTypeface(custom_font);
            deltadeathcases.setTypeface(custom_font);
            deltaconfirmedcases.setTypeface(custom_font);
            recoveredcases.setTypeface(custom_font);
            deathcases.setTypeface(custom_font);
            activecases.setTypeface(custom_font);
            gridtextrecovered.setTypeface(custom_font);
            gridtextdeath.setTypeface(custom_font);
            gridtextactive.setTypeface(custom_font);
            gridtextconfirmed.setTypeface(custom_font);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            monRecyclerViewItemClick.onRecyclerViewItemClick(getAdapterPosition());
        }

        @SuppressLint("DefaultLocale")
        public void onBindView(int position) {

            // World.init(mContext);
            // final int flagsint = World.getFlagOf(data.get(position).getCountry());
            // flags.setImageResource(flagsint);
            confirmedcases.setText(String.format("%,d", Integer.parseInt(data.get(position).getCases())));
            countryname.setText(data.get(position).getCountry());
            if (data.get(position).getTodayCases().equals("0")) {
                deltaconfirmedcases.setText("");
            } else {
                deltaconfirmedcases.setText(String.format("↑%,d", Integer.parseInt(data.get(position).getTodayCases())));
            }
            recoveredcases.setText(String.format("%,d", Integer.parseInt(data.get(position).getRecovered())));
            deathcases.setText(String.format("%,d", Integer.parseInt(data.get(position).getDeaths())));
            if (data.get(position).getTodayDeaths().equals("0")) {
                deltadeathcases.setText("");
            } else {
                deltadeathcases.setText(String.format("↑%,d", Integer.parseInt(data.get(position).getTodayDeaths())));
            }
            activecases.setText(String.format("%,d", Integer.parseInt(data.get(position).getActive())));
        }
    }
}
