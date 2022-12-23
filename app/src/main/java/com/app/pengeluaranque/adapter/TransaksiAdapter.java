package com.app.pengeluaranque.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import androidx.annotation.NonNull;

import com.app.pengeluaranque.databinding.ItemTransaksiBinding;
import com.app.pengeluaranque.model.entity.Transaksi;
import com.app.pengeluaranque.utils.FunctionHelper;
import com.app.pengeluaranque.view.main.MainActivityTransaksi;

public class TransaksiAdapter extends
        RecyclerView.Adapter<TransaksiAdapter.ViewHolder> {

    private static final String TAG = TransaksiAdapter.class.getSimpleName();

    private Context context;
    private List<Transaksi> list;
    private final TransaksiAdapterCallback mAdapterCallback;
    private ItemTransaksiBinding binding;

    public TransaksiAdapter(Context context, List<Transaksi> list, TransaksiAdapterCallback adapterCallback, TransaksiAdapterCallback mAdapterCallback) {
        this.context = context;
        this.list = list;
        this.mAdapterCallback = mAdapterCallback;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemTransaksiBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaksi item = list.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        int size = this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addData(List<Transaksi> transaksis){
        this.list = transaksis;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull ItemTransaksiBinding itemView) {
            super(itemView.getRoot());

            itemView.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Transaksi transaksi = list.get(getAdapterPosition());
                    mAdapterCallback.onEdit(transaksi);
                    return true;
                }
            });


            binding.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Transaksi transaksi = list.get(getAdapterPosition());
                    mAdapterCallback.onDelete(transaksi);
                }
            });
        }

        void bindData(Transaksi item) {
            String date = item.tgl_transaksi;
            binding.tvDate.setText(date);

            int Kuantity = item.qty;
            binding.tvKuantity.setText(Kuantity);

            int subTotal = item.sub_total;
            String initsubTotal = FunctionHelper.rupiahFormat(subTotal);
            binding.tvST.setText(initsubTotal);

            String diskon = item.promo;
            binding.tvDiskon.setText(diskon);

            int totalAkhir = item.total_akhir;
            String inittotalAkhir = FunctionHelper.rupiahFormat(totalAkhir);
            binding.tvTA.setText(inittotalAkhir);

            int uangBayar = item.uang_bayar;
            String inituangBayar = FunctionHelper.rupiahFormat(uangBayar);
            binding.tvUB.setText(inituangBayar);

            int kembalian = item.kembalian;
            String initkembalian = FunctionHelper.rupiahFormat(kembalian);
            binding.tvKembalian.setText(initkembalian);

        }
    }

    public interface TransaksiAdapterCallback {
        void onEdit(Transaksi transaksi);
        void onDelete(Transaksi transaksi);
    }
}

