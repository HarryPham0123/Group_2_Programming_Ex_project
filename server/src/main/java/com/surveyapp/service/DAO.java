package com.surveyapp.service;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> getAll();

    Optional<T> get(String id);

    boolean save(T t);

    boolean update(String code, T t);

    boolean delete(String code);
}
