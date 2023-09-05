package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveResume(Resume resume) {
        int indexElement = Arrays.binarySearch(STORAGE, 0, size, resume);
        int indexResume = Math.abs(indexElement) - 1;
        System.arraycopy(STORAGE, indexResume, STORAGE, indexResume + 1, size - indexResume);
        STORAGE[indexResume] = resume;
    }

    @Override
    public void deleteResume(int index) {
        System.arraycopy(STORAGE, index + 1, STORAGE, index, size - index - 1);
    }

    @Override
    public int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        int index = Arrays.binarySearch(STORAGE, 0, size, resume);
        if (index < 0) {
            return -1;
        }
        return index;
    }

    @Override
    public String toString() {
        return Arrays.toString(STORAGE);
    }

}
