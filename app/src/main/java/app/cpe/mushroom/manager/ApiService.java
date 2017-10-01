package app.cpe.mushroom.manager;

import java.util.List;

import app.cpe.mushroom.data.dao.BakedDao;
import app.cpe.mushroom.data.dao.PlantDao;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by PONG on 10/7/2016 AD.
 */

public interface ApiService {

    @GET("process/test_add.php")
    Observable<String> addTemp(@Query("temp1") String temp,
                               @Query("hum1") String hum);

    @GET("process/add_baked.php")
    Observable<String> addBaked(@Query("b_temp") String b_temp,
                                @Query("b_hum") String b_hum,
                                @Query("b_hh") String b_hh,
                                @Query("b_mm") String b_mm);

    @GET("process/add_plant.php")
    Observable<String> addPlant(@Query("p_temp") String p_temp,
                                @Query("p_hum") String p_hum,
                                @Query("p_dd") String p_dd,
                                @Query("p_hh") String p_hh,
                                @Query("p_mm") String p_mm);

    @GET("json/plant_log.php")
    Observable<List<PlantDao>> getPlantLog();

    @GET("json/baked_log.php")
    Observable<List<BakedDao>> getBakedLog();

}
