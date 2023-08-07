package com.urise.storage;

import com.urise.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int STORAGE_LIMIT = 10000;
    private final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    private int size;

    /*
   More suitable method from Arrays class for method clear is copyOf whit zero length
    */
    public void clear() {
        for (int i = 0; i < size; i++) {
            STORAGE[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        int resultGetIndex = getIndex(r.uuid);
        if (size == STORAGE.length) {
            System.out.println("storage is full, can't add resume, size is " + size);
            return;
        } else if (resultGetIndex != -1) {
            System.out.println("already there are in storage resume whit uuid=" + r.uuid);
        } else {
            STORAGE[size++] = r;
        }
    }

    public Resume get(String uuid) {
        int resultGetIndex = getIndex(uuid);
        if (resultGetIndex != -1) {
            return STORAGE[resultGetIndex];
        } else {
            System.out.println("there are not in storage resume whit uuid=" + uuid + " to getting");
        }
        return null;
    }

    public void delete(String uuid) {
        int resultGetIndex = getIndex(uuid);
        if (resultGetIndex != -1) {
            STORAGE[resultGetIndex] = STORAGE[size - 1];
            STORAGE[--size] = null;
        } else {
            System.out.println("there are not in storage resume whit uuid=" + uuid + " to deleting");
        }
    }

    public void update(Resume resume) {
        int resultGetIndex = getIndex(resume.uuid);
        if (resultGetIndex != -1) {
            STORAGE[resultGetIndex] = resume;
        } else {
            System.out.println("there are not in storage resume whit uuid=" + resume.uuid + " to updating");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     * More suitable method from Arrays class for method getAll is copyOf whit size length
     */
    public Resume[] getAll() {
        return java.util.Arrays.copyOf(STORAGE, size);
    }

    public int size() {
        return size;
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
