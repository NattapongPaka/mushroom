package app.cpe.mushroom.ui.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.cpe.mushroom.R;


/**
 * Created by Dev on 5/1/2560.
 */

public class CustomAlertDialog extends BaseDialog {

    public static final String TAG = CustomAlertDialog.class.getSimpleName();

    public static CustomAlertDialog newInstance() {
        return new CustomAlertDialog();
    }

    public CustomAlertDialog() {
        super();
    }

    @Override
    public View attachView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    public CustomAlertDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public CustomAlertDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public CustomAlertDialog setCancel(boolean cancel) {
        setCancelable(cancel);
        return this;
    }

    @Override
    public Dialog createDialog(Bundle savedInstanceState) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setCancelable(isCancelable());
        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);
        if (onPositiveClickListener != null) {
            builder.setPositiveButton(btnPostText != null ? btnPostText : getString(R.string.ok),
                    (dialogInterface, i) -> onPositiveClickListener.onPositiveClickListener(dialogInterface, i));
        }
        if (onNegativeClickListener != null) {
            builder.setNegativeButton(btnNegText != null ? btnNegText : getString(R.string.cancel),
                    (dialogInterface, i) -> onNegativeClickListener.onNegativeClickListener(dialogInterface, i));
        }
        dialog = builder.create();
        return dialog;
    }

    @Override
    public Bundle saveInstanceState(Bundle outState) {
        Bundle bundle = new Bundle();
        bundle.putString("title", this.title);
        bundle.putString("message", this.message);
        outState.putBundle("Data", bundle);
        return bundle;
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {
        Bundle bundle = savedInstanceState.getBundle("Data");
        if (bundle != null) {
            this.title = bundle.getString("title");
            this.message = bundle.getString("message");
        }
    }

    @Override
    public void viewCreate(View view, Bundle savedInstanceState) {

    }

    @Override
    public void destroyView() {

    }

    public void show() {
        super.show(getActivity().getSupportFragmentManager(), TAG);
    }

    public CustomAlertDialog setOnPositiveClickListener(String btnPostText, BaseDialog.OnPositiveClickListener onPositiveClickListener) {
        this.onPositiveClickListener = onPositiveClickListener;
        this.btnPostText = btnPostText;
        return this;
    }

    public CustomAlertDialog setOnNegativeClickListener(String btnNegText, BaseDialog.OnNegativeClickListener onNegativeClickListener) {
        this.onNegativeClickListener = onNegativeClickListener;
        this.btnNegText = btnNegText;
        return this;
    }
}
