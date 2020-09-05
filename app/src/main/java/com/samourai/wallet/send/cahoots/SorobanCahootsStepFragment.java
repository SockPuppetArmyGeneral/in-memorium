package com.samourai.wallet.send.cahoots;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samourai.wallet.R;
import com.samourai.wallet.cahoots.CahootsMessage;
import com.samourai.wallet.cahoots.CahootsType;
import com.samourai.wallet.cahoots.STONEWALLx2;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SorobanCahootsStepFragment extends Fragment {

    private TextView stoneWallx2TotalFee, stoneWallx2SplitFee;
    private ViewGroup feeSplitUpContainer;
    private int step = 0;
    private static final String TAG = "CahootsStepView";

    private CahootsMessage cahootsMessage;

    public static SorobanCahootsStepFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("step", position);
        SorobanCahootsStepFragment fragment = new SorobanCahootsStepFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        TextView stepText = view.findViewById(R.id.cahoots_step_text);
        stoneWallx2TotalFee = view.findViewById(R.id.stonewall_splitup_total_fee);
        stoneWallx2SplitFee = view.findViewById(R.id.stonewall_collab_fee);
        feeSplitUpContainer = view.findViewById(R.id.stonewall_fee_splitup_container);

        step = getArguments().getInt("step");
        stepText.setText("Step ".concat(String.valueOf(step + 1)));

        feeSplitUpContainer.setVisibility(View.GONE);

        // show stonewallx2 split fees to counterparty
        if (this.cahootsMessage != null && cahootsMessage.getType() == CahootsType.STONEWALLX2 && cahootsMessage.getStep() == 3) {
            STONEWALLx2 stonewallx2 = (STONEWALLx2)cahootsMessage.getCahoots();
            feeSplitUpContainer.post(() -> {
                feeSplitUpContainer.setVisibility(View.VISIBLE);
            });

            stoneWallx2TotalFee.setText(formatForBtc(stonewallx2.getFeeAmount()));
            stoneWallx2SplitFee.setText(formatForBtc(stonewallx2.getFeeAmount() / 2));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cahoots_step_view_soroban, container, false);
    }

    public void setCahootsMessage(CahootsMessage cahootsMessage) {
        this.cahootsMessage = cahootsMessage;
    }

    private String formatForBtc(Long amount) {
        return (String.format(Locale.ENGLISH, "%.8f", getBtcValue((double) amount)).concat(" BTC"));
    }

    private Double getBtcValue(Double sats) {
        return (sats / 1e8);
    }
}
