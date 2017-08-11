package fr.imie.geekquote.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import fr.imie.geekquote.R;
import fr.imie.geekquote.model.Quote;

/**
 * Created by WYLLIAM on 11/08/2017.
 */
public class DetailQuoteActivity extends AppCompatActivity {

    public static final String TAG = "DetailQuoteTag";
    private static final int MY_ACTIVITY_CODE = 1;

    private TextView tvQuote;
    private TextView tvDateQuote;
    private RatingBar rbQuoteMark;
    private Button bCancel;
    private Button bOk;
    private Quote quote;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote_activity);
        Log.d(TAG, "DetailQuoteActivity - onCreate");

        tvQuote = (TextView) findViewById(R.id.tvDetailQuote);
        tvDateQuote = (TextView) findViewById(R.id.tvDetailDate);
        rbQuoteMark = (RatingBar) findViewById(R.id.rbDetailMark);
        bCancel = (Button) findViewById(R.id.bDetailCancel);
        bOk = (Button) findViewById(R.id.bDetailOk);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            quote = (Quote) extras.get("quote");
            position = extras.getInt("position");
            tvQuote.setText(quote.getStrQuote());
            tvDateQuote.setText(quote.getCreationDate().toString());
            rbQuoteMark.setRating(quote.getRating());
        }

        bCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        bOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getIntent().putExtra("rating", (int)rbQuoteMark.getRating());
                getIntent().putExtra("position", position);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
    }
}
