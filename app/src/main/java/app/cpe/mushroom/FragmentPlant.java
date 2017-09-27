package app.cpe.mushroom;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.cpe.mushroom.base.BaseFragment;
import app.cpe.mushroom.manager.HttpManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DEV on 20/9/2560.
 */

public class FragmentPlant extends BaseFragment {

    public static final String TAG = FragmentPlant.class.getSimpleName();

    @BindView(R.id.txt_dd)
    TextView txtDd;
    @BindView(R.id.txt_hh)
    TextView txtHh;
    @BindView(R.id.txt_mm)
    TextView txtMm;
    @BindView(R.id.edt_dd)
    EditText edtDd;
    @BindView(R.id.edt_hh)
    EditText edtHh;
    @BindView(R.id.edt_mm)
    EditText edtMm;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.txt_temp)
    TextView txtTemp;
    @BindView(R.id.txt_humidity)
    TextView txtHumidity;
    @BindView(R.id.edt_temp)
    EditText edtTemp;
    @BindView(R.id.edt_humidity)
    EditText edtHumidity;
    @BindView(R.id.btn_start)
    Button btnStart;

    MainActivity mainActivity;

    String p_temp = "0";
    String p_hum = "0";
    String p_dd = "0";
    String p_hh = "0";
    String p_mm = "0";

    public static FragmentPlant newInstance() {
        return new FragmentPlant();
    }

    @Override
    public int setupLayout() {
        return R.layout.layout_plant;
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
        edtDd.setText(p_dd);
        edtHh.setText(p_hh);
        edtMm.setText(p_mm);
        edtTemp.setText(p_temp);
        edtHumidity.setText(p_hum);
    }

    @OnClick(R.id.btn_start)
    public void setBtnStartOnClick() {
        HttpManager.getInstatance().getService().addPlant(p_temp, p_hum, p_dd, p_hh, p_mm)
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
