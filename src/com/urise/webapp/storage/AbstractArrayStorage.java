package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractArrayStorage extends AbstractStorage {

    public final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    @Override
    public final void saveResume(Resume resume, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("storage is full, can't add resume ", resume.getUuid());
        }
        saveArrayResume(resume, searchKey);
        size++;
    }

    public int size() {
        return size;
    }

    @Override
    public List<Resume> getAllResumes() {
        List<Resume> resumesList = new ArrayList<>(Arrays.asList(STORAGE).stream().filter(resume -> resume != null).collect(
                Collectors.toList()));
        return resumesList;
    }

    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    public final Resume getResume(Object searchKey) {
        return STORAGE[(Integer) searchKey];
    }

    @Override
    public void deleteResume(Object searchKey) {
        deleteResumeArray(searchKey);
        size--;
    }

    @Override
    public String toString() {
        List<Resume> listResumes = new ArrayList<>(Arrays.stream(STORAGE).filter((resume) -> resume != null).collect(Collectors.toList()));
        return listResumes.toString();
    }

    @Override
    public final void updateResume(Object searchKey, Resume resume) {
        STORAGE[(Integer) searchKey] = resume;
    }

    public final Resume getPerIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("wrong index");
            return null;
        }
        return STORAGE[index];
    }

    public abstract void saveArrayResume(Resume resume, Object searchKey);

    public abstract void deleteResumeArray(Object searchKey);

    public abstract Object getSearchKey(String uuid);

}
