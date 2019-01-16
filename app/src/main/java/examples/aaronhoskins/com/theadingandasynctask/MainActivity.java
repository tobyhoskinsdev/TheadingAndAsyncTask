package examples.aaronhoskins.com.theadingandasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sampleTheading();
        sampleRunnable();
    }

    public void sampleTheading() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("RUNNING");
                    Thread.sleep(8000);
                    System.out.println("STOP");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        System.out.println("Starting");
        thread.start();


    }

    public void sampleRunnable() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("RUNNING");
                    Thread.sleep(8000);
                    System.out.println("STOP");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("Starting");
        thread.start();

    }
}
