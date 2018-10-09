package com.example.admin.asyncfile;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;
//   PDFView pdfView;
    Context context;
    Uri uri = null;
    String url;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        pdfView = findViewById(R.id.text);

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webView.loadUrl("https://developer.android.com/");
        webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=https://www.tutorialspoint.com/java/java_tutorial.pdf");
//        http://drive.google.com/viewerng/viewer?embedded=true&url=
        /*url = "https://www.tutorialspoint.com/java/java_tutorial.pdf";
        new DownloadImage().execute(url);*/
    }

    class DownloadImage extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Download Image Tutorial");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }


        @Override
       protected String doInBackground(String... url) {
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();

                System.out.println("Downloading");
                URL urls = new URL(url[0]);

                URLConnection conection = urls.openConnection();
                conection.connect();
                int lenghtOfFile = conection.getContentLength();
                InputStream input = new BufferedInputStream(urls.openStream(), 8192);
              //  OutputStream output = new FileOutputStream(root+"/downloadedfile.exe");
                File storagePath = Environment.getExternalStorageDirectory();
                OutputStream os = new FileOutputStream(new File(storagePath,"names.pdf"));
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    }
                    input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String file_url) {
            System.out.println("Downloaded");
            try {
                uri = Uri.parse(url);
//                pdfView.fromUri(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mProgressDialog.dismiss();

        }

    }
}