package com.ablanco.hashnestandroidapi;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Álvaro Blanco Cabrero on 4/10/15
 * HashnestAndroidAPISample
 */
class GsonMapper{

    private static GsonMapper mInstance;
    private Gson mGson;

    private GsonMapper(){

        this.mGson = new GsonBuilder()
                .setFieldNamingStrategy(new HashnestNamingStrategy())
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

    }

    public static GsonMapper getInstance(){
        if(mInstance == null){
            mInstance = new GsonMapper();
        }

        return mInstance;
    }

    public <T> T createObject(String json,Class<T> clazz){
        return this.mGson.fromJson(json,clazz);
    }

    public <T> ArrayList<T> createCollection(String json, final Class<T> clazz){

        return this.mGson.fromJson(json, new ListOfJson<T>(clazz));
    }

    public Boolean getSuccesResult(String json){
        return this.mGson.fromJson(json,BooleanResult.class).getSucces();
    }

    class HashnestNamingStrategy implements FieldNamingStrategy {

        @Override
        public String translateName(Field field) {
            return field.getName().replaceAll("([A-Z])", "_$1").toLowerCase();
        }

    }

    class ListOfJson<T> implements ParameterizedType
    {
        private Class<?> wrapped;

        public ListOfJson(Class<T> wrapper)
        {
            this.wrapped = wrapper;
        }

        @Override
        public Type[] getActualTypeArguments()
        {
            return new Type[] { wrapped };
        }

        @Override
        public Type getRawType()
        {
            return List.class;
        }

        @Override
        public Type getOwnerType()
        {
            return null;
        }
    }

    class BooleanResult{
        protected Boolean succes;

        public Boolean getSucces() {
            return succes;
        }

        public void setSucces(Boolean succes) {
            this.succes = succes;
        }
    }

}
