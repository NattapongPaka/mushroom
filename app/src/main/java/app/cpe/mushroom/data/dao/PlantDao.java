package app.cpe.mushroom.data.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DEV on 1/10/2560.
 */

public class PlantDao implements Parcelable {

    /**
     * timeStamp : 2017-10-01 04:59:00
     * p_temp_log : 46
     * p_hum_log : 75
     */



    @SerializedName("timeStamp")
    private String timeStamp;
    @SerializedName("p_temp_log")
    private String pTempLog;
    @SerializedName("p_hum_log")
    private String pHumLog;

    private PlantDao(Parcel in) {
        timeStamp = in.readString();
        pTempLog = in.readString();
        pHumLog = in.readString();
    }

    public static final Creator<PlantDao> CREATOR = new Creator<PlantDao>() {
        @Override
        public PlantDao createFromParcel(Parcel in) {
            return new PlantDao(in);
        }

        @Override
        public PlantDao[] newArray(int size) {
            return new PlantDao[size];
        }
    };

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPTempLog() {
        return pTempLog;
    }

    public void setPTempLog(String pTempLog) {
        this.pTempLog = pTempLog;
    }

    public String getPHumLog() {
        return pHumLog;
    }

    public void setPHumLog(String pHumLog) {
        this.pHumLog = pHumLog;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(timeStamp);
        parcel.writeString(pTempLog);
        parcel.writeString(pHumLog);
    }
}
