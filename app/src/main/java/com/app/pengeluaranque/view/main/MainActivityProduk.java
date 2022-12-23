package com.app.pengeluaranque.view.main;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.pengeluaranque.TambahData;
import com.app.pengeluaranque.adapter.ProdukAdapter;
import com.app.pengeluaranque.databinding.ActivityMainBinding;
import com.app.pengeluaranque.databinding.MenuBinding;
import com.app.pengeluaranque.model.entity.Produk;
import com.app.pengeluaranque.model.entity.Produk;
import com.app.pengeluaranque.utils.FunctionHelper;
import com.app.pengeluaranque.view.add.AddDataActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivityProduk extends AppCompatActivity
        implements ProdukAdapter.ProdukAdapterCallback {

    private MenuBinding binding;
    private ProdukAdapter produkAdapter;
    private MainViewModelProduk mainViewModel;

    private List<Produk> mProduks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initAdapter();
        observeData();

        initAction();
    }

    private void initAction() {
        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TambahData.startActivity(MainActivityProduk.this, false,
                        null);
            }
        });

        binding.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.deleteAllData();
                binding.tvTotal.setText("0");
            }
        });
    }

    private void initAdapter() {
        produkAdapter = new produkAdapter(this, mProduks, this);
        binding.rvProduks.setLayoutManager(new LinearLayoutManager(this));
        binding.rvProduks.setItemAnimator(new DefaultItemAnimator());
        binding.rvProduks.setAdapter(produkAdapter);
    }

    private void observeData() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModelProduk.class);
        mainViewModel.getmProduks().observe(this,
                new Observer<List<Produk>>() {
                    @Override
                    public void onChanged(List<Produk> produks) {
                        if (produks.isEmpty()) {
                            binding.btnHapus.setVisibility(View.GONE);
                        } else {
                            binding.btnHapus.setVisibility(View.VISIBLE);
                        }

                        ProdukAdapter.addData(produks);
                    }
                });

        mainViewModel.getTotalPrice().observe(this,
                new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        if (integer == null) {
                            int totalPrice = 0;
                            String initPrice = FunctionHelper.rupiahFormat(totalPrice);
                            binding.tvTotal.setText(initPrice);
                        } else {
                            int totalPrice = integer;
                            String initPrice = FunctionHelper.rupiahFormat(totalPrice);
                            binding.tvTotal.setText(initPrice);
                        }
                    }
                });
    }

    @Override
    public void onEdit(Produk produk) {
        TambahData.startActivity(this, true, produk);
    }

    @Override
    public void onDelete(Produk produk) {
        int uid = produk.id_produk;
        mainViewModel.deleteSingleData(uid);
    }
}