package com.e.study1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {

    private Button btn_1,btn_2,btn_3;
    private ImageView iv_1;
    private ProgressBar pb_1;
    private TextView per_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);

        iv_1 = findViewById(R.id.iv_1);

        pb_1 = findViewById(R.id.pb_1);
        per_tv = findViewById(R.id.percent_tv);

        btn_set();
        /*btn_1.setOnClickListener(view -> {
            ExampleTask task = new ExampleTask();
            task.execute();
        });*/
    }


    private void btn_set(){
        btn_1.setText("load");
        btn_2.setText("image");
        btn_3.setText("clear");
        btn_1.setOnClickListener(new BtnClick());
        btn_2.setOnClickListener(new BtnClick());
        btn_3.setOnClickListener(new BtnClick());
    }
    class BtnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int id = view.getId();
            String url = null;
            switch (id){
                case R.id.btn_1:
                    ExampleTask task = new ExampleTask();
                    task.execute();
                    break;
                case R.id.btn_2:
                    url = "https://source.unsplash.com/random";
                    imageSet(url,iv_1);
                    break;
                case R.id.btn_3:
                    ClearView();
                    break;
            }
        }

        public void imageSet(String url, ImageView iv){
            new ImageLoadTask(url,iv).execute();
        }
        public void ClearView(){pb_1.setProgress(0); per_tv.setText("");iv_1.setImageBitmap(null);}
    }
    public class ExampleTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            pb_1.setMax(100) ;
            pb_1.setProgress(0) ;
            per_tv.setText("0%");
        }
        @Override
        protected Boolean doInBackground(Void ...voids) {

            int len = 0 ;

            try {
                while (len < 100) {
                    len++;
                    publishProgress(len) ;
                    Thread.sleep(10) ;
                }
                Thread.sleep(500) ;
            } catch (Exception e) {
                e.printStackTrace() ;
            }

            return (len==100);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int percent = values[0];
            pb_1.setProgress(percent);
            per_tv.setText(percent+"%");
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
                per_tv.setText("good") ;
            else
                per_tv.setText("bad") ;
        }

    }
}