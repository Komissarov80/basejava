package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MapStorageName extends MapStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void save(Resume resume) {
        if (resume == null) {
            throw new IllegalArgumentException("Can't save resume. Resume is null");
        }
        getNotExistingSearchKey(resume.getFullName());
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public Object getSearchKey(String fullName) {
        Set<Map.Entry<String, Resume>> entrySet = storage.entrySet();
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            String key = entry.getKey();
            String name = entry.getValue().getFullName();
            if (name.equals(fullName)) {
                return key;
            }
        }
        return null;
    }

    @Override
    public boolean isExist(String fullName) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getValue().getFullName().equals(fullName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public List<Resume> getAllSortedByName() {
        return storage.values().stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume getResume(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getExistingSearchKey(resume.getFullName());
        storage.put((String) searchKey, resume);
    }

    @Override
    public void deleteResume(Object searchKey) {
        storage.remove((String) searchKey);
    }

    private String getKeyByFullName(String fullName) {
        Set<Map.Entry<String, Resume>> entrySet = storage.entrySet();
        for (Map.Entry<String, Resume> entry : entrySet) {
            if (entry.getValue().equals(fullName)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
