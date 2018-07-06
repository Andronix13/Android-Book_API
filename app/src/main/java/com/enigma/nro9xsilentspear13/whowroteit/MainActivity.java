package com.enigma.nro9xsilentspear13.whowroteit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.book_input);
        textView1 = findViewById(R.id.titleText);
        textView2 = findViewById(R.id.author_text);

    }

    public void searchBooks(View view) {
        String queryString = editText.getText().toString();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && queryString.length()!=0) {
            new FetchBook(textView1, textView2).execute(queryString);
            textView2.setText("");
            textView1.setText(R.string.loading);
        }
        else {
            if (queryString.length() == 0) {
                textView2.setText("");
                textView1.setText("Please enter a search term");
            } else {
                textView2.setText("");
                textView1.setText("Please check your network connection and try again.");
            }
        }
    }
}
