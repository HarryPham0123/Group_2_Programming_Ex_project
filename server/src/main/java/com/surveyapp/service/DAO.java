package com.surveyapp.service;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> getAll() throws Exception;

    Optional<T> get(String id) throws Exception;

    void save(T t) throws Exception;

    void update(String code, T t) throws Exception;

    void delete(String code)throws Exception;
}
