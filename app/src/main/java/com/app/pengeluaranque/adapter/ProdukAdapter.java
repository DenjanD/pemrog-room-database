package com.app.pengeluaranque.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import androidx.annotation.NonNull;

import com.app.pengeluaranque.databinding.ItemProdukBinding;
import com.app.pengeluaranque.model.entity.Produk;
import com.app.pengeluaranque.utils.FunctionHelper;
import com.app.pengeluaranque.view.main.TambahData;

public class ProdukAdapter extends
        RecyclerView.Adapter<ProdukAdapter.ViewHolder> {

    private static final String TAG = ProdukAdapter.class.getSimpleName();

    private Context context;
    private List<Produk> list;
    private final ProdukAdapterCallback mAdapterCallback;
    private ItemProdukBinding binding;

    public ProdukAdapter(Context context, List<Produk> list, ProdukAdapterCallback adapterCallback, ProdukAdapterCallback mAdapterCallback) {
        this.context = context;
        this.list = list;
        this.mAdapterCallback = mAdapterCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemProdukBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Produk item = list.get(position);
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

    public static void addData(List<Produk> produks){
        this.list = produks;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull ItemProdukBinding itemView) {
            super(itemView.getRoot());

            itemView.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Produk produk = list.get(getAdapterPosition());
                    mAdapterCallback.onEdit(produk);
                    return true;
                }
            });


            binding.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Produk produk = list.get(getAdapterPosition());
                    mAdapterCallback.onDelete(produk);
                }
            });
        }

        void bindData(Produk item) {
            String name = item.nama;
            binding.tvName.setText(name);

            int stock = item.stok;
            binding.tvStock.setText(stock);

            int price = item.harga;
            String initPrice = FunctionHelper.rupiahFormat(price);
            binding.tvPrice.setText(initPrice);


        }
    }

    public interface ProdukAdapterCallback {
        void onEdit(Produk produk);
        void onDelete(Produk produk);
    }
}

