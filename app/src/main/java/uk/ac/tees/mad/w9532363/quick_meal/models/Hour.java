package uk.ac.tees.mad.w9532363.quick_meal.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Hour {
    String Monday="Monday";
    String Tuesday="Tuesday";
    String Wednesday="Wednesday";
    String Thusday="Thursday";
    String Friday="Friday";
    String Saturday="Saturday";
    String Sunday="Sunday";


    public String getMonday() {
        return Monday;
    }

    public void setMonday(String monday) {
        Monday = monday;
    }

    public String getTuesday() {
        return Tuesday;
    }

    public void setTuesday(String tuesday) {
        Tuesday = tuesday;
    }

    public String getWednesday() {
        return Wednesday;
    }

    public void setWednesday(String wednesday) {
        Wednesday = wednesday;
    }

    public String getThusday() {
        return Thusday;
    }

    public void setThusday(String thusday) {
        Thusday = thusday;
    }

    public String getFriday() {
        return Friday;
    }

    public void setFriday(String friday) {
        Friday = friday;
    }

    public String getSaturday() {
        return Saturday;
    }

    public void setSaturday(String saturday) {
        Saturday = saturday;
    }

    public String getSunday() {
        return Sunday;
    }

    public void setSunday(String sunday) {
        Sunday = sunday;
    }

    public String getHoursToday(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());

        switch (day){
            case "Sunday":
                return this.Sunday;
            case "Monday":
                return this.Monday;
            case "Tuesday":
                return this.Tuesday;
            case "Wednesday":
                return this.Wednesday;
            case "Thusday":
                return this.Thusday;
            case "Friday":
                return this.Friday;
            case "Saturday":
                return this.Saturday;

           default:
               return "Hello";
             //   return this.Sunday;




        }
    }

}
