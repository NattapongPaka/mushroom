package app.cpe.mushroom.ui.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nattapongpaka on 9/4/2017 AD.
 */

public abstract class BaseDialog extends AppCompatDialogFragment {

    protected String title;
    protected String message;
    protected Dialog dialog;
    protected String btnPostText;
    protected String btnNegText;
    protected OnPositiveClickListener onPositiveClickListener;
    protected OnNegativeClickListener onNegativeClickListener;

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAllowEnterTransitionOverlap(true);
        setAllowReturnTransitionOverlap(true);
        getActivity().getSupportFragmentManager().beginTransaction().commitAllowingStateLoss();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = attachView(inflater, container, savedInstanceState);
        if (view != null) {
            return view;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewCreate(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroyView();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
        Dialog dialog = createDialog(savedInstanceState);
        if (dialog != null) {
            return dialog;
        } else {
            return super.onCreateDialog(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Bundle bundle = saveInstanceState(outState);
        if (bundle != null) {
            super.onSaveInstanceState(bundle);
        }
    }

    private void onRestoreInstanceState(Bundle savedInstanceState) {
        restoreInstanceState(savedInstanceState);
    }

    public abstract View attachView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract Dialog createDialog(Bundle savedInstanceState);

    public abstract Bundle saveInstanceState(Bundle outState);

    public abstract void restoreInstanceState(Bundle savedInstanceState);

    public abstract void viewCreate(View view, Bundle savedInstanceState);

    public abstract void destroyView();

    public interface OnPositiveClickListener {
        void onPositiveClickListener(DialogInterface dialogInterface, int i);
    }

    public interface OnNegativeClickListener {
        void onNegativeClickListener(DialogInterface dialogInterface, int i);
    }
}
