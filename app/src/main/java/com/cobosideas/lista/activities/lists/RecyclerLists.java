package com.cobosideas.lista.activities.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cobosideas.lista.R;
import com.cobosideas.lista.global.Constants;

import java.util.List;

public class RecyclerLists extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //CODE_INT_RECYCLER_CARD_VIEW
    private final int CODE_INT_CARD_VIEW_DEFAULT = Constants.CODES_CARD_VIEW_LISTS.CODE_INT_CARD_VIEW_DEFAULT;
    private final int CODE_INT_CARD_VIEW_SIMPLE = Constants.CODES_CARD_VIEW_LISTS.CODE_INT_CARD_VIEW_SIMPLE;
    private final int CODE_INT_CARD_VIEW_IMAGE = Constants.CODES_CARD_VIEW_LISTS.CODE_INT_CARD_VIEW_IMAGE;
    //CODE_INT_RECYCLER
    private final int CODE_INT_RECYCLER_ACCESS = Constants.CODES_RECYCLER_LISTS.CODE_INT_RECYCLER_ACCESS;
    private final int CODE_INT_RECYCLER_DELETE = Constants.CODES_RECYCLER_LISTS.CODE_INT_RECYCLER_DELETE;
    private final int CODE_INT_RECYCLER_EDIT = Constants.CODES_RECYCLER_LISTS.CODE_INT_RECYCLER_EDIT;
    //DataSet to display on Recycler
    private List<ModelItemLists> dataSetLists;

    private RecyclerListsInputListener recyclerListsInputListener;
    public interface RecyclerListsInputListener {
        void onInterfaceString(int CODE_INT_LIST_ID, Long stringValue, int itemPosition);
    }
    RecyclerLists(@NonNull List<ModelItemLists> dataSetLists, @NonNull RecyclerListsInputListener contextListener) {
        this.dataSetLists = dataSetLists;
        this.recyclerListsInputListener = contextListener;
    }

    @Override
    public int getItemViewType(int position) {
        return dataSetLists.get(position).function;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolderDefault extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv_listsItems;
        TextView tv_listsId;
        TextView tv_listsName;
        TextView tv_listsDescription;
        TextView tv_listaValue;
        ImageView iv_iconLists;
        ImageButton ib_lists_edit;


        MyViewHolderDefault(View itemView) {
            super(itemView);
            cv_listsItems = itemView.findViewById(R.id.cv_listsItems);
            tv_listsId = itemView.findViewById(R.id.tv_lists_id);
            tv_listsDescription = itemView.findViewById(R.id.tv_lists_description);
            tv_listsName = itemView.findViewById(R.id.tv_lists_name);
            tv_listaValue = itemView.findViewById(R.id.tv_lists_value);
            iv_iconLists = itemView.findViewById(R.id.iv_icon_lists);
            ib_lists_edit = itemView.findViewById(R.id.ib_card_view_lists_edit);
        }
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolderSimple extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv_listsItems;
        TextView tv_listsId;
        TextView tv_listsLink;
        TextView tv_listsName;
        TextView tv_listaValue;
        ImageView iv_iconLists;
        ImageButton ib_lists_edit;


        MyViewHolderSimple(View itemView) {
            super(itemView);
            cv_listsItems = itemView.findViewById(R.id.cv_listsItems);
            tv_listsId = itemView.findViewById(R.id.tv_lists_id);
            tv_listsLink = itemView.findViewById(R.id.tv_lists_link);
            tv_listsName = itemView.findViewById(R.id.tv_lists_name);
            tv_listaValue = itemView.findViewById(R.id.tv_lists_value);
            iv_iconLists = itemView.findViewById(R.id.iv_icon_lists);
            ib_lists_edit = itemView.findViewById(R.id.ib_card_view_lists_edit);
        }
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolderImage extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv_listsItems;
        TextView tv_listsId;
        TextView tv_listsLink;
        TextView tv_listsName;
        TextView tv_listaValue;
        ImageView iv_iconLists;
        ImageButton ib_lists_edit;


        MyViewHolderImage(View itemView) {
            super(itemView);
            cv_listsItems = itemView.findViewById(R.id.cv_listsItems);
            tv_listsId = itemView.findViewById(R.id.tv_lists_id);
            tv_listsLink = itemView.findViewById(R.id.tv_lists_link);
            tv_listsName = itemView.findViewById(R.id.tv_lists_name);
            tv_listaValue = itemView.findViewById(R.id.tv_lists_value);
            iv_iconLists = itemView.findViewById(R.id.iv_icon_lists);
            ib_lists_edit = itemView.findViewById(R.id.ib_card_view_lists_edit);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // create a new view
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.cardview_lists_item_default_template, viewGroup, false);
        RecyclerView.ViewHolder viewHolder = new MyViewHolderDefault(view);
        switch (viewType){
            case CODE_INT_CARD_VIEW_DEFAULT:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.cardview_lists_item_default_template, viewGroup, false);
                viewHolder = new MyViewHolderDefault(view);
                break;
            case CODE_INT_CARD_VIEW_SIMPLE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.cardview_lists_item_simple_template, viewGroup, false);
                viewHolder = new MyViewHolderSimple(view);
                break;
            case CODE_INT_CARD_VIEW_IMAGE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.cardview_lists_item_image_template, viewGroup, false);
                viewHolder = new MyViewHolderImage(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataSet at this position
        // - replace the contents of the view with that element
        String convertIdToString = dataSetLists.get(position).id + "";
        int typeCardViewToShow = dataSetLists.get(position).function;
        switch (typeCardViewToShow){
            case CODE_INT_CARD_VIEW_DEFAULT:
                MyViewHolderDefault myViewHolderDefault = (MyViewHolderDefault) holder;
                myViewHolderDefault.tv_listsId.setText(convertIdToString);
                myViewHolderDefault.tv_listsName.setText(dataSetLists.get(position).name);
                myViewHolderDefault.tv_listsDescription.setText(dataSetLists.get(position).description);
                myViewHolderDefault.iv_iconLists.setImageResource(dataSetLists.get(position).icon);

                //Click on ImageButton allow us to enter to edit lists item
                myViewHolderDefault.ib_lists_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Long itemDataBaseId = dataSetLists.get(holder.getAdapterPosition()).id;
                        final int selectedItem  = holder.getAdapterPosition();
                        recyclerListsInputListener.onInterfaceString(
                                CODE_INT_RECYCLER_EDIT,
                                itemDataBaseId,
                                selectedItem);
                    }
                });
                //Adding OnClickListeners to each item to access to ActivityManageFunctions
                myViewHolderDefault.cv_listsItems.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Long itemDataBaseId = dataSetLists.get(holder.getAdapterPosition()).id;
                        final int selectedItem  = holder.getAdapterPosition();
                        recyclerListsInputListener.onInterfaceString(
                                CODE_INT_RECYCLER_ACCESS,
                                itemDataBaseId,
                                selectedItem);
                    }
                });
                //Adding OnLongClickListeners to be able to delete them
                myViewHolderDefault.cv_listsItems.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        final Long itemDataBaseId = dataSetLists.get(holder.getAdapterPosition()).id;
                        final int selectedItem  = holder.getAdapterPosition();
                        recyclerListsInputListener.onInterfaceString(
                                CODE_INT_RECYCLER_DELETE,
                                itemDataBaseId,
                                selectedItem);
                        return true;
                    }
                });
                break;
            case CODE_INT_CARD_VIEW_SIMPLE:
                MyViewHolderSimple myViewHolderSimple = (MyViewHolderSimple) holder;
                myViewHolderSimple.tv_listsId.setText(convertIdToString);
                myViewHolderSimple.tv_listsLink.setText(dataSetLists.get(position).name);
                myViewHolderSimple.tv_listsName.setText(dataSetLists.get(position).description);
                myViewHolderSimple.iv_iconLists.setImageResource(dataSetLists.get(position).icon);

                myViewHolderSimple.ib_lists_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Long itemDataBaseId = dataSetLists.get(holder.getAdapterPosition()).id;
                        final int selectedItem  = holder.getAdapterPosition();
                        recyclerListsInputListener.onInterfaceString(
                                CODE_INT_RECYCLER_EDIT,
                                itemDataBaseId,
                                selectedItem);
                    }
                });
                //Adding OnClickListeners to each item to access to ActivityManageFunctions
                myViewHolderSimple.cv_listsItems.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Long itemDataBaseId = dataSetLists.get(holder.getAdapterPosition()).id;
                        final int selectedItem  = holder.getAdapterPosition();
                        recyclerListsInputListener.onInterfaceString(
                                CODE_INT_RECYCLER_ACCESS,
                                itemDataBaseId,
                                selectedItem);
                    }
                });
                //Adding OnLongClickListeners to be able to delete them
                myViewHolderSimple.cv_listsItems.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        final Long itemDataBaseId = dataSetLists.get(holder.getAdapterPosition()).id;
                        final int selectedItem  = holder.getAdapterPosition();
                        recyclerListsInputListener.onInterfaceString(
                                CODE_INT_RECYCLER_DELETE,
                                itemDataBaseId,
                                selectedItem);
                        return true;
                    }
                });
                break;
            case CODE_INT_CARD_VIEW_IMAGE:
                MyViewHolderImage myViewHolderImage = (MyViewHolderImage) holder;
                myViewHolderImage.tv_listsId.setText(convertIdToString);
                myViewHolderImage.tv_listsLink.setText(dataSetLists.get(position).name);
                myViewHolderImage.tv_listsName.setText(dataSetLists.get(position).description);
                myViewHolderImage.iv_iconLists.setImageResource(dataSetLists.get(position).icon);

                //Edit lists item
                myViewHolderImage.ib_lists_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Long itemDataBaseId = dataSetLists.get(holder.getAdapterPosition()).id;
                        final int selectedItem  = holder.getAdapterPosition();
                        recyclerListsInputListener.onInterfaceString(
                                CODE_INT_RECYCLER_EDIT,
                                itemDataBaseId,
                                selectedItem);
                    }
                });
                //Adding OnClickListeners to each item to access to ActivityManageFunctions
                myViewHolderImage.cv_listsItems.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Long itemDataBaseId = dataSetLists.get(holder.getAdapterPosition()).id;
                        final int selectedItem  = holder.getAdapterPosition();
                        recyclerListsInputListener.onInterfaceString(
                                CODE_INT_RECYCLER_ACCESS,
                                itemDataBaseId,
                                selectedItem);
                    }
                });
                //Adding OnLongClickListeners to be able to delete them
                myViewHolderImage.cv_listsItems.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        final Long itemDataBaseId = dataSetLists.get(holder.getAdapterPosition()).id;
                        final int selectedItem  = holder.getAdapterPosition();
                        recyclerListsInputListener.onInterfaceString(
                                CODE_INT_RECYCLER_DELETE,
                                itemDataBaseId,
                                selectedItem);
                        return true;
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dataSetLists.size();
    }
    /**    Add new Item to recyclerView  and update   **/
    void addItemToRecycler(ModelItemLists newItem) {
        dataSetLists.add(newItem);
        notifyDataSetChanged();
    }
    /**    remove Item from recyclerView and update    **/
    void deleteItemToRecycler(int selectedToDelete) {
        dataSetLists.remove(selectedToDelete);
        notifyItemRemoved(selectedToDelete);
    }
}
