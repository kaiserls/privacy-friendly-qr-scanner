package com.secuso.privacyfriendlycodescanner.qrscanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

// TODO:
import.google.zxing.client.result.EPCParsedResult;
import com.secuso.privacyfriendlycodescanner.qrscanner.R;

import java.util.ArrayList;
import java.util.List;
public class EPCResultAdapter extends RecyclerView.Adapter<EPCResultAdapter.EPCViewHolder> {

    public static class EPCResultItem {
        public final EPCResultItemType type;
        public final String content;

        EPCResultItem(EPCResultItemType type, String content) {
            this.type = type;
            this.content = content;
        }
    }

    public enum EPCResultItemType {
        TYPE_BIC("BIC:"),
        TYPE_NAME("Name:"),
        TYPE_IBAN("IBAN:"),
        TYPE_CURRENCY("Currency"),
        TYPE_AMOUNT("Amount:"),
        TYPE_REFERENCE("Reference:")

        @StringRes
        int local;

        EPCResultItemType(int local) {
            this.local = local;
        }
    }

    private final List<EPCResultItem> resultItems = new ArrayList<>();

    private EPCResultAdapter(@NonNull List<EPCResultItem> resultItems) {
        this.resultItems.addAll(resultItems);
    }

    public EPCResultAdapter(EPCParsedResult result) {
        this(buildResultItems(result));
    }

    private static List<EPCResultItem> buildResultItems(EPCParsedResult result) {
        List<EPCResultItem> items = new ArrayList<>();

        if(result != null) {
            if(result.getBIC() != null) {
                items.add(new EPCResultItem(EPCResultItemType.TYPE_BIC, result.getBIC()));
            }
            if(result.getName() != null) {
                items.add(new EPCResultItem(EPCResultItemType.TYPE_NAME, result.getName()));
            }
            if(result.getIBAN() != null) {
                items.add(new EPCResultItem(EPCResultItemType.TYPE_IBAN, result.getIBAN()));
            }
            if(result.getCurrency() != null) {
                items.add(new EPCResultItem(EPCResultItemType.TYPE_CURRENCY, result.getCurrency()));
            }
            if(result.getAmount() != null) {
                items.add(new EPCResultItem(EPCResultItemType.TYPE_AMOUNT, result.getAmount()));
            }
            if(result.getReference() != null) {
                items.add(new EPCResultItem(EPCResultItemType.TYPE_REFERENCE, result.getReference()));
            }
        }
        return items;
    }

    @NonNull
    @Override
    public EPCViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.item_result_epc, viewGroup, false);
        return new EPCViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EPCViewHolder viewHolder, int i) {
        Context context = viewHolder.content.getContext();
        viewHolder.content.setText(resultItems.get(i).content);
        viewHolder.type.setText(context.getString(resultItems.get(i).type.local));
    }

    @Override
    public int getItemCount() {
        return resultItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resultItems.get(position).type.ordinal();
    }

    class EPCViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        TextView type;
        EPCViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.item_result_epc_content);
            type = itemView.findViewById(R.id.item_result_epc_type);
        }
    }
}
