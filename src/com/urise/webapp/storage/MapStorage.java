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
    public void updateResume(Object index, Resume r) {
        if (isExist(r.getUuid()) == null) {
            throw new NotExistStorageException(r.getUuid());
        }
        storage.put(r.getUuid(), r);
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
    public void deleteResume(String uuid, Object index) {
        if (isExist(uuid) == null) {
            throw new NotExistStorageException(uuid);
        }
        storage.remove(uuid);
    }

    @Override
    public Resume getResume(String uuid, Object index) {
        if (isExist(uuid) == null) {
            throw new NotExistStorageException(uuid);
        }
        return storage.get(uuid);
    }

    @Override
    public String isExist(String uuid) {
        if (storage.get(uuid) == null) {
            return null;
        }
        return storage.get(uuid).getUuid();
    }
}
