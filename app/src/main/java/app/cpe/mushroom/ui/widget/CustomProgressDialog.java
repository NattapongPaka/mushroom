package app.cpe.mushroom.ui.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import app.cpe.mushroom.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Dev on 30/12/2559.
 */

public class CustomProgressDialog extends BaseDialog {

    public static final String TAG = CustomProgressDialog.class.getSimpleName();

    @BindView(R.id.progress)
    ProgressBar progress;

    Unbinder unbinder1;

    private Unbinder unbinder;

    public static CustomProgressDialog newInstance() {
        return new CustomProgressDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.MyDialog);
    }

    @Override
    public View attachView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.progress_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public Dialog createDialog(Bundle savedInstanceState) {
        return null;
    }

    @Override
    public Bundle saveInstanceState(Bundle outState) {
        return null;
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void viewCreate(View view, Bundle savedInstanceState) {
        progress.setInterpolator(new DecelerateInterpolator());
    }

    @Override
    public void destroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
