package maa.coronavirustracker.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import maa.coronavirustracker.R;

public class DistricViewHolder extends RecyclerView.ViewHolder {

    TextView DisName,DisActive,DisConfirm,DisDeltaConfirm,DisRecovered,DisDeltaRecovered,DisDeceased,DisDeltaDeceased;

    public DistricViewHolder(@NonNull View itemView) {
        super(itemView);

        DisName = itemView.findViewById(R.id.NameDis);
        DisActive = itemView.findViewById(R.id.ActiveCasesDis);
        DisConfirm = itemView.findViewById(R.id.ConfirmedCasesDis);
        DisDeltaConfirm = itemView.findViewById(R.id.DeltaConfirmedCasesDis);
        DisRecovered = itemView.findViewById(R.id.RecoveredCasesDis);
        DisDeltaRecovered = itemView.findViewById(R.id.DeltaRecoveredCasesDis);
        DisDeceased = itemView.findViewById(R.id.DeathsCasesDis);
        DisDeltaDeceased = itemView.findViewById(R.id.DeltaDeathsCasesDis);


    }
}
