package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MapStorageNameTest {

    private final Storage storage;
    public static final String fullName1 = "Vasa";
    public static final String fullName2 = "Peter";
    public static final Resume r1 = new Resume("1111", "Misha");
    public static final Resume r2 = new Resume("2222", "Dima");
    public static final Resume r3 = new Resume("3333", "Anton");

    public MapStorageNameTest() {
        storage = new MapStorageName();
    }

    @BeforeEach
    public void setValue() {
        storage.clear();
        storage.save(r1);
        storage.save(r3);
        storage.save(r2);
    }

    @Test
    void save() {
        assertSize(3);
        Resume r4 = new Resume("4444", "Valera");
        storage.save(r4);
        assertSize(4);
    }

    @Test
    void saveException() {
        Resume r4 = new Resume("1111", "Misha");
        assertThrows(ExistStorageException.class, () -> storage.save(r4));
        Resume r5 = null;
        assertThrows(IllegalArgumentException.class, () -> storage.save(r5));
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void getAllSorted() {
        List resumesTest = new ArrayList<Resume>();
        resumesTest.add(r3);
        resumesTest.add(r2);
        resumesTest.add(r1);
        assertArrayEquals(resumesTest.toArray(), storage.getAllSortedByName().toArray());
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        List resumes = new ArrayList<Resume>();
        assertArrayEquals(resumes.toArray(), storage.getAllSortedByName().toArray());
    }

    @Test
    void get() {
        assertGet(r1);
        assertGet(r2);
        assertGet(r3);
    }

    @Test
    void getException() {
        assertThrows(NotExistStorageException.class, () -> storage.get(fullName1));
    }

    @Test
    void delete() {
        storage.delete("Dima");
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.get("Dima"));
    }

    @Test
    void deleteException() {
        assertThrows(NotExistStorageException.class, () -> storage.delete(fullName2));
    }

    @Test
    void update() {
        Resume r4 = new Resume("4444", "Anton");
        storage.update(r4);
        assertSame(storage.get("Anton"), r4);
    }

    @Test
    void updateException() {
        Resume r4 = new Resume("4444", "Boris");
        assertThrows(NotExistStorageException.class, () -> storage.update(r4));
    }

    public void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    public void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getFullName()));
    }
}