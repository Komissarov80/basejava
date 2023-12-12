package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected final Comparator<Resume> comparator =
            Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    @Override
    public void update(Resume resume) {
        Object searchKey = getExistingSearchKey(resume.getUuid());
        updateResume(searchKey, resume);
    }

    @Override
    public void save(Resume resume) {
        if (resume == null) {
            throw new IllegalArgumentException("Can't save resume. Resume is null");
        }
        Object searchKey = getNotExistingSearchKey(resume.getUuid());
        saveResume(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        deleteResume(searchKey);
    }

    public final List<Resume> getAllSorted() {
        List<Resume> resumesList = getAllResumes();
        resumesList.sort(comparator);
        return resumesList;
    }

    protected Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(uuid)) {
            return searchKey;
        }
        throw new ExistStorageException(uuid);
    }

    protected Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public abstract void updateResume(Object searchKey, Resume r);

    public abstract void saveResume(Resume resume, Object searchKey);

    public abstract Object getSearchKey(String uuid);

    public abstract void deleteResume(Object searchKey);

    public abstract Resume getResume(Object searchKey);

    public abstract boolean isExist(String uuid);

    public abstract List<Resume> getAllResumes();
}
