package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        updateResume(getExistingSearchKey(r.getUuid()), r);
    }

    @Override
    public void save(Resume resume) {
        if (resume == null) {
            throw new IllegalArgumentException("Can't save resume. Resume is null");
        }
        saveResume(resume, getNotExistingSearchKey(resume.getUuid()));
    }

    @Override
    public Resume get(String uuid) {
        return getResume(getExistingSearchKey(uuid));
    }

    @Override
    public void delete(String uuid) {
        deleteResume(getExistingSearchKey(uuid));
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(uuid)) {
            return searchKey;
        }
        throw new ExistStorageException(uuid);
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public abstract void updateResume(Object searchKey, Resume r);

    public abstract void saveResume(Resume resume, Object searchKey);

    public abstract Object getSearchKey(String uuid);

    public abstract void deleteResume(Object searchKey);

    public abstract Resume getResume(Object searchKey);

    public abstract boolean isExist(String uuid);
}
