package com.example.admin.threadsexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button imageabutton, ToastButton;
    ImageView image;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageabutton = findViewById(R.id.imageview);
        ToastButton = findViewById(R.id.toastbutton);

        image = findViewById(R.id.imageView);

        progressBar = findViewById(R.id.progressBar);


        imageabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ToastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"i am in toast",Toast.LENGTH_SHORT).show();
            }
        });

        imageabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImage();
                new LoadIconText().execute(R.drawable.jimmy);
            }
        });
    }


    private void loadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.jimmy);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        image.setImageBitmap(bitmap);


                    }
                });



            }
        }).start();
    }


    class LoadIconText extends AsyncTask<Integer,Integer,Bitmap>{


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            image.setImageBitmap(bitmap);
            progressBar.setVisibility(progressBar.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(ProgressBar.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Integer... integers) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.jimmy);
            return null;
        }
    }
}
