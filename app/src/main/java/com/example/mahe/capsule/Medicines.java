package com.example.mahe.capsule;

/**
 * Created by mahe on 10/19/2016.
 */
public class Medicines {

   int _id;


    String _name;
    String _dosage;
   String _nooftimes;
    String _times;
    byte[] _image;
     public Medicines()
     {}
    public Medicines( String _name, String _dosage, String _nooftimes, String _times,byte[] array) {

        this._name = _name;
        this._dosage = _dosage;
        this._nooftimes = _nooftimes;
        this._times = _times;
        this._image=array;

    }

    public byte[] get_image() {
        return _image;
    }

    public void set_image(byte[] _image) {
        this._image = _image;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_dosage() {
        return _dosage;
    }

    public void set_dosage(String _dosage) {
        this._dosage = _dosage;
    }

    public String get_nooftimes() {
        return _nooftimes;
    }

    public void set_nooftimes(String _nooftimes) {
        this._nooftimes = _nooftimes;
    }

    public String get_times() {
        return _times;
    }

    public void set_times(String _times) {
        this._times = _times;
    }

    public String toString(){return _name;}
}
