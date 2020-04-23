package com.example.homework13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    Menu menuFinal = null;
    WebView webView = null;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buttonSubmit = findViewById(R.id.submit_area);
        webView = findViewById(R.id.webView);
        editText = findViewById(R.id.editText);

        buttonSubmit.setOnCreateContextMenuListener(this);
        MainActivity.context=getApplicationContext();
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String Url)
            {
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, view, menuInfo);
        menuFinal = menu;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menuFinal = menu;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return menuChoice(item);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        return menuChoice(item);
    }
    private boolean menuChoice(MenuItem item) {

        webView.loadUrl(item.getTitle().toString());
        return false;
    }

    public void Submit(View view) {
        String webaddress = "http://"+editText.getText().toString();
        webView.loadUrl(webaddress);
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    public void BookMark(View view) {
        String webaddress = "http://"+editText.getText().toString();
        menuFinal.add(0,0,0,webaddress);
        editText.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
