package app.cpe.mushroom.data;

import java.util.List;

import app.cpe.mushroom.data.dao.BakedDao;
import app.cpe.mushroom.data.dao.PlantDao;
import app.cpe.mushroom.manager.ApiService;
import app.cpe.mushroom.manager.HttpManager;
import rx.Observable;

/**
 * Created by DEV on 1/10/2560.
 */

public class AppDataManager implements ApiService, DataManager.Pref, DataManager.Db {
    public static AppDataManager instance;

    public static AppDataManager getInstance() {
        if (instance == null) {
            instance = new AppDataManager();
        }
        return instance;
    }

    @Override
    public Observable<String> addTemp(String temp, String hum) {
        return null;
    }

    @Override
    public Observable<String> addBaked(String b_temp, String b_hum, String b_hh, String b_mm) {
        return null;
    }

    @Override
    public Observable<String> addPlant(String p_temp, String p_hum, String p_dd, String p_hh, String p_mm) {
        return null;
    }

    @Override
    public Observable<List<PlantDao>> getPlantLog() {
        return HttpManager.getInstatance().getService().getPlantLog();
    }

    @Override
    public Observable<List<BakedDao>> getBakedLog() {
        return null;
    }
}
