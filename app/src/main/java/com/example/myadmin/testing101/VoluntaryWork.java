package com.example.myadmin.testing101;

import android.os.Parcel;
import android.os.Parcelable;

public class VoluntaryWork implements Parcelable {


    private String ShortDesc;
    private String Date;
    private String Pic_Url;
    private String BigDesc;
    private String Description;

    public VoluntaryWork(){}

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public VoluntaryWork(String ShortDesc, String Pic_Url , String BigDesc, String Date, String Description)
    {
        this.ShortDesc=ShortDesc;
        this.Date=Date;
        this.Pic_Url=Pic_Url;
        this.BigDesc=BigDesc;
        this.Description=Description;

    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getShortDesc() {
        return ShortDesc;
    }

    public void setShortDesc(String shortDesc) {
        ShortDesc = shortDesc;
    }

    public String getPic_Url() {
        return Pic_Url;
    }

    public void setPic_Url(String pic_Url) {
        Pic_Url = pic_Url;
    }

    public String getBigDesc() {
        return BigDesc;
    }

    public void setBigDesc(String bigDesc) {
        BigDesc = bigDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ShortDesc);
        dest.writeString(BigDesc);
        dest.writeString(Date);
        dest.writeString(Pic_Url);
    }

    public static final Parcelable.Creator<VoluntaryWork> CREATOR = new Parcelable.Creator<VoluntaryWork>() {
        public VoluntaryWork createFromParcel(Parcel in) {
            return new VoluntaryWork(in);
        }

        public VoluntaryWork[] newArray(int size) {
            return new VoluntaryWork[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private VoluntaryWork(Parcel in) {
        ShortDesc = in.readString();
        BigDesc = in.readString();
        Pic_Url = in.readString();
        Date = in.readString();
    }
}
