package sai.com.viewmodeljava.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import sai.com.viewmodeljava.adapter.viewholder.BaseViewHolder;


public abstract class BaseRecyclerAdapter<T, V extends BaseViewHolder> extends RecyclerView.Adapter<V> {


    protected String TAG = getClass().getSimpleName();
    private List<T> data;


    public BaseRecyclerAdapter(List<T> data) {
        this.data = data;
    }


    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public T getItem(int position) throws IndexOutOfBoundsException {
        return data.get(position);
    }

    public void addItem(T object) {
        data.add(object);
        notifyItemChanged(data.indexOf(object));
    }

    public void removeItem(int position) throws IndexOutOfBoundsException {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void resetItems(List<T> data, int position) {
        if (data != null) {
            this.data = data;
            notifyItemChanged(position, data.size());
        }
    }

    public void resetItems(List<T> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    public void setData(int position, T data) {
        if (data != null) {
            this.data.set(position, data);
            notifyItemChanged(position);
        }
    }

    public void addItems(List<T> data) {
        if (data != null) {
            int startRange = (this.data.size() - 1) > 0 ? data.size() - 1 : 0;
            this.data.addAll(data);
            notifyItemRangeChanged(startRange, data.size());
        }
    }

    public List<T> getData() {
        return data;
    }

    public void setFilter(List<T> data) {
        this.data = new ArrayList<>();
        this.data.addAll(data);
        resetItems(data);
    }
}
