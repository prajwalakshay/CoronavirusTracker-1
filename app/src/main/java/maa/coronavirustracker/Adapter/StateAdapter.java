package maa.coronavirustracker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import maa.coronavirustracker.Models.StateHelper;
import maa.coronavirustracker.R;
import maa.coronavirustracker.UI.Main.DistrictDetails;

public class StateAdapter extends RecyclerView.Adapter<StateViewHolder> {
    List<StateHelper> stateHelpers;
    Context Statecontext;

    public StateAdapter(Context statecontext, List<StateHelper> mystateHelpers) {
        Statecontext = statecontext;
        stateHelpers = mystateHelpers;
    }


    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View statesView = layoutInflater.inflate(R.layout.states, parent, false);
        return new StateViewHolder(statesView);
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int position) {

        holder.statenamess.setText(stateHelpers.get(position).getStateNames());
        if (stateHelpers.get(position).getStateDeltaConfirmCases().equals("0")) {
            holder.statedeltacondirmcases.setText("    ");
        } else {
            holder.statedeltacondirmcases.setText("↑" + stateHelpers.get(position).getStateDeltaConfirmCases());
        }
        holder.stateconfirmcases.setText(String.format("%,d", Integer.parseInt(stateHelpers.get(position).getStateConfirmCases())));

        if (stateHelpers.get(position).getStateDeltaRecoveredCases().equals("0")) {
            holder.statedeltarecoveredcases.setText("    ");
        } else {
            holder.statedeltarecoveredcases.setText("↑" + stateHelpers.get(position).getStateDeltaRecoveredCases());
        }
        holder.staterecoveredcases.setText(String.format("%,d", Integer.parseInt(stateHelpers.get(position).getStateRecoveredCases())));
        if (stateHelpers.get(position).getStateDeltaDeathCases().equals("0")) {
            holder.statedeltadeathcases.setText("    ");
        } else {
            holder.statedeltadeathcases.setText("↑" + stateHelpers.get(position).getStateDeltaDeathCases());
        }
        holder.statedeathcases.setText(String.format("%,d", Integer.parseInt(stateHelpers.get(position).getStateDeathCases())));

        holder.stateactivecases.setText(stateHelpers.get(position).getStateActiveCases());


        holder.statescard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent district = new Intent(Statecontext, DistrictDetails.class);
                district.putExtra("STATENAME", stateHelpers.get(position).getStateNames());
                district.putExtra("STATECONFIRMCASES", stateHelpers.get(position).getStateConfirmCases());
                district.putExtra("STATEDELTACONFIRMCASES", stateHelpers.get(position).getStateDeltaConfirmCases());
                district.putExtra("STATERECOVEREDASES", stateHelpers.get(position).getStateRecoveredCases());
                district.putExtra("STATEDELTARECOVEREDCASES", stateHelpers.get(position).getStateDeltaRecoveredCases());
                district.putExtra("STATEDEATHCASES", stateHelpers.get(position).getStateDeathCases());
                district.putExtra("STATEDELTADEATHCASES", stateHelpers.get(position).getStateDeltaDeathCases());
                district.putExtra("STATEACTIVECASES", stateHelpers.get(position).getStateActiveCases());
                district.putExtra("TIMEY", stateHelpers.get(position).getStateUpdatedTime());
                Statecontext.startActivity(district);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stateHelpers.size();
    }
}
