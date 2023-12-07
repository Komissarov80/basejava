package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapStorage extends AbstractStorage {

    public final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void updateResume(Object searchKey, Resume resume) {
        storage.put((String) searchKey, resume);
    }

    @Override
    public void saveResume(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public Object getSearchKey(String uuid) {
        if (isExist(uuid)) {
            return uuid;
        } else {
            return null;
        }
    }

    @Override
    public void deleteResume(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    public Resume getResume(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    public boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    public List<Resume> getAllResumes() {
        return storage.values().stream().collect(Collectors.toList());
    }
}
