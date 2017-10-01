package app.cpe.mushroom.data.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DEV on 1/10/2560.
 */

public class BakedDao implements Parcelable {


    /**
     * timeStamp : 2017-09-30 14:25:38
     * b_temp_log : 44
     * b_hum_log : 88
     */



    @SerializedName("timeStamp")
    private String timeStamp;
    @SerializedName("b_temp_log")
    private String bTempLog;
    @SerializedName("b_hum_log")
    private String bHumLog;

    private BakedDao(Parcel in) {
        timeStamp = in.readString();
        bTempLog = in.readString();
        bHumLog = in.readString();
    }

    public static final Creator<BakedDao> CREATOR = new Creator<BakedDao>() {
        @Override
        public BakedDao createFromParcel(Parcel in) {
            return new BakedDao(in);
        }

        @Override
        public BakedDao[] newArray(int size) {
            return new BakedDao[size];
        }
    };

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getBTempLog() {
        return bTempLog;
    }

    public void setBTempLog(String bTempLog) {
        this.bTempLog = bTempLog;
    }

    public String getBHumLog() {
        return bHumLog;
    }

    public void setBHumLog(String bHumLog) {
        this.bHumLog = bHumLog;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(timeStamp);
        parcel.writeString(bTempLog);
        parcel.writeString(bHumLog);
    }
}
