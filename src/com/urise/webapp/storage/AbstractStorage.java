package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    private void getExistingSearchKey(String uuid) {
        throw new ExistStorageException(uuid);
    }

    private void getNotExistingSearchKey(String uuid) {
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (searchKey == null) {
            getNotExistingSearchKey(r.getUuid());
        }
        updateResume(searchKey, r);
    }

    @Override
    public void save(Resume r) {
        if (r == null) {
            throw new IllegalArgumentException("Can't save resume. Resume is null");
        }
        Object searchKey = getSearchKey(r.getUuid());
        if (searchKey != null) {
             getExistingSearchKey(r.getUuid());
        }
        saveResume(r);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (searchKey == null) {
            getNotExistingSearchKey(uuid);
        }
        return getResume(uuid, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (searchKey == null) {
            getNotExistingSearchKey(uuid);
        }
        deleteResume(uuid, searchKey);
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
