package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public boolean saveResume(Resume r) {
        if (size == STORAGE.length) {
            System.out.println("storage is full, can't add resume, size is " + size);
            return false;
        }
        STORAGE[size] = r;
        return true;
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
        STORAGE[size] = null;
    }

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

}
