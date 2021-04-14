package fr.mds.geekquote;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import fr.mds.geekquote.model.Quote;
import fr.mds.geekquote.model.QuoteListAdapter;
import android.widget.AdapterView;
import android.widget.Toast;


public class QuoteListActivity extends Activity {

    public static final String TAG = "GeekQuoteTAG";

    public static int numberOfItems = 0;
    public static ArrayList<Quote> listOfQuote = new ArrayList<>();
    public static ArrayAdapter<Quote> QuoteListAdapter;
    public static ClipData.Item tvQuoteListActivity;
    //  private EditText editText =  findViewById(R.id.etQuoteListActivity);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvQuoteListActivity);

        // Create the adapter to convert the array to views
        QuoteListAdapter = new QuoteListAdapter(this, listOfQuote);
        listView.setAdapter(QuoteListAdapter);

        String[] arrayOfQuotes = getResources().getStringArray(R.array.array_of_quotes);
        for (int i = 0; i < arrayOfQuotes.length; i++){
            this.addQuote(arrayOfQuotes[i]);
            Log.d(TAG, arrayOfQuotes[i]);
        // Toast.makeText(this, arrayOfQuotes[i], Toast.LENGTH_LONG).show();
        }

        Button button =(Button)  findViewById(R.id.btnQuoteListActivity);
        EditText editText =  findViewById(R.id.etQuoteListActivity);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String wroteText = editText.getText().toString();
                QuoteListActivity.this.addQuote(wroteText);
                editText.setText("");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(QuoteListActivity.this, listOfQuote.get(position).getStrQuote(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QuoteListActivity.this, QuoteActivity.class);
                Quote quoteToDisplay = listOfQuote.get(position);
                Log.d(TAG,  "Quote list: " + listOfQuote);
                Log.d(TAG,  "Quote toDisplay: " + listOfQuote);

                intent.putExtra("quote",  quoteToDisplay);
                intent.putExtra("position", position);
                startActivityForResult(intent,0);
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    protected void addQuote(String strQuote){
        numberOfItems++;
        LinearLayout theLayout =  this.findViewById(R.id.linearlayoutQuoteListActivityV);
        Random r = new Random();
        int low = 0;
        int high = 5;
        int randomNumber = r.nextInt(high-low) + low;
        Quote myQuote = new Quote(strQuote, randomNumber , Calendar.getInstance().getTime(), numberOfItems);
        listOfQuote.add(myQuote);
        QuoteListAdapter.notifyDataSetChanged();
        // TextView textView = new TextView(this);//
        // textView.setText(myQuote.getStrQuote());
        // theLayout.addView(textView);
        // ListView listView =  (ListView) findViewById(R.id.lvQuoteListActivity);
        // ArrayAdapter<Quote> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfQuote);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle bundle = data.getExtras();
        if(bundle != null){
            if(resultCode == QuoteActivity.RESULT_YES){
                listOfQuote.get(bundle.getInt("position")).setRating(bundle.getInt("newRating"));
            } else if (resultCode == QuoteActivity.RESULT_NO) {
                Toast.makeText(this,bundle.getString("msgCancel"), Toast.LENGTH_LONG).show();
                Log.d(TAG,  bundle.getString("msgCancel"));
            }
        }
    }

}
