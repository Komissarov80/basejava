package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;
    public static final String uuid1 = "1111";
    public static final String uuid2 = "2222";
    public static final String uuid3 = "3333";
    public static final String uuid4 = "4444";
    public static final String uuid5 = "5555";
    public static final Resume r1 = new Resume("1111", "Misha");
    public static final Resume r2 = new Resume("2222", "Dima");
    public static final Resume r3 = new Resume("3333", "Anton");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
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
        Resume r4 = new Resume("1111", "Victor");
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
        assertArrayEquals(resumesTest.toArray(), storage.getAllSorted().toArray());
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        List resumes = new ArrayList<Resume>();
        assertArrayEquals(resumes.toArray(), storage.getAllSorted().toArray());
    }

    @Test
    void get() {
        assertGet(r1);
        assertGet(r2);
        assertGet(r3);
    }

    @Test
    void getException() {
        assertThrows(NotExistStorageException.class, () -> storage.get(uuid4));
    }

    @Test
    void delete() {
        storage.delete(uuid2);
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.get(uuid2));
    }

    @Test
    void deleteException() {
        assertThrows(NotExistStorageException.class, () -> storage.delete(uuid4));
    }

    @Test
    void update() {
        Resume r4 = new Resume("3333", "Ura");
        storage.update(r4);
        assertSame(storage.get("3333"), r4);
    }

    @Test
    void updateException() {
        Resume r4 = new Resume("4444", "Boris");
        assertThrows(NotExistStorageException.class, () -> storage.update(r4));
    }

    @Test
    void overflowArrayException() {
        storage.clear();
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
                storage.save(new Resume(String.valueOf(i), String.valueOf(i)));
            }
        } catch (ArrayIndexOutOfBoundsException | StorageException e) {
            fail("Storage fills wrong. Test failed");
        }
        assertSize(STORAGE_LIMIT);
        Resume r4 = new Resume("4444", "Boris");
        assertThrows(StorageException.class, () -> storage.save(r4));
    }

    public void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    public void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

}