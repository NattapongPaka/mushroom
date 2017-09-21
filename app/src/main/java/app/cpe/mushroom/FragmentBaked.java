package app.cpe.mushroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AndroidException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.TextView;

import java.util.logging.Logger;

import app.cpe.mushroom.base.BaseFragment;
import app.cpe.mushroom.manager.HttpManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DEV on 20/9/2560.
 */

public class FragmentBaked extends BaseFragment {

    public static final String TAG = FragmentBaked.class.getSimpleName();

    @BindView(R.id.txt_hh)
    TextView txtHh;
    @BindView(R.id.txt_mm)
    TextView txtMm;
    @BindView(R.id.edt_hh)
    EditText edtHh;
    @BindView(R.id.edt_mm)
    EditText edtMm;
    @BindView(R.id.space)
    Space space;
    @BindView(R.id.txt_temp)
    TextView txtTemp;
    @BindView(R.id.txt_humidity)
    TextView txtHumidity;
    @BindView(R.id.edt_temp)
    EditText edtTemp;
    @BindView(R.id.edt_humidity)
    EditText edtHumidity;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.btn_start)
    Button btnStart;

    Unbinder unbinder;

    MainActivity mainActivity;

    public static FragmentBaked newInstance() {
        return new FragmentBaked();
    }

    @Override
    public int setupLayout() {
        return R.layout.layout_baked;
    }

    @Override
    public void bindView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        mainActivity.setUpToolBar(TAG, true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_start)
    public void setBtnStartOnClick() {
        HttpManager.getInstatance().getService().addTemp("111", "222")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, s);
                    }
                });
    }
}
