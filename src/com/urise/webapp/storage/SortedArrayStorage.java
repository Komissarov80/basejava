package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveArrayResume(Resume resume, int index) {
        int indexResume = Math.abs(index) - 1;
        System.arraycopy(STORAGE, indexResume, STORAGE, indexResume + 1, size - indexResume);
        STORAGE[indexResume] = resume;
    }

    @Override
    public void deleteResumeArray(int index) {
        System.arraycopy(STORAGE, index + 1, STORAGE, index, size - index - 1);
    }

    @Override
    public int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return Arrays.binarySearch(STORAGE, 0, size, resume);
    }

    @Override
    public String toString() {
        return Arrays.toString(STORAGE);
    }

}
