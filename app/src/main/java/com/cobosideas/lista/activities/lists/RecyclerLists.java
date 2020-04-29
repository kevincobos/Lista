package com.cobosideas.lista.activities.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cobosideas.lista.R;
import com.cobosideas.lista.global.Constants;

import java.util.List;

public class RecyclerLists extends RecyclerView.Adapter<RecyclerLists.MyViewHolder>{
    //CODE_INT_MAIN_RECYCLER
    private final int CODE_INT_MAIN_RECYCLER_ACCESS = Constants.CODES_MAIN_RECYCLER.CODE_INT_MAIN_RECYCLER_ACCESS;
    private final int CODE_INT_MAIN_RECYCLER_DELETE = Constants.CODES_MAIN_RECYCLER.CODE_INT_MAIN_RECYCLER_DELETE;
    //DataSet to display on Recycler
    private List<ModelItemLists> dataSetLists;

    private RecyclerListsInputListener recyclerListsInputListener;
    public interface RecyclerListsInputListener {
        void onInterfaceString(int CODE_INT_MR_ID, Long stringValue, int itemPosition);
    }
    public RecyclerLists(@NonNull List<ModelItemLists> dataSetLists,  @NonNull RecyclerListsInputListener contextListener) {
        this.dataSetLists = dataSetLists;
        this.recyclerListsInputListener = contextListener;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv_listsItems;
        TextView tv_listsId;
        TextView tv_listsLink;
        TextView tv_listsName;
        TextView tv_listaValue;


        MyViewHolder(View itemView) {
            super(itemView);
            cv_listsItems = itemView.findViewById(R.id.cv_listsItems);
            tv_listsId = itemView.findViewById(R.id.tv_lists_id);
            tv_listsLink = itemView.findViewById(R.id.tv_lists_link);
            tv_listsName = itemView.findViewById(R.id.tv_lists_name);
            tv_listaValue = itemView.findViewById(R.id.tv_lists_value);
        }
    }
    /**    Add new Item to recyclerView  and update   **/
    void addItemToRecycler(ModelItemLists newItem) {
        dataSetLists.add(newItem);
        notifyDataSetChanged();
    }
    /**    remove Item from recyclerView and update    **/
    void deleteItemToRecycler(int selectedToDelete, ModelItemLists newItem) {
        dataSetLists.remove(selectedToDelete);
        notifyItemRemoved(selectedToDelete);
    }
    @NonNull
    @Override
    public RecyclerLists.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // create a new view
        View viewDialog = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.cardview_lists_item_simple_template, viewGroup, false);
        return new MyViewHolder(viewDialog);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        // - get element from your dataSet at this position
        // - replace the contents of the view with that element
        String convertIdToString = dataSetLists.get(position).id + "";
        holder.tv_listsId.setText(convertIdToString);
        holder.tv_listsLink.setText(dataSetLists.get(position).name);
        holder.tv_listsName.setText(dataSetLists.get(position).description);
        holder.tv_listaValue.setText(dataSetLists.get(position).date + "");

        //Adding OnClickListeners to each part of the list item then will be able to modify them+
        holder.cv_listsItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Long itemDataBaseId = dataSetLists.get(holder.getAdapterPosition()).id;
                final int selectedItem  = holder.getAdapterPosition();
                recyclerListsInputListener.onInterfaceString(
                        CODE_INT_MAIN_RECYCLER_ACCESS,
                        itemDataBaseId,
                        selectedItem);
            }
        });
        //Adding OnLongClickListeners to be able to delete them
        holder.cv_listsItems.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Long itemDataBaseId = dataSetLists.get(holder.getAdapterPosition()).id;
                final int selectedItem  = holder.getAdapterPosition();
                recyclerListsInputListener.onInterfaceString(
                        CODE_INT_MAIN_RECYCLER_DELETE,
                        itemDataBaseId,
                        selectedItem);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSetLists.size();
    }
}
