package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.sql.SQLOutput;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    public final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void save(Resume resume) {
        boolean checkResumeInStorage = checkResumInStorade(resume);
        if (checkResumeInStorage) {
            if (saveResume(resume)){
                size++;
            }
        }

    }

    public boolean checkResumInStorade(Resume resume) {
        if (resume == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (resume.getUuid().equals(STORAGE[i].getUuid())) {
                System.out.println("there are in storage resume whit uuid=" + resume.getUuid() + " can't adding");
                return false;
            }
        }
        return true;
    }

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE, size);
    }

    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return STORAGE[index];
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteResume(index);
            size--;
        } else {
            System.out.println("there are not in storage resume whit uuid=" + uuid + " to deleting");
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("there are not in storage resume whit uuid=" + resume.getUuid() + " to updating");
            return;
        }
        STORAGE[index] = resume;
    }

    public abstract boolean saveResume(Resume resume);

    public abstract void deleteResume(int index);

    public abstract int getIndex(String uuid);

}
