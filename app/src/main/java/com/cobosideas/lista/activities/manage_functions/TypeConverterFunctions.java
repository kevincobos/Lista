package com.cobosideas.lista.activities.manage_functions;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

class TypeConverterFunctions {
    @TypeConverter
    public static FunctionTypeMoneyAmount getFunctionTypeMoneyAmount(String stringFunctionMoney){
        if (stringFunctionMoney == null) return null;
        Type listType = new TypeToken<FunctionTypeMoneyAmount>() { }.getType();
        return new Gson().fromJson(stringFunctionMoney, listType);
    }
    @TypeConverter
    public static String getStringFunctionTypeMoney(FunctionTypeMoneyAmount functionTypeMoneyAmount){
        if (functionTypeMoneyAmount == null) return null;
        Type listType = new TypeToken<FunctionTypeMoneyAmount>() { }.getType();
        return new Gson().toJson(functionTypeMoneyAmount, listType);
    }
}
