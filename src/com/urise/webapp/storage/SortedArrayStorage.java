package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void addAndIncrementResume(Resume resume) {
        if (size == 0) {
            STORAGE[size++] = resume;
        } else {
            int indexElement = Arrays.binarySearch(STORAGE, 0, size, resume);
            if (indexElement == -1) {
                System.arraycopy(STORAGE, 0, STORAGE, 1, size);
                STORAGE[0] = resume;
                size++;
            } else {
                int indexResume = Math.abs(indexElement) - 1;
                System.arraycopy(STORAGE, indexResume, STORAGE, indexResume + 1, size - indexResume);
                STORAGE[indexResume] = resume;
                size++;
            }
        }
    }

    @Override
    public void deleteResume(int index) {
        System.arraycopy(STORAGE, index + 1, STORAGE, index, size - index - 1);
        size--;
    }

    @Override
    public String toString() {
        return Arrays.toString(STORAGE);
    }

}
