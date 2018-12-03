package in.sourcecode.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView img2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        img2 = (ImageView) findViewById(R.id.imageView2);


        Thread t1 = new Thread() {

            @Override
            public void run() {
                super.run();
                try {

                    sleep(3000);


                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (InterruptedException e) {
                    e.getMessage();
                }

            }
        };
        t1.start();
    }


}

/*
  Thread myThread = new Thread() {
            @Override
            public void run() {

                try {
                    sleep(3000);

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        myThread.start();

    }
 */