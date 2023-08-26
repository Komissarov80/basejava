package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void addAndIncrementResume(Resume r) {
        STORAGE[size++] = r;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return STORAGE[index];
        }
        System.out.println("there are not in storage resume whit uuid=" + uuid + " to getting");
        return null;
    }

    public void deleteResume(int index) {
        STORAGE[index] = STORAGE[size - 1];
        STORAGE[--size] = null;
    }

    public int size() {
        return size;
    }

}
