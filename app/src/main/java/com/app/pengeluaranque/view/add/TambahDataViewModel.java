package com.app.pengeluaranque.view.add;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.pengeluaranque.model.entity.Produk;
import com.app.pengeluaranque.utils.database.DatabaseClient;
import com.app.pengeluaranque.utils.database.daos.PengeluaranDao;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class TambahDataViewModel extends AndroidViewModel {

    private PengeluaranDao pengeluaranDao;

    public TambahDataViewModel(@NonNull Application application) {
        super(application);

        pengeluaranDao = DatabaseClient.getInstance(application).getAppDatabase().pengeluaranDao();
    }

    public void addProduk(final String namabarang, final int stok, final int hargabarang) {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        Produk produk = new Produk();
                        produk.nama = namabarang;
                        produk.stok = stok;
                        produk.harga = hargabarang;
                        produkDao.insertData(produk);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void updateProduk(final int uid, final String namabarang, final int stok, final int hargabarang) {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        pengeluaranDao.updateData(namabarang, stok, hargabarang, uid);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
