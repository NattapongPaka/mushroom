package app.cpe.mushroom;

import android.content.Context;
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
import android.widget.Toast;

import java.util.logging.Logger;

import app.cpe.mushroom.base.BaseFragment;
import app.cpe.mushroom.manager.HttpManager;
import app.cpe.mushroom.utils.LogUtil;
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

    @BindView(R.id.edt_hh)
    EditText edtHh;
    @BindView(R.id.edt_mm)
    EditText edtMm;
    @BindView(R.id.edt_temp)
    EditText edtTemp;
    @BindView(R.id.edt_humidity)
    EditText edtHumidity;
    @BindView(R.id.btn_start)
    Button btnStart;

    MainActivity mainActivity;

    private String temp = "0";
    private String humidity = "0";
    private String hh = "0";
    private String mm = "0";

    public static FragmentBaked newInstance() {
        return new FragmentBaked();
    }

    @Override
    public int setupLayout() {
        return R.layout.layout_baked;
    }

    @Override
    public Unbinder bindView(View view) {
        return ButterKnife.bind(this, view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setUpToolBar(TAG, true);
        initView();
    }

    private void initView() {
        edtTemp.setText(temp);
        edtHumidity.setText(humidity);
        edtHh.setText(hh);
        edtMm.setText(mm);
    }

    @OnClick(R.id.btn_start)
    public void setBtnStartOnClick() {
        HttpManager.getInstatance().getService().addBaked(temp, humidity, hh, mm)
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
                        Toast.makeText(getContext(), "Result : " + s, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
