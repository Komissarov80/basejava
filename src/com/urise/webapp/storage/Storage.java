package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface Storage {
    void clear();
    void update(Resume r);
    void save(Resume r);
    Resume get(String uuid);
    List<Resume> getAllSorted();
    void delete(String uuid);
    int size();
}
