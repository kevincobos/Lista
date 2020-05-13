package com.cobosideas.lista.activities.manage_functions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.TypeConverters;

import com.cobosideas.lista.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecyclerManageFunctions extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //DataSet to display on Recycler
    private List<ModelItemManageFunctions> dataSetManageFunctions;
    private RecyclerManageFunctionsInputListener recyclerInputListener;

    public interface RecyclerManageFunctionsInputListener {
        void onInterfaceString(int CODE_INT_MR_ID, Long stringValue, int itemPosition);
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    RecyclerManageFunctions(@NonNull List<ModelItemManageFunctions> dataSet,
                            @NonNull RecyclerManageFunctionsInputListener contextListener){
        this.dataSetManageFunctions = dataSet;
        this.recyclerInputListener = contextListener;
    }
    static class MyViewHolderDefault extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv_listsItems;
        TextView tv_manage_function_Name;
        TextView tv_manage_function_number;

        MyViewHolderDefault(View itemView) {
            super(itemView);
            cv_listsItems = itemView.findViewById(R.id.cv_manage_function_integer);
            tv_manage_function_Name = itemView.findViewById(R.id.tv_manage_function_name);
            tv_manage_function_number = itemView.findViewById(R.id.tv_manage_function_number);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // create a new view
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.cv_manage_functions_integer_math_default, viewGroup, false);
        RecyclerView.ViewHolder viewHolder = new RecyclerManageFunctions.MyViewHolderDefault(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String stringFunctionType = dataSetManageFunctions.get(position).JSON+"";

        FunctionTypeMoneyAmount functionTypeMoneyAmount = TypeConverterFunctions.getFunctionTypeMoneyAmount(stringFunctionType);

        RecyclerManageFunctions.MyViewHolderDefault myViewHolderDefault = (RecyclerManageFunctions.
                MyViewHolderDefault) holder;
        //myViewHolderDefault.tv_manage_function_Name.setText(dataSetManageFunctions.get(position).);
        myViewHolderDefault.tv_manage_function_Name.setText(functionTypeMoneyAmount.name);

        myViewHolderDefault.tv_manage_function_number.setText(functionTypeMoneyAmount.number+"");
    }
    @Override
    public int getItemCount() {
        return dataSetManageFunctions.size();
    }
    /**    Add new Item to recyclerView  and update   **/
    void addItemToRecycler(ModelItemManageFunctions newItem) {
        dataSetManageFunctions.add(newItem);
        notifyDataSetChanged();
    }
    /**    remove Item from recyclerView and update    **/
    void deleteItemToRecycler(int selectedToDelete, ModelItemManageFunctions newItem) {
        dataSetManageFunctions.remove(selectedToDelete);
        notifyItemRemoved(selectedToDelete);
    }
}
