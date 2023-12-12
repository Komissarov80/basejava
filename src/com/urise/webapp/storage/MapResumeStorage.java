package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void saveResume(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Resume getResume(Object searchKey) {
        return storage.get(((Resume) searchKey).getUuid());
    }

    @Override
    public boolean isExist(String uuid) {
        return getSearchKey(uuid) != null;
    }

    @Override
    public List<Resume> getAllResumes() {
        List<Resume> resumeList = new ArrayList<>();
        resumeList.addAll(storage.values());
        return resumeList;
    }

    @Override
    public void updateResume(Object searchKey, Resume resume) {
        Resume currentResume = (Resume) searchKey;
        storage.put(currentResume.getUuid(), resume);
    }

    @Override
    public void deleteResume(Object searchKey) {
        Resume r = (Resume) searchKey;
        storage.remove(r.getUuid());
    }

}
