package fr.imie.geekquote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.imie.geekquote.R;
import fr.imie.geekquote.adapter.QuoteListAdapter;
import fr.imie.geekquote.model.Quote;

public class QuoteListActivity extends Activity {

    private static final int MY_ACTIVITY_CODE = 0;
    public static final String TAG = "GeekQuoteTag";

    private ArrayList<Quote> myQuotes = new ArrayList<Quote>();

    private Button mButton;
    private EditText mEditText;
    private ListView mList;

    private QuoteListAdapter quoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        Log.d(TAG, "QuoteListActivity - onCreate");

        mList = (ListView) findViewById(R.id.listView);
        mButton = (Button) findViewById(R.id.add_button);
        mEditText = (EditText) findViewById(R.id.tv_main_hello);

        mButton.setOnClickListener(onClick());
        mList.setOnItemClickListener(onItemClick());

        quoteAdapter = new QuoteListAdapter(this, myQuotes);
        mList.setAdapter(quoteAdapter);
    }

    private void addQuote(String strQuote) {
        Quote quote = new Quote(strQuote);
        myQuotes.add(0, quote);
        quoteAdapter.notifyDataSetChanged();
    }

    private AdapterView.OnItemClickListener onItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent quoteActivity = new Intent(QuoteListActivity.this, DetailQuoteActivity.class);
                Quote quote = myQuotes.get(position);
                quoteActivity.putExtra("quote", quote);
                quoteActivity.putExtra("position",position);
                QuoteListActivity.this.startActivityForResult(quoteActivity, MY_ACTIVITY_CODE);
            }
        };
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MY_ACTIVITY_CODE:
                switch (resultCode) {
                    case RESULT_CANCELED:
                        break;
                    case RESULT_OK:
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            int rating = extras.getInt("rating");
                            int position = extras.getInt("position");
                            Quote quote = myQuotes.get(position);
                            quote.setRating(rating);
                            quoteAdapter.notifyDataSetChanged();
                        }
                        Toast.makeText(this, "We're back to the main list !! ;)", Toast.LENGTH_SHORT).show();
                        break;
                }
        }
    }

    private View.OnClickListener onClick() {

        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addQuote(mEditText.getText().toString());
                mEditText.setText("");
            }
        };
    }
}
