package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume r = new Resume("Ivan");
        Class classResume = Resume.class;
        Method method = classResume.getMethod("toString", null);
        System.out.println(method.invoke(r));
    }

}
