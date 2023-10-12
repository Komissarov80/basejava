package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    public final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    public final void saveResume(Resume resume, int index) {
        if (size + 1 > STORAGE_LIMIT) {
            throw new StorageException("storage is full, can't add resume ", resume.getUuid());
        }
        saveArrayResume(resume, index);
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

    public final Resume getResume(String uuid, int index) {
        return STORAGE[index];
    }

    @Override
    public void deleteResume(String uuid, int index) {
        deleteResumeArray(index);
        size--;
    }

    @Override
    public final void updateResume(int index, Resume resume) {
        STORAGE[index] = resume;
    }

    public final Resume getPerIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("wrong index");
            return null;
        }
        return STORAGE[index];
    }

    public abstract void saveArrayResume(Resume resume, int index);

    public abstract void deleteResumeArray(int index);

    public abstract int getIndex(String uuid);

}
