package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        updateResume(getNotExistingSearchKey(r.getUuid()), r);
    }

    @Override
    public void save(Resume r) {
        if (r == null) {
            throw new IllegalArgumentException("Can't save resume. Resume is null");
        }
        getExistingSearchKey(r.getUuid());
        saveResume(r);
    }

    @Override
    public Resume get(String uuid) {
        return getResume(uuid, getNotExistingSearchKey(uuid));
    }

    @Override
    public void delete(String uuid) {
        deleteResume(uuid, getNotExistingSearchKey(uuid));
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (searchKey != null) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (searchKey == null) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public abstract void clear();

    @Override
    public abstract int size();

    @Override
    public abstract Resume[] getAll();

    public abstract void updateResume(Object index, Resume r);

    public abstract void saveResume(Resume r);

    public abstract Object getSearchKey(String uuid);

    public abstract void deleteResume(String uuid, Object index);

    public abstract Resume getResume(String uuid, Object index);

    public abstract String isExist(String uuid);
}
