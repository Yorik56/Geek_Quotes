package fr.mds.geekquote.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fr.mds.geekquote.QuoteListActivity;
import fr.mds.geekquote.R;

public class QuoteListAdapter extends ArrayAdapter<Quote> {
    public QuoteListAdapter(@NonNull Context context, ArrayList<Quote> quotes) {
        super(context, 0,quotes);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Quote quote = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_quote,parent,false);

        }
        TextView tvQuote = (TextView) convertView.findViewById(R.id.tvQuoteListActivity);
        if (quote.getId() % 2 == 0){
            tvQuote.setBackgroundColor(R.color.design_default_color_secondary_variant);
        } else {
            tvQuote.setBackgroundColor(R.color.teal_700);
        }
        tvQuote.setText(quote.getStrQuote());

        return convertView;
    }
}
