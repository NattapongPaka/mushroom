package app.cpe.mushroom.data.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by DEV on 1/10/2560.
 */

public class RxResultDao implements Parcelable{

    private List<PlantDao> plantDaos;
    private List<BakedDao> bakedDaos;

    public RxResultDao(){};

    private RxResultDao(Parcel in) {
        plantDaos = in.createTypedArrayList(PlantDao.CREATOR);
        bakedDaos = in.createTypedArrayList(BakedDao.CREATOR);
    }

    public static final Creator<RxResultDao> CREATOR = new Creator<RxResultDao>() {
        @Override
        public RxResultDao createFromParcel(Parcel in) {
            return new RxResultDao(in);
        }

        @Override
        public RxResultDao[] newArray(int size) {
            return new RxResultDao[size];
        }
    };

    public List<PlantDao> getPlantDaos() {
        return plantDaos;
    }

    public RxResultDao setPlantDaos(List<PlantDao> plantDaos) {
        this.plantDaos = plantDaos;
        return this;
    }

    public List<BakedDao> getBakedDaos() {
        return bakedDaos;
    }

    public RxResultDao setBakedDaos(List<BakedDao> bakedDaos) {
        this.bakedDaos = bakedDaos;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(plantDaos);
        parcel.writeTypedList(bakedDaos);
    }
}
