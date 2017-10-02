package app.cpe.mushroom.data.db;

import android.content.ContentValues;

import java.util.List;

import app.cpe.mushroom.data.dao.BakedDao;
import app.cpe.mushroom.data.dao.PlantDao;
import app.cpe.mushroom.data.entity.Baked;
import app.cpe.mushroom.data.entity.Plant;

/**
 * Created by DEV on 1/10/2560.
 */

public class HistoryDataSource extends BaseDataSource {

    HistoryDataSource(DatabaseHelper db) {
        super(db);
    }

    public long addPlant(List<PlantDao> plantDaos) {
        openTransaction();
        long result = -1;
        try {
            deleteTable(Plant.TABLE);
            for (PlantDao plantDao : plantDaos) {
                ContentValues values = new ContentValues();
                values.put(Plant.TEMP_LOG, plantDao.getPTempLog());
                values.put(Plant.HUM_LOG, plantDao.getPHumLog());
                values.put(Plant.TIME_STAMP, plantDao.getTimeStamp());
                result = database.insertOrThrow(Plant.TABLE, null, values);
            }
            transactionSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeTransaction();
        }
        return result;
    }

    public long addBaked(List<BakedDao> bakedDaos) {
        openTransaction();
        long result = -1;
        try {
            deleteTable(Baked.TABLE);
            for (BakedDao bakedDao : bakedDaos) {
                ContentValues values = new ContentValues();
                values.put(Baked.TEMP_LOG, bakedDao.getBTempLog());
                values.put(Baked.HUM_LOG, bakedDao.getBHumLog());
                values.put(Baked.TIME_STAMP, bakedDao.getTimeStamp());
                result = database.insertOrThrow(Baked.TABLE, null, values);
            }
            transactionSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeTransaction();
        }
        return result;
    }
}
