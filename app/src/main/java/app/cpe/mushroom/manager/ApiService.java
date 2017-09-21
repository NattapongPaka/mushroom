package app.cpe.mushroom.manager;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by PONG on 10/7/2016 AD.
 */

public interface ApiService {

    @GET("/add.php")
    Observable<String> addTemp(@Query("temp1") String temp,@Query("hum1") String hum);

}
