package com.example.android.recyclerview.ui;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.recyclerview.R;

public class DetailActivity extends AppCompatActivity {


    private static final String BUNDLE_EXTRA = "BUNDLE_EXTRA";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";      // I used the string directly instead
    private static final String EXTRA_IMAGE_RED_ID = "EXTRA_IMAGE_RED_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRA);

        ((TextView)findViewById(R.id.quote_text)).setText(extras.getString(EXTRA_QUOTE));

        ((TextView)findViewById(R.id.quote_attribution)).setText(extras.getString("EXTRA_ATTR"));
        ((ImageView)findViewById(R.id.im_detail_image)).setImageDrawable(ContextCompat.getDrawable(this, extras.getInt(EXTRA_IMAGE_RED_ID)));
    }
}
