package com.cobosideas.lista.recyclerMain;

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
import com.cobosideas.lista.room.models.ItemRoom;

import java.util.List;

public class MainRecycler extends RecyclerView.Adapter<MainRecycler.MyViewHolder>{
    //CODE_MAIN_RECYCLER_ID_INT
    private final int CODE_MR_ID_INT = Constants.CODES_ALERT_MAIN_RECYCLER.CODE_MAIN_RECYCLER_ID_INT;

    private MainRecyclerInputListener mainRecyclerInputListener;
    public interface MainRecyclerInputListener {
        void onInterfaceString(int CODE_MR_ID_INT, String stringValue);
    }
    //DataSet to display on Recycler
    private List<ItemRoom> dataSetListaItemROOOM;

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
        TextView tv_listaName;
        TextView tv_listaDescription;
        ImageView iv_imageId;


        MyViewHolder(View itemView) {
            super(itemView);

            cv_listaItems = itemView.findViewById(R.id.cv_listaItems);
            tv_listaName = itemView.findViewById(R.id.tv_listaName);
            tv_listaDescription = itemView.findViewById(R.id.tv_listaDescription);
            iv_imageId = itemView.findViewById(R.id.iv_photoId);
        }
    }

    // Provide a suitable constructor (depends on the kind of data set)
    public MainRecycler(List<ItemRoom> myDataSet, MainRecyclerInputListener context) {
        this.dataSetListaItemROOOM = myDataSet;
        //this.context = context;
        mainRecyclerInputListener = context;
    }

    /**    Add new Item to recyclerView     **/
    public void addItemToRecycler(ItemRoom newItem) {
        dataSetListaItemROOOM.add(newItem);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MainRecycler.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // create a new view
        View viewDialog = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.recycler_main_item_simple_template, viewGroup, false);
        return new MyViewHolder(viewDialog);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // - get element from your dataSet at this position
        // - replace the contents of the view with that element
        holder.tv_listaName.setText(dataSetListaItemROOOM.get(position).name);
        holder.tv_listaDescription.setText(dataSetListaItemROOOM.get(position).description);
        holder.iv_imageId.setImageResource(dataSetListaItemROOOM.get(position).photoId);


        //Adding OnClickListeners to each part of the list item then will be able to modify them+
        holder.cv_listaItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainRecyclerInputListener.onInterfaceString(CODE_MR_ID_INT, (dataSetListaItemROOOM.get(holder.getAdapterPosition()).id + ""));
            }
        });
    }

    // Return the size of your dataSet (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSetListaItemROOOM.size();
    }
}
