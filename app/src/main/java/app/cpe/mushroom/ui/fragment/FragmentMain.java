package app.cpe.mushroom.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import java.util.List;

import app.cpe.mushroom.R;
import app.cpe.mushroom.base.BaseFragment;
import app.cpe.mushroom.data.dao.BakedDao;
import app.cpe.mushroom.data.dao.PlantDao;
import app.cpe.mushroom.data.db.Db;
import app.cpe.mushroom.manager.HttpManager;
import app.cpe.mushroom.ui.activity.MainActivity;
import app.cpe.mushroom.utils.LogUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        mainActivity.setUpToolBar(TAG,false);
        getAllLogFromServer();
    }

    @OnClick(R.id.btnBaked)
    public void setOnClickBtnBaked() {
        mainActivity.switchFragment(FragmentBaked.newInstance(),FragmentBaked.TAG);
    }

    @OnClick(R.id.btnPlant)
    public void setOnClickBtnPlant() {
        mainActivity.switchFragment(FragmentPlant.newInstance(),FragmentPlant.TAG);
    }

    @OnClick(R.id.btnStatus)
    public void setOnClickBtnStatus() {
        mainActivity.switchFragment(FragmentStatus.newInstance(),FragmentStatus.TAG);
    }

    @OnClick(R.id.btnHistory)
    public void setOnClickBtnHistory() {
        mainActivity.switchFragment(FragmentHistory.newInstance(),FragmentHistory.TAG);
    }

    private void getAllLogFromServer() {
        Observable<Long> call1 = HttpManager.getInstatance().getService().getBakedLog().subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<List<BakedDao>, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(List<BakedDao> bakedDaos) {
                        return Observable.fromCallable(() -> Db.getInstance().getHistoryDataSource().addBaked(bakedDaos));
                    }
                });

        Observable<Long> call2 = HttpManager.getInstatance().getService().getPlantLog().subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<List<PlantDao>, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(List<PlantDao> plantDaos) {
                        return Observable.fromCallable(() -> Db.getInstance().getHistoryDataSource().addPlant(plantDaos));
                    }
                });

        Observable.concat(call1, call2).last()
                .subscribe(
                        aLong -> LogUtil.D("Add baked plant success %d", aLong),
                        e -> e.printStackTrace()
                );

    }
}
