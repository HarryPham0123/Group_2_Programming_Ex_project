package com.surveyapp.service;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> getAll();

    Optional<T> get(String id);

    void save(T t);

    void update(String code, T t);

    void delete(String code);
}
