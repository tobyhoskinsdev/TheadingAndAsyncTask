package examples.aaronhoskins.com.theadingandasynctask;

import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;
//import android.widget.TextView;

public class SampleAsyncTask extends AsyncTask<String, String,String> {
//    TextView tvPassedTextView;

    public SampleAsyncTask(/*TextView tvPassedTextView*/) {
//        this.tvPassedTextView = tvPassedTextView;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
//        tvPassedTextView.setText("Seconds Ran" + values[0]);
        EventBus.getDefault().post(new TaskEvent("Seconds Ran" + values[0]));

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
 //       tvPassedTextView.setText("Setting up task to run");
        EventBus.getDefault().post("Setting up task to run");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        int counter = 0;
        while(counter != 15) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
            publishProgress(String.valueOf(counter));
        }
        return "Task Completed.  Run Time = " + counter;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
//        tvPassedTextView.setText(s);
        EventBus.getDefault().post(new TaskEvent(s));
    }
}
