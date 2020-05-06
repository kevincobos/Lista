package com.cobosideas.lista.recyclerMain;

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
import com.cobosideas.lista.room.models.ItemRoom;

import java.util.List;

public class MainRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //CODE_INT_RECYCLER_CARD_VIEW
    private final int CODE_INT_CARD_VIEW_DEFAULT = Constants.CODES_LISTA_CARD_VIEW.CODE_INT_CARD_VIEW_DEFAULT;
    private final int CODE_INT_CARD_VIEW_SIMPLE = Constants.CODES_LISTA_CARD_VIEW.CODE_INT_CARD_VIEW_LINK;
    //CODE_INT_MAIN_RECYCLER
    private final int CODE_INT_MAIN_RECYCLER_ACCESS = Constants.CODES_MAIN_RECYCLER.CODE_INT_MAIN_RECYCLER_ACCESS;
    private final int CODE_INT_MAIN_RECYCLER_DELETE = Constants.CODES_MAIN_RECYCLER.CODE_INT_MAIN_RECYCLER_DELETE;
    private final int CODE_INT_MAIN_RECYCLER_PREFERENCES = Constants.CODES_MAIN_RECYCLER.CODE_INT_MAIN_RECYCLER_PREFERENCES;
    //DataSet to display on Recycler
    private List<ItemRoom> dataSetListaItemROOOM;

    private MainRecyclerInputListener mainRecyclerInputListener;
    public interface MainRecyclerInputListener {
        void onInterfaceString(int CODE_INT_MR_ID, Long stringValue, int itemPosition);
    }


    //Context context;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv_listaItems;
        TextView tv_id;
        TextView tv_listaName;
        TextView tv_listaDescription;
        ImageView iv_imageId;
        ImageButton ib_preferences;


        MyViewHolder(View itemView) {
            super(itemView);
            cv_listaItems = itemView.findViewById(R.id.cv_listaItems);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_listaName = itemView.findViewById(R.id.tv_listaName);
            tv_listaDescription = itemView.findViewById(R.id.tv_listaDescription);
            iv_imageId = itemView.findViewById(R.id.iv_photoId);
            ib_preferences = itemView.findViewById(R.id.ib_card_view_main_preferences);
        }
    }

    // Provide a suitable constructor (depends on the kind of data set)
    public MainRecycler(List<ItemRoom> myDataSet, MainRecyclerInputListener context) {
        this.dataSetListaItemROOOM = myDataSet;
        this.mainRecyclerInputListener = context;
    }

    /**    Add new Item to recyclerView  and update   **/
    public void addItemToRecycler(ItemRoom newItem) {
        dataSetListaItemROOOM.add(newItem);
        notifyDataSetChanged();
    }
    /**    remove Item from recyclerView and update    **/
    public void deleteItemToRecycler(int selectedToDelete, ItemRoom newItem) {
        dataSetListaItemROOOM.remove(selectedToDelete);
        notifyItemRemoved(selectedToDelete);
    }
    @Override
    public int getItemViewType(int position) {
        return dataSetListaItemROOOM.get(position).function;
    }
    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // create a new view
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.cardview_main_default_template, viewGroup, false);
        RecyclerView.ViewHolder viewHolder = new MyViewHolder(view);
        switch (viewType){
            case CODE_INT_CARD_VIEW_DEFAULT:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.cardview_main_default_template, viewGroup, false);
                viewHolder = new MyViewHolder(view);
                break;
            case CODE_INT_CARD_VIEW_SIMPLE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.cardview_main_selection_link_template, viewGroup, false);
                viewHolder = new MyViewHolder(view);
                break;
        }
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataSet at this position
        // - replace the contents of the view with that element
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        String convertIdToString = dataSetListaItemROOOM.get(position).id + "";
        myViewHolder.tv_id.setText(convertIdToString);
        myViewHolder.tv_listaName.setText(dataSetListaItemROOOM.get(position).name);
        myViewHolder.tv_listaDescription.setText(dataSetListaItemROOOM.get(position).description);
        myViewHolder.iv_imageId.setImageResource(dataSetListaItemROOOM.get(position).icon);


        //Adding OnClickListeners to each part of the list item then will be able to modify them+
        myViewHolder.cv_listaItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Long itemDataBaseId = dataSetListaItemROOOM.get(holder.getAdapterPosition()).id;
                final int selectedItem = holder.getAdapterPosition();
                mainRecyclerInputListener.onInterfaceString(
                        CODE_INT_MAIN_RECYCLER_ACCESS,
                        itemDataBaseId,
                        selectedItem);
            }
        });
        //Adding OnLongClickListeners to be able to delete them
        myViewHolder.cv_listaItems.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Long itemDataBaseId = dataSetListaItemROOOM.get(holder.getAdapterPosition()).id;
                final int selectedItem = holder.getAdapterPosition();
                mainRecyclerInputListener.onInterfaceString(
                        CODE_INT_MAIN_RECYCLER_DELETE,
                        itemDataBaseId,
                        selectedItem);
                return true;
            }
        });

        //This will sent us over ActivityEditLista to change the way this item acts
        myViewHolder.ib_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Long itemDataBaseId = dataSetListaItemROOOM.get(holder.getAdapterPosition()).id;
                final int selectedItem = holder.getAdapterPosition();
                mainRecyclerInputListener.onInterfaceString(
                        CODE_INT_MAIN_RECYCLER_PREFERENCES,
                        itemDataBaseId,
                        selectedItem);
            }
        });
    }

    // Return the size of your dataSet (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSetListaItemROOOM.size();
    }
}
