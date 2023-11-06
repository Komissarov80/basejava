package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

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
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
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
        storage.remove(searchKey);
    }

    @Override
    public Resume getResume(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    public boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }
}
