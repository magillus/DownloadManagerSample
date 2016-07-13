package com.matsdevelopsolutions.downloadmanagersample;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);

        long downloadId = getIntent().getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);

        label.setText(String.format("Id clicked: %d", downloadId));
    }
}
