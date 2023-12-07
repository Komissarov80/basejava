package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapStorageName extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void saveResume(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public Object getSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            String key = entry.getKey();
            Resume currentResume = entry.getValue();
            if (currentResume.getUuid().equals(uuid)) {
                return currentResume;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSortedByName() {
        return storage.values().stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Resume getResume(Object searchKey) {
        Resume r = (Resume) searchKey;
        return storage.get(r.getUuid());
    }

    @Override
    public boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    public List<Resume> getAllResumes() {
        return storage.values().stream().collect(Collectors.toList());
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
