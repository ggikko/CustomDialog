package ggikko.me.customdialogpraticeapp.dialog;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;

/**
 * Created by ggikko on 2016. 11. 3..
 */

public class BaseDialog extends AppCompatDialogFragment {

    @Override
    public void show(FragmentManager manager, String tag) {
        Log.e("ggikko", "show");
        super.show(manager, tag);
    }

    @Override
    public void dismiss() {
        Log.e("ggikko", "dismiss");
        super.dismiss();
    }
}
