package com.example.paulomello.banca_valdir.providers;

import java.util.List;

public interface SQLiteGenericDAO<T> {

    long create(T t) throws  Exception;
    void update(T t) throws  Exception;
    void delete(T t) throws  Exception;
    T searchById(long id) throws  Exception;
    T searchByName(String name) throws  Exception;
    List<T> searchAll() throws  Exception;

}
