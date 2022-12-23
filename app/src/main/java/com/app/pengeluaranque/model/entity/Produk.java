package com.app.pengeluaranque.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_produk")
public class Produk implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id_produk;

    @ColumnInfo(name = "nama")
    public String nama;

    @ColumnInfo(name = "stok")
    public int stok;

    @ColumnInfo(name = "harga")
    public int harga;

    public Produk() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_produk);
        dest.writeString(nama);
        dest.writeInt(stok);
        dest.writeInt(harga);
    }

    protected Produk(Parcel in){
        this.id_produk = in.readInt();
        this.nama = in.readString();
        this.stok = in.readInt();
        this.harga = in.readInt();
    }
    static final Creator<Produk> CREATOR = new Creator<Produk>() {
        @Override
        public Produk createFromParcel(Parcel source) {
            return new Produk(source);
        }

        @Override
        public Produk[] newArray(int size) {
            return new Produk[size];
        }
    };
}

