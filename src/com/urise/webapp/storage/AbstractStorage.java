package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            updateResume(index, r);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void save(Resume r) {
        if (r == null) {
            throw new IllegalArgumentException("Can't save resume. Resume is null");
        }
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        saveResume(r, index);
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return getResume(uuid, index);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(uuid, index);
        }
    }

    @Override
    public abstract void clear();

    @Override
    public abstract int size();

    @Override
    public abstract Resume[] getAll();

    public abstract void updateResume(int index, Resume r);

    public abstract void saveResume(Resume r, int index);

    public abstract int getIndex(String uuid);

    public abstract void deleteResume(String uuid, int index);

    public abstract Resume getResume(String uuid, int index);
}
