package com.matsdevelopsolutions.downloadmanagersample;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_10mb)
    Button download10mb;

    @BindView(R.id.but_50mb)
    Button download50mb;

    @BindView(R.id.but_50m_completed)
    Button getDownload50mbCompleted;

    @BindView(R.id.status_line)
    TextView statusLine;

    long activeDownloadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        download10mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse("http://perlak.com/work/10MB.zip"));
                request.setTitle("10MB hidden download ");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
                startDownload(request);
            }
        });

        download50mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse("http://perlak.com/work/50MB.zip"));
                request.setTitle("50MB standard ");
                request.setDescription("50 download - visible active download notification");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                startDownload(request);
            }
        });

        getDownload50mbCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse("http://perlak.com/work/50MB.zip"));
                request.setTitle("50MB standard ");
                request.setDescription("50 download - all visible");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                startDownload(request);
            }
        });

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                statusLine.setText(String.format("download completed  id= %d ", downloadId));
            }
        }, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    private void startDownload(DownloadManager.Request request) {
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        activeDownloadId = downloadManager.enqueue(request);
        statusLine.setText(String.format("Download started id = %d", activeDownloadId));
    }


}
