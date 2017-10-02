package app.cpe.mushroom.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.cpe.mushroom.ui.widget.CustomProgressDialog;
import butterknife.Unbinder;

/**
 * Created by DEV on 20/9/2560.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutResId = setupLayout();
        if (layoutResId == 0) new RuntimeException("Null view");
        View view = inflater.inflate(layoutResId, container, false);
        unbinder = bindView(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected void showProgressDialog() {
        CustomProgressDialog.newInstance().show(getChildFragmentManager(), CustomProgressDialog.TAG);
    }

    protected void dismissProgressDialog() {
        Fragment fragment = getChildFragmentManager().findFragmentByTag(CustomProgressDialog.TAG);
        if (fragment != null) {
            CustomProgressDialog customProgressDialog = (CustomProgressDialog) fragment;
            customProgressDialog.dismissAllowingStateLoss();
        }
    }

    protected void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public abstract int setupLayout();

    public abstract Unbinder bindView(View view);
}
