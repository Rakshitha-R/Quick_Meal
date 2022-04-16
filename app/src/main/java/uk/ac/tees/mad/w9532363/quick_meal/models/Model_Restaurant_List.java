package uk.ac.tees.mad.w9532363.quick_meal.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Model_Restaurant_List implements Parcelable{

    private String name;
    private String image;
    private String address;
    private float delivery_charge;

    public Hour hour;
    private List<Menus> menus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(float delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public Hour getHour() {
        return hour;
    }

    public void setHour(Hour hour) {
        this.hour = hour;
    }

    public List<Menus> getMenus() {
        return menus;
    }

    public void setMenus(List<Menus> menus) {
        this.menus = menus;
    }

    protected Model_Restaurant_List(Parcel in) {
        name = in.readString();
        image = in.readString();
        address = in.readString();
        delivery_charge = in.readFloat();
        menus = in.createTypedArrayList(Menus.CREATOR);
    }

    public static final Creator<Model_Restaurant_List> CREATOR = new Creator<Model_Restaurant_List>() {
        @Override
        public Model_Restaurant_List createFromParcel(Parcel in) {
            return new Model_Restaurant_List(in);
        }

        @Override
        public Model_Restaurant_List[] newArray(int size) {
            return new Model_Restaurant_List[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(image);
        parcel.writeString(address);
        parcel.writeFloat(delivery_charge);
        parcel.writeTypedList(menus);
    }
}
