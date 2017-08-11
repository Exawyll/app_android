package fr.imie.geekquote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import fr.imie.geekquote.R;
import fr.imie.geekquote.model.Quote;

/**
 * Created by WYLLIAM on 10/08/2017.
 */
public class QuoteListAdapter extends ArrayAdapter<Quote> {

    public QuoteListAdapter(Context context, ArrayList<Quote> quotes) {
        super(context, 0, quotes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Quote quote = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_quote, parent, false);
        }
        // Lookup view for data population
        TextView tvQuote = (TextView) convertView.findViewById(R.id.tvQuote);
        tvQuote.setTextSize(18);
        TextView tvCreation = (TextView) convertView.findViewById(R.id.tvCreationDate);
        tvQuote.setTextSize(18);

        if (position % 2 == 0) {
            convertView.setBackgroundResource(R.drawable.quotes_list_backgroundcolor);
        } else {
            convertView.setBackgroundResource(R.drawable.quotes_list_background_alternate);
        }

        // Populate the data into the template view using the data object
        tvQuote.setText(quote.getStrQuote());
        tvCreation.setText(quote.getCreationDate().toString());

        // Return the completed view to render on screen
        return convertView;

    }
}
