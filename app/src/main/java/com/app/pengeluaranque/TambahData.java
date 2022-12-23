package com.app.pengeluaranque;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.app.pengeluaranque.databinding.ActivityAddDataBinding;
import com.app.pengeluaranque.model.entity.Produk;
import com.app.pengeluaranque.view.add.TambahDataViewModel;

public class TambahData extends AppCompatActivity {

    private static String KEY_IS_EDIT = "key_is_edit";
    private static String KEY_DATA = "key_data";

    // Untuk kebutuhan data yang akan dipakai pada Activitu AddData
    public static void startActivity(Context context, boolean isEdit, Produk produk) {
        Intent intent = new Intent(new Intent(context, TambahData.class));
        intent.putExtra(KEY_IS_EDIT, isEdit);
        intent.putExtra(KEY_DATA, produk);
        context.startActivity(intent);
    }
    private ActivityAddDataBinding ItemProdukBinding;
    private TambahDataViewModel addDataViewModel;

    private boolean mIsEdit = false;
    private int mUid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemProdukBinding = ActivityAddDataBinding.inflate(getLayoutInflater());
        setContentView(ItemProdukBinding.getRoot());

        addDataViewModel = ViewModelProviders.of(this).get(TambahDataViewModel.class);

        loadData();
        initAction();
    }

    private void initAction() {
        ItemProdukBinding.btn_simpan_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namabarang = ItemProdukBinding.tie_nama_produk_tp.getText().toString();
                String stok = ItemProdukBinding.tie_stock_tp.getText().toString();
                String hargabarang = ItemProdukBinding.tie_harga_tp.getText().toString();

                if (namabarang.isEmpty() || stok.isEmpty() || hargabarang.isEmpty()) {
                    Toast.makeText(TambahData.this, getString(R.string.error_message_form_empty),
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (mIsEdit) {
                        addDataViewModel.updateProduk(mUid, namabarang, Integer.parseInt(hargabarang));
                    } else {
                        addDataViewModel.addProduk(namabarang, Integer.parseInt(hargabarang));
                    }
                    finish();
                }
            }
        });
    }
    private void loadData() {
        mIsEdit = getIntent().getBooleanExtra(KEY_IS_EDIT, false);
        if (mIsEdit) {
            Produk produk = getIntent().getParcelableExtra(KEY_DATA);
            if (produk != null) {
                mUid = produk.id_produk;
                String namabarang = produk.nama;
                int stok = produk.stok;
                int hargabarang = produk.harga;

                ItemProdukBinding.tie_nama_produk_tp.setText(namabarang);
                ItemProdukBinding.tie_stock_tp.setText(String.valueOf(stok));
                ItemProdukBinding.tie_harga_tp.setText(String.valueOf(hargabarang));
            }
        }
    }
}