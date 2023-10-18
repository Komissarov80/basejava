package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveArrayResume(Resume resume) {
        int result = Math.abs(Arrays.binarySearch(STORAGE, 0, size, resume)) - 1;
        System.arraycopy(STORAGE, result, STORAGE, result + 1, size - result);
        STORAGE[result] = resume;
    }

    @Override
    public void deleteResumeArray(Object index) {
        System.arraycopy(STORAGE, (Integer)index + 1, STORAGE, (Integer)index, size - (Integer)index - 1);
    }

    @Override
    public Object getSearchKey(String uuid) {
        Resume resume = new Resume(uuid);
        int result = Arrays.binarySearch(STORAGE, 0, size, resume);
        if (result < 0) {
            return null;
        } else {
            return Arrays.binarySearch(STORAGE, 0, size, resume);
        }
    }

    @Override
    public String isExist(String uuid) {
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(STORAGE);
    }

}
