package com.cobosideas.lista.activities.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cobosideas.lista.R;
import com.cobosideas.lista.global.Constants;

import java.util.List;

public class RecyclerLists extends RecyclerView.Adapter<RecyclerLists.MyViewHolder>{
    //CODE_MAIN_RECYCLER_ID_INT
    private final int CODE_INT_RECYCLER_LISTS_ID = Constants.CODES_RECYCLER_LISTS.CODE_INT_RECYCLER_LISTS_ID;
    //DataSet to display on Recycler
    private List<ModelItemLists> dataSetLists;

    private RecyclerListsInputListener recyclerListsInputListener;
    public interface RecyclerListsInputListener {
        void onInterfaceString(int CODE_INT_RECYCLER_LISTS_ID, Long stringValue);
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
    public void addItemToRecycler(ModelItemLists newItem) {
        dataSetLists.add(newItem);
        notifyDataSetChanged();
    }
    /**    remove Item from recyclerView and update    **/
    public void deleteItemToRecycler(int selectedToDelete, ModelItemLists newItem) {
        dataSetLists.remove(selectedToDelete);
        notifyItemRemoved(selectedToDelete);
    }
    @NonNull
    @Override
    public RecyclerLists.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // create a new view
        View viewDialog = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.cardview_main_simple, viewGroup, false);
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
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
