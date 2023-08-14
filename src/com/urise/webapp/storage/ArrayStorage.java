package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int STORAGE_LIMIT = 10000;
    private final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    private int size;

    /*
   More suitable method from Arrays class for method clear is fill
    */
    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.uuid);
        if (size == STORAGE.length) {
            System.out.println("storage is full, can't add resume, size is " + size);
        } else if (index != -1) {
            System.out.println("already there are in storage resume whit uuid=" + r.uuid);
        } else {
            STORAGE[size++] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return STORAGE[index];
        }
        System.out.println("there are not in storage resume whit uuid=" + uuid + " to getting");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("there are not in storage resume whit uuid=" + uuid + " to deleting");
            return;
        }
        STORAGE[index] = STORAGE[size - 1];
        STORAGE[--size] = null;
    }

    public void update(Resume resume) {
        int resultGetIndex = getIndex(resume.uuid);
        if (resultGetIndex == -1) {
            System.out.println("there are not in storage resume whit uuid=" + resume.uuid + " to updating");
            return;
        }
        STORAGE[resultGetIndex] = resume;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     * More suitable method from Arrays class for method getAll is copyOf whit size length
     */
    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE, size);
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
