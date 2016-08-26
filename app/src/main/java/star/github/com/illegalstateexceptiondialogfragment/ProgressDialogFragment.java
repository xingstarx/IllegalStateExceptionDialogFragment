package star.github.com.illegalstateexceptiondialogfragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by xiongxingxing on 16/8/26.
 */

public class ProgressDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("处理中...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return dialog;
    }
}
