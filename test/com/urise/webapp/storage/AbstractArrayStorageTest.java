package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;
    public static final String uuid1 = "Misha";
    public static final String uuid2 = "Dima";
    public static final String uuid3 = "Anton";
    public static final String uuid4 = "Valera";
    public static final String uuid5 = "Ura";
    public static final Resume r1 = new Resume(uuid1);
    public static final Resume r2 = new Resume(uuid2);
    public static final Resume r3 = new Resume(uuid3);


    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setValue() {
        storage.save(r3);
        storage.save(r2);
        storage.save(r1);
    }

    @Test
    void save() {
        assertSize(3);
        Resume r4 = new Resume(uuid4);
        storage.save(r4);
        assertGet(r4);
    }

    @Test
    void saveException() {
        Resume r4 = new Resume(uuid1);
        assertThrows(ExistStorageException.class, () -> storage.save(r4));
        Resume r5 = null;
        assertThrows(IllegalArgumentException.class, () -> storage.save(r5));
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void getAll() {
        Resume[] resumesTest = {r3, r2, r1};
        assertArrayEquals(resumesTest, storage.getAll());
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        Resume[] resumes = {};
        assertArrayEquals(resumes, storage.getAll());
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
        Resume r4 = new Resume(uuid4);
        storage.save(r4);
        r4.setUuid(uuid5);
        assertSame(r4.getUuid(), uuid5);
    }

    @Test
    void updateException() {
        Resume r4 = new Resume(uuid4);
        assertThrows(NotExistStorageException.class, () -> storage.update(r4));
    }

    @Test
    void overflowArrayException() {
        Resume r4 = new Resume(uuid4);
        storage.clear();
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
                storage.save(new Resume(String.valueOf(i)));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            fail("Storage overflow ahead of time. Test failed");
        }
        assertSize(STORAGE_LIMIT);
        assertThrows(StorageException.class, () -> storage.save(r4));
    }

    public void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    public void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

}