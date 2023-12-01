package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;

import java.util.Arrays;

public class MainTestSortedArrayStorage {

    static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static String[] rightOrderSave = {"Alex", "Andrey", "Dima", "Sasha", "Slava"};
    public static String[] rightOrderDelete = {"Alex", "Dima", "Sasha", "Slava"};

    public static void main(String[] args) {
        Resume r1 = new Resume("1","Alex");
        Resume r2 = new Resume("2","Sasha");
        Resume r3 = new Resume("3","Andrey");
        Resume r4 = new Resume("4","Dima");
        Resume r5 = new Resume("5","Slava");
        SORTED_ARRAY_STORAGE.save(r1); // Alex   \ Alex
        SORTED_ARRAY_STORAGE.save(r2);//Sasha     \Sasha
        SORTED_ARRAY_STORAGE.save(r5);//Slava    \ Andrey
        SORTED_ARRAY_STORAGE.save(r3);//Andrey   \ Dima
        SORTED_ARRAY_STORAGE.save(r4);//Dima     ] Slava

        System.out.println("sorted is " + Arrays.toString(SORTED_ARRAY_STORAGE.STORAGE));
        System.out.println(testSaveMethod());
        SORTED_ARRAY_STORAGE.delete("3");
        System.out.println(testDeleteMethod());
        System.out.println(testUpdateMethod());
        System.out.println(testGetIndex());
        SORTED_ARRAY_STORAGE.delete("3");
    }

    public static String testDeleteMethod() {
        for (int i = 0; i < SORTED_ARRAY_STORAGE.size(); i++) {
            if (!SORTED_ARRAY_STORAGE.STORAGE[i].getFullName().equals(rightOrderDelete[i])) {
                return "delete method fail";
            }
        }
        return "delete method passed";
    }

    public static String testSaveMethod() {
        for (int i = 0; i < SORTED_ARRAY_STORAGE.size(); i++) {
            System.out.println("in mainTest sortedArrayStorage is " + SORTED_ARRAY_STORAGE.STORAGE[i].getFullName() + " in array " + rightOrderSave[i]);
            if (!SORTED_ARRAY_STORAGE.STORAGE[i].getFullName().equals(rightOrderSave[i])) {
                return "save method fail";
            }
        }
        return "save method passed";
    }

    public static String testUpdateMethod() {
        int index = (int) SORTED_ARRAY_STORAGE.getSearchKey("6");
        Resume r6 = new Resume("6","Sasha");
        SORTED_ARRAY_STORAGE.update(r6);
        if (SORTED_ARRAY_STORAGE.STORAGE[index] != r6) {
            return "update method fail";
        }
        return "update method passed";
    }

    public static String testGetIndex() {
        // index of resume with name Sasha
        int indexSasha = 2;
        int answerForSasha = (int) SORTED_ARRAY_STORAGE.getSearchKey("Sasha");
        // in STORAGE there are not Dasha, so getIndex() must return -1
        int indexDasha = -1;
        int answerForDasha = (int) SORTED_ARRAY_STORAGE.getSearchKey("Dasha");
        if (indexSasha != 2 || indexDasha != -1) {
            return "testGetIndex failed";
        }
        return "testGetIndex passed";
    }

}
