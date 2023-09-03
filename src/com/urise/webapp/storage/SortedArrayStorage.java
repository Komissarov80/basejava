package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public boolean saveResume(Resume resume) {
        if (STORAGE.length == size) {
            System.out.println("storage is full, can't add resume, size is " + size);
            return false;
        }
        if (size == 0) {
            STORAGE[size] = resume;
            return true;
        } else {
            int indexElement = Arrays.binarySearch(STORAGE, 0, size, resume);
            if (indexElement == -1) {
                System.arraycopy(STORAGE, 0, STORAGE, 1, size);
                STORAGE[0] = resume;
                return true;
            } else {
                int indexResume = Math.abs(indexElement) - 1;
                System.arraycopy(STORAGE, indexResume, STORAGE, indexResume + 1, size - indexResume);
                STORAGE[indexResume] = resume;
                return true;
            }
        }
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
