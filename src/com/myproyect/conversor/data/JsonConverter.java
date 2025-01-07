package com.myproyect.conversor.data;

public interface JsonConverter {
    <T> T fromJson(String json, Class<T> classOfT);
}
