package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    public static final List<Resume> listResumes = new ArrayList();

    @Override
    public void clear() {
        listResumes.clear();
    }

    @Override
    public void updateResume(int index, Resume r) {
        listResumes.set(index, r);
    }

    @Override
    public void saveResume(Resume r, int index) {
        listResumes.add(r);
    }

    @Override
    public Resume getResume(String uuid, int index) {
        return listResumes.get(index);

    }

    @Override
    public Resume[] getAll() {
        return listResumes.toArray(new Resume[0]);
    }

    @Override
    public void deleteResume(String uuid, int index) {
        listResumes.remove(index);
    }

    @Override
    public int size() {
        return listResumes.size();
    }

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (listResumes.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
