package com.cobosideas.lista.activities.manage_functions;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class TypeConverterFunctions {
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
    @TypeConverter
    public static FunctionTypeReminder getFunctionTypeRemainder(String stringFunctionRemainder){
        if (stringFunctionRemainder == null) return null;
        Type listType = new TypeToken<FunctionTypeReminder>() { }.getType();
        return new Gson().fromJson(stringFunctionRemainder, listType);
    }
    @TypeConverter
    public static String getStringFunctionTypeRemainder(FunctionTypeReminder functionTypeReminder){
        if (functionTypeReminder == null) return null;
        Type listType = new TypeToken<FunctionTypeReminder>() { }.getType();
        return new Gson().toJson(functionTypeReminder, listType);
    }
}
