package app.cpe.mushroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.cpe.mushroom.base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by DEV on 20/9/2560.
 */

public class FragmentMain extends BaseFragment {

    public static final String TAG = FragmentMain.class.getSimpleName();

    @BindView(R.id.btnBaked)
    Button btnBaked;
    @BindView(R.id.btnPlant)
    Button btnPlant;
    @BindView(R.id.btnStatus)
    Button bnStatus;
    @BindView(R.id.btnHistory)
    Button btnHistory;

    Unbinder unbinder;

    MainActivity mainActivity;

    public static FragmentMain newInstance() {
        return new FragmentMain();
    }

    @Override
    public int setupLayout() {
        return R.layout.layout_main;
    }

    @Override
    public void bindView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        mainActivity.setUpToolBar(TAG,false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnBaked)
    public void setOnClickBtnBaked() {
        mainActivity.switchFragment(FragmentBaked.newInstance(),FragmentBaked.TAG);
    }

    @OnClick(R.id.btnPlant)
    public void setOnClickBtnPlant() {

    }

    @OnClick(R.id.btnStatus)
    public void setOnClickBtnStatus() {

    }

    @OnClick(R.id.btnHistory)
    public void setOnClickBtnHistory() {

    }
}
