package com.myapps.dhruv.health;




public class doctor {
    String name;
    String timings;
    String fee;
    Integer photoid;
    String location;
    String about;
    String num;
    int count = 0;


    doctor(){

    }

    public void setname(String name){
        this.name = name;
    }
    public void settime(String time){
        this.timings = time;
    }
    public void setfee(String fee){
        this.fee = fee;
    }
    public void setPhotoid(Integer photo){
        this.photoid = photo;
    }
    public void setloc(String loc){
        this.location = loc;
    }
    public void setabout(String about){this.about = about; }
    public void setnum(String num){
        this.num = num;
    }
    public void seti(Integer i){
        this.count = i;
    }
}

