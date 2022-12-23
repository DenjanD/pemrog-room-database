package com.app.pengeluaranque.view.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.pengeluaranque.model.entity.Produk;
import com.app.pengeluaranque.utils.database.DatabaseClient;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MainViewModelTransaksi extends AndroidViewModel {

    private LiveData<List<Produk>> mProduks;
    private ProdukDao produkDao;
    private LiveData<Integer> mTotalPrice;

    public MainViewModelTransaksi(@NonNull Application application) {
        super(application);

        produkDao = DatabaseClient.getInstance(application)
                .getAppDatabase().pengeluaranDao();
        mProduks = produkDao.getAll();
        mTotalPrice = produkDao.getTotalPrice();
    }

    public LiveData<List<Produk>> getmProduks() {
        return mProduks;
    }

    public LiveData<Integer> getTotalPrice() {
        return mTotalPrice;
    }

    public void deleteAllData() {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        produkDao.deleteAllData();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteSingleData(final int uid) {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        produkDao.deleteSingleData(uid);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
