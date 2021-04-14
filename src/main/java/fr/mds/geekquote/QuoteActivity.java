package fr.mds.geekquote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import fr.mds.geekquote.model.Quote;

public class QuoteActivity extends Activity implements View.OnClickListener {
    public static final String TAG = "GeekQuoteTAG";

    private Button btnActivityQuoteCancel;
    private Button buttonOk;
    private RatingBar ratingBar;
    private int newRating;
    private int position;

    public static final int RESULT_YES = 2;
    public static final int RESULT_NO = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        //Get Data from bundle
        Bundle bundle = getIntent().getExtras();
        Quote quote = (Quote) bundle.getSerializable("quote");
        position = bundle.getInt("position");

        //Get View Elements
        TextView quoteTextView =  findViewById(R.id.tvActivityQuoteText);
        TextView dateQuote = findViewById(R.id.tvActivityQuoteDate);
        ratingBar = findViewById(R.id.rbRatingQuote);
        buttonOk =  findViewById(R.id.btnActivityQuoteOk);
        btnActivityQuoteCancel =  findViewById(R.id.btnActivityQuoteCancel);

        //Apply data from bundle to view
        dateQuote.setText("written on " + quote.getCreationDate());
        ratingBar.setRating(quote.getRating());
        quoteTextView.setText(quote.getStrQuote());

        //Get new rating
        newRating = quote.getRating();


        buttonOk.setOnClickListener(this);
        btnActivityQuoteCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        if(v == btnActivityQuoteCancel)  {
            intent.putExtra("msgCancel", "Pas de changement !");
            setResult(QuoteActivity.RESULT_NO, intent);
        } else if (v == buttonOk){
            int newRatingToInt = (int) ratingBar.getRating();
            intent.putExtra("newRating", newRatingToInt);
            intent.putExtra("position", position);

            setResult(QuoteActivity.RESULT_YES, intent);
        }

        finish();
    }
}
