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
    public void updateResume(Object index, Resume r) {
        storage.set((Integer)index, r);
    }

    @Override
    public void saveResume(Resume r) {
        storage.add(r);
    }

    @Override
    public Resume getResume(String uuid, Object index) {
        return storage.get((Integer)index);

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
    public void deleteResume(String uuid, Object index) {
        storage.remove((int)index);
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
