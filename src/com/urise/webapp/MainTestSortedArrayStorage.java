package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;

public class MainTestSortedArrayStorage {

    static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static String[] rightOrderSave = {"Alex", "Andrey", "Dima", "Sasha", "Slava"};
    public static String[] rightOrderDelete = {"Alex", "Dima", "Sasha", "Slava"};

    public static void main(String[] args) {
        Resume r1 = new Resume("Alex");
        Resume r2 = new Resume("Sasha");
        Resume r3 = new Resume("Andrey");
        Resume r4 = new Resume("Dima");
        Resume r5 = new Resume("Slava");
        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r2);
        SORTED_ARRAY_STORAGE.save(r5);
        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r4);


        System.out.println(testSaveMethod());
        SORTED_ARRAY_STORAGE.delete("Andrey");
        System.out.println(testDeleteMethod());
        System.out.println(testUpdateMethod());
        System.out.println(testGetIndex());
        SORTED_ARRAY_STORAGE.delete("Andrey");
    }

    public static String testDeleteMethod() {
        for (int i = 0; i < SORTED_ARRAY_STORAGE.size(); i++) {
            if (!SORTED_ARRAY_STORAGE.STORAGE[i].getUuid().equals(rightOrderDelete[i])) {
                return "delete method fail";
            }
        }
        return "delete method passed";
    }

    public static String testSaveMethod() {
        for (int i = 0; i < SORTED_ARRAY_STORAGE.size(); i++) {
            if (!SORTED_ARRAY_STORAGE.STORAGE[i].getUuid().equals(rightOrderSave[i])) {
                return "save method fail";
            }
        }
        return "save method passed";
    }

    public static String testUpdateMethod() {
        int index = SORTED_ARRAY_STORAGE.getIndex("Sasha");
        Resume r6 = new Resume("Sasha");
        SORTED_ARRAY_STORAGE.update(r6);
        if (SORTED_ARRAY_STORAGE.STORAGE[index] != r6) {
            return "update method fail";
        }
        return "update method passed";
    }

    public static String testGetIndex() {
        // index of resume with name Sasha
        int indexSasha = 2;
        int answerForSasha = SORTED_ARRAY_STORAGE.getIndex("Sasha");
        // in STORAGE there are not Dasha, so getIndex() must return -1
        int indexDasha = -1;
        int answerForDasha = SORTED_ARRAY_STORAGE.getIndex("Dasha");
        if (indexSasha != 2 || indexDasha != -1) {
            return "testGetIndex failed";
        }
        return "testGetIndex passed";
    }

}
