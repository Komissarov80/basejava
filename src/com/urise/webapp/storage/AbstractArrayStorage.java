package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    public final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (STORAGE.length == size) {
            System.out.println("storage is full, can't add resume, size is " + size);
            return;
        }
        if (index >= 0) {
            System.out.println("Can't save resume. Storage contain resume with uuid " + resume.getUuid());
            return;
        }
        saveResume(resume, index);
        size++;
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

    public final Resume get(String uuid) {
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

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("there are not in storage resume whit uuid=" + resume.getUuid() + " to updating");
            return;
        }
        STORAGE[index] = resume;
    }

    public abstract void saveResume(Resume resume, int index);

    public abstract void deleteResume(int index);

    public abstract int getIndex(String uuid);

}
