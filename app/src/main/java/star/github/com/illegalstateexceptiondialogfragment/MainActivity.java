package star.github.com.illegalstateexceptiondialogfragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static star.github.com.illegalstateexceptiondialogfragment.R.id.execute;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(execute);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProgressTask();
            }
        });
    }

    private void startProgressTask() {
        new ProgressTask().execute();
    }


    private static final String TAG_PROGRESS = "progress";

    private void showProgress() {
        DialogFragment dialog = new ProgressDialogFragment();
        dialog.show(getSupportFragmentManager(), TAG_PROGRESS);

        // 例外対策
        // IllegalStateException: Can not perform this action after onSaveInstanceState
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.add(dialog, TAG_PROGRESS);
//        ft.commitAllowingStateLoss();
    }

    private void hideProgress() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_PROGRESS);
        if (fragment == null || !(fragment instanceof DialogFragment)) {
            return;
        }
//        ((DialogFragment) fragment).dismissAllowingStateLoss();
        ((DialogFragment) fragment).dismiss();
    }

    private class ProgressTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            showProgress();
        }

        @Override
        protected void onPostExecute(Void result) {
            hideProgress();
        }
    }
}
