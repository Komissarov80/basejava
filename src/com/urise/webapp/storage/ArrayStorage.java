package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveArrayResume(Resume resume, Object searchKey) {
        STORAGE[size] = resume;
    }

    public void deleteResumeArray(Object searchKey) {
        STORAGE[(Integer) searchKey] = STORAGE[size - 1];
        STORAGE[size - 1] = null;
    }

    @Override
    public Object getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public boolean isExist(String uuid) {
        return getSearchKey(uuid) == null ? false : true;
    }

}
