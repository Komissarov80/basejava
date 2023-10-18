package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    public final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    @Override
    public final void saveResume(Resume resume) {
        if (size + 1 > STORAGE_LIMIT) {
            throw new StorageException("storage is full, can't add resume ", resume.getUuid());
        }
        saveArrayResume(resume);
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

    public final Resume getResume(String uuid, Object index) {
        return STORAGE[(Integer)index];
    }

    @Override
    public void deleteResume(String uuid, Object index) {
        deleteResumeArray(index);
        size--;
    }

    @Override
    public final void updateResume(Object index, Resume resume) {
        STORAGE[(Integer) index] = resume;
    }

    public final Resume getPerIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("wrong index");
            return null;
        }
        return STORAGE[index];
    }

    public abstract void saveArrayResume(Resume resume);

    public abstract void deleteResumeArray(Object index);

    public abstract Object getSearchKey(String uuid);

}
