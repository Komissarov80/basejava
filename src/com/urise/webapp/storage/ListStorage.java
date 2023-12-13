package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    public static final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void updateResume(Object searchKey, Resume r) {
        storage.set((Integer) searchKey, r);
    }

    @Override
    public void saveResume(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    public Resume getResume(Object searchKey) {
        return storage.get((Integer) searchKey);

    }

    @Override
    public boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public List<Resume> getAllResumes() {
        return storage;
    }

    @Override
    public void deleteResume(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Object getSearchKey(String uuid) {
        int searchKey = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                searchKey = i;
            }
        }
        return searchKey >= 0 ? searchKey : null;
    }

}
