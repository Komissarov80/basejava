package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    public final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void save(Resume resume) {
        if (resume == null) {
            throw new IllegalArgumentException("Can't save resume. Resume is null");
        }
        int index = getIndex(resume.getUuid());
        if (size + 1 > STORAGE_LIMIT) {
            throw new StorageException("storage is full, can't add resume ", resume.getUuid());
        }
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(resume, index);
        size++;
    }

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE, size);
    }

    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return STORAGE[index];
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteResume(index);
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        STORAGE[index] = resume;
    }

    public final Resume getPerIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("wrong index");
            return null;
        }
        return STORAGE[index];
    }

    public abstract void saveResume(Resume resume, int index);

    public abstract void deleteResume(int index);

    public abstract int getIndex(String uuid);

}
