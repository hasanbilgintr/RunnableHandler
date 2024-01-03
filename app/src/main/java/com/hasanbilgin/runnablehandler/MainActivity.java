package com.hasanbilgin.runnablehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    int number;
    Runnable runnable;
    Handler handler;
    Button startButton, stopButton, pauseButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        pauseButton = findViewById(R.id.pauseButton);
        number = 0;
    }

    public void startOnClick(View view) {
//        while (number < 10) {
//            textView.setText("Time: " + number);
//            number++;
//            textView.setText("Time: " + number);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        /*amaç başla diyince sayıcak stop diyince 0lancak algoritma lazım
        bu  Thread.sleep(1000); viewdeki hiç bişeye dokunulmaz olucaktır yani bu kod bitmeden hiç bir işlem yaptırtmıcaktır dokunmaya kalksan threed sonunda hata verir bundan dolayı
        bize threed arka tarafta işlem yapsın bizde görüntüdede dokunmamıza olanak sağlaması için runnable kullanıcaz*/

        handler = new Handler(Looper.myLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText("Time: " + number);
                number++;
                textView.setText("Time: " + number);
                handler.postDelayed(runnable, 1000);//1000ms de bu metot çalışçak
            }
        };
        handler.post(runnable);//çalıştırması
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        pauseButton.setEnabled(true);
        startButton.setText("Başla");
    }

    public void duraklatOnClick(View view) {
        handler.removeCallbacks(runnable);//durdurması

        startButton.setEnabled(true);
        stopButton.setEnabled(true);
        pauseButton.setEnabled(false);
        startButton.setText("Devam Et");


    }

    public void stopOnClick(View view) {
        handler.removeCallbacks(runnable);
        number = 0;
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
       pauseButton.setEnabled(false);
        textView.setText("Time: " + number);
        startButton.setText("Başla");

    }

}