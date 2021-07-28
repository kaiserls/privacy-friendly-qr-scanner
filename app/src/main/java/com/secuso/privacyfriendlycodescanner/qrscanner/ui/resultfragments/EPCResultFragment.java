package com.secuso.privacyfriendlycodescanner.qrscanner.ui.resultfragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.client.result.EPCParsedResult;
import com.secuso.privacyfriendlycodescanner.qrscanner.R;
import com.secuso.privacyfriendlycodescanner.qrscanner.ui.adapter.EPCResultAdapter;
import com.secuso.privacyfriendlycodescanner.qrscanner.ui.adapter.EmailResultAdapter;

public class EPCResultFragment extends ResultFragment {

    EPCParsedResult result;

    RecyclerView resultList;

    public EPCResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_result_email, container, false);

        result = (BankingParsedResult) parsedResult;

        resultList = v.findViewById(R.id.fragment_result_recycler_view);
        resultList.setLayoutManager(new LinearLayoutManager(getContext()));
        resultList.setAdapter(new EPCResultAdapter(result));

        return v;
    }

    @Override
    public void onProceedPressed(Context context) {
        // TODO: rework this..
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.choose_action)
                .setItems(R.array.open_banking_app, (dialog, which) -> {
                    String caption = "";
                    switch (which) {
                        case 0:
                            //TODO: Find interface for banking apps if public and existing
                            /*
                            Intent banking = new Intent(Intent.ACTION_SENDTO);
                            //TODO: What is setData for this case?
                            banking.setData(Uri.parse("transfer-to:"));
                            banking.putExtra(Intent.EXTRA_BIC, result.getBIC());
                            banking.putExtra(Intent.EXTRA_NAME, result.getName());
                            banking.putExtra(Intent.EXTRA_IBAN, result.getIBAN());
                            banking.putExtra(Intent.EXTRA_CURRENCY, result.getCurrency());
                            banking.putExtra(Intent.EXTRA_AMOUNT, result.getAmount());
                            banking.putExtra(Intent.EXTRA_REFERENCE, result.getReference());
                            caption = getResources().getStringArray(R.array.open_banking_app)[0];
                            startActivity(Intent.createChooser(banking, caption));
                            */
                            break;
                        default:
                    }
                });
        builder.create().show();
    }


}
