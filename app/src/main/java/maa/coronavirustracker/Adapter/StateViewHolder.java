package maa.coronavirustracker.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import maa.coronavirustracker.R;

public class StateViewHolder extends RecyclerView.ViewHolder {

    TextView statenamess, stateconfirmcases, statedeltacondirmcases, stateactivecases, staterecoveredcases, statedeltarecoveredcases, statedeathcases, statedeltadeathcases;
    CardView statescard;

    public StateViewHolder(@NonNull View itemView) {
        super(itemView);
        statenamess = itemView.findViewById(R.id.StateName);
        stateconfirmcases = itemView.findViewById(R.id.ConfirmedCasesState);
        statedeltacondirmcases = itemView.findViewById(R.id.DeltaConfirmedCasesState);
        stateactivecases = itemView.findViewById(R.id.ActiveCasesState);
        staterecoveredcases = itemView.findViewById(R.id.RecoveredCasesState);
        statedeltarecoveredcases = itemView.findViewById(R.id.DeltaRecoveredCasesState);
        statedeathcases = itemView.findViewById(R.id.DeathsCasesState);
        statedeltadeathcases = itemView.findViewById(R.id.DeltaDeathsCasesState);
        statescard = itemView.findViewById(R.id.Cards_states);
    }
}
