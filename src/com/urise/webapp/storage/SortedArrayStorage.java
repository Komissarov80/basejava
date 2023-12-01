package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveArrayResume(Resume resume, Object searchKey) {
        System.arraycopy(STORAGE, Math.abs((Integer) searchKey) - 1, STORAGE, Math.abs((Integer) searchKey),
                size - (Math.abs((Integer) searchKey) - 1));
        STORAGE[Math.abs((Integer) searchKey) - 1] = resume;
    }

    @Override
    public void deleteResumeArray(Object searchKey) {
        System.arraycopy(STORAGE, (Integer) searchKey + 1, STORAGE, (Integer) searchKey,
                size - (Integer) searchKey - 1);
    }

    @Override
    public Object getSearchKey(String uuid) {
        return Arrays.binarySearch(STORAGE, 0, size, new Resume(uuid, uuid));
    }

    @Override
    public boolean isExist(String uuid) {
        return (Integer) getSearchKey(uuid) < 0 ? false : true;
    }

}
