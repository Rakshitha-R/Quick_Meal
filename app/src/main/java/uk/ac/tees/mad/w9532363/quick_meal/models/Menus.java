package uk.ac.tees.mad.w9532363.quick_meal.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Menus implements Parcelable {

    private String url;
    private int total_in_the_cart;
    private String name;
    private float price;

    public int getTotal_in_the_cart() {
        return total_in_the_cart;
    }

    public void setTotal_in_the_cart(int total_in_the_cart) {
        this.total_in_the_cart = total_in_the_cart;
    }

    protected Menus(Parcel in) {
        url = in.readString();
        total_in_the_cart = in.readInt();
        name = in.readString();
        price = in.readFloat();
    }


    public static final Creator<Menus> CREATOR = new Creator<Menus>() {
        @Override
        public Menus createFromParcel(Parcel in) {
            return new Menus(in);
        }

        @Override
        public Menus[] newArray(int size) {
            return new Menus[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeInt(total_in_the_cart);
        parcel.writeString(name);
        parcel.writeFloat(price);
    }
}
