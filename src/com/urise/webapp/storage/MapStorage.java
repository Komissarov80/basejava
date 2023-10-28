package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
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
    public void updateResume(Object searchKey, Resume r) {
        if (isExist(r.getUuid()) == null) {
            throw new NotExistStorageException(r.getUuid());
        }
        storage.put((String) searchKey, r);
    }

    @Override
    public void saveResume(Resume r) {
        if (isExist(r.getUuid()) != null) {
            throw new ExistStorageException(r.getUuid());
        }
        storage.put(r.getUuid(), r);
    }

    @Override
    public Object getSearchKey(String uuid) {
        return isExist(uuid);
    }

    @Override
    public void deleteResume(Object searchKey) {
        if (isExist((String)searchKey) == null) {
            throw new NotExistStorageException((String) searchKey);
        }
        storage.remove(searchKey);
    }

    @Override
    public Resume getResume(Object searchKey) {
        if (isExist((String) searchKey) == null) {
            throw new NotExistStorageException((String) searchKey);
        }
        return storage.get(searchKey);
    }

    @Override
    public String isExist(String uuid) {
        if (storage.get(uuid) == null) {
            return null;
        }
        return storage.get(uuid).getUuid();
    }
}
