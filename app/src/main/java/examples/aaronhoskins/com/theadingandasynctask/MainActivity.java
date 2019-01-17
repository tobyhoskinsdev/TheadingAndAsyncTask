package examples.aaronhoskins.com.theadingandasynctask;

import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    TextView tvDisplayAsyncResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplayAsyncResults = findViewById(R.id.tvAsyncResult);


        //sampleTheading();
        //sampleRunnable();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
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

    public void onClick(View view) {
        SampleAsyncTask sampleAsyncTask = new SampleAsyncTask(/*tvDisplayAsyncResults*/);
        sampleAsyncTask.execute();
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TaskEvent event){
        if(event != null) {
            String passedEventString = event.getMessage();
            tvDisplayAsyncResults.setText(passedEventString);
        }
    }

}
