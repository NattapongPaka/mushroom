package app.cpe.mushroom.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import java.util.List;

import app.cpe.mushroom.R;
import app.cpe.mushroom.base.BaseFragment;
import app.cpe.mushroom.data.Constance;
import app.cpe.mushroom.data.dao.BakedDao;
import app.cpe.mushroom.data.dao.PlantDao;
import app.cpe.mushroom.data.db.Db;
import app.cpe.mushroom.manager.HttpManager;
import app.cpe.mushroom.ui.activity.MainActivity;
import app.cpe.mushroom.ui.widget.CustomProgressDialog;
import app.cpe.mushroom.utils.LogUtil;
import app.cpe.mushroom.utils.PreferenceUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Notification;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

    MainActivity mainActivity;

    public static FragmentMain newInstance() {
        return new FragmentMain();
    }

    @Override
    public int setupLayout() {
        return R.layout.layout_main;
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
        mainActivity.setUpToolBar(TAG, false);
        Boolean isAllData = (Boolean) PreferenceUtil.getInstance().getValue(Constance.IS_ALL_DATA);
        if (isAllData == null || !isAllData) {
            getAllLogFromServer();
        }
    }

    @OnClick(R.id.btnBaked)
    public void setOnClickBtnBaked() {
        mainActivity.switchFragment(FragmentBaked.newInstance(), FragmentBaked.TAG);
    }

    @OnClick(R.id.btnPlant)
    public void setOnClickBtnPlant() {
        mainActivity.switchFragment(FragmentPlant.newInstance(), FragmentPlant.TAG);
    }

    @OnClick(R.id.btnStatus)
    public void setOnClickBtnStatus() {
        mainActivity.switchFragment(FragmentStatus.newInstance(), FragmentStatus.TAG);
    }

    @OnClick(R.id.btnHistory)
    public void setOnClickBtnHistory() {
        mainActivity.switchFragment(FragmentHistory.newInstance(), FragmentHistory.TAG);
    }

    private void getAllLogFromServer() {
        Observable<Long> call1 = HttpManager.getInstatance().getService().getBakedLog().subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap(bakedDaos -> Observable.fromCallable(() -> Db.getInstance().getHistoryDataSource().addBaked(bakedDaos)));

        Observable<Long> call2 = HttpManager.getInstatance().getService().getPlantLog().subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap(plantDaos -> Observable.fromCallable(() -> Db.getInstance().getHistoryDataSource().addPlant(plantDaos)));

        Observable.concat(call1, call2).last()
                .doOnSubscribe(() -> showProgressDialog())
                .doOnCompleted(() -> dismissProgressDialog())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            showToast("Load data complete");
                            PreferenceUtil.getInstance().setSharedPreference(Constance.IS_ALL_DATA, true);
                            LogUtil.D("Last result %d", result.longValue());
                        },
                        t -> t.printStackTrace()
                );
    }
}
