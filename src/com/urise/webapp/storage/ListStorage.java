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
        storage.set((Integer)searchKey, r);
    }

    @Override
    public void saveResume(Resume r) {
        storage.add(r);
    }

    @Override
    public Resume getResume(Object searchKey) {
        return storage.get((Integer)searchKey);

    }

    @Override
    public String isExist(String uuid) {
        return null;
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public void deleteResume(Object searchKey) {
        storage.remove((int)searchKey);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Object getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

}
