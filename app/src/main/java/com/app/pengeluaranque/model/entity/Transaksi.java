package com.app.pengeluaranque.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_transaksi")
public class Transaksi implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    public int id_transaksi;

    @ColumnInfo(name = "id_produk")
    public int id_produk;

    @ColumnInfo(name = "tgl_transaksi")
    public String tgl_transaksi;

    @ColumnInfo(name = "qty")
    public int qty;

    @ColumnInfo(name = "sub_total")
    public int sub_total;

    @ColumnInfo(name = "promo")
    public String promo;

    @ColumnInfo(name = "total_akhir")
    public int total_akhir;

    @ColumnInfo(name = "uang_bayar")
    public int uang_bayar;

    @ColumnInfo(name = "kembalian")
    public int kembalian;

    protected Transaksi(Parcel in) {
        this.id_transaksi = in.readInt();
        this.id_produk = in.readInt();
        this.tgl_transaksi = in.readString();
        this.promo = in.readString();
        this.qty = in.readInt();
        this.sub_total = in.readInt();
        this.total_akhir =  in.readInt();
        this.uang_bayar = in.readInt();
        this.kembalian = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_transaksi);
        dest.writeInt(id_produk);
        dest.writeString((tgl_transaksi));
        dest.writeString(promo);
        dest.writeInt(qty);
        dest.writeInt(sub_total);
        dest.writeInt(total_akhir);
        dest.writeInt(uang_bayar);
        dest.writeInt(kembalian);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Transaksi> CREATOR = new Creator<Transaksi>() {
        @Override
        public Transaksi createFromParcel(Parcel source) {
            return new Transaksi(source);
        }

        @Override
        public Transaksi[] newArray(int size) {
            return new Transaksi[size];
        }
    };
}
