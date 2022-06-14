package com.gson.shade;

import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.Paranamer;

import java.lang.reflect.AccessibleObject;

public class Parameter {
    private  Paranamer paranamer;

    public Paranamer getParanamer() {
        if (paranamer != null) {
            return paranamer;
        }
        paranamer = new BytecodeReadingParanamer();
        return paranamer;
    }

    public String[] getParameterNames(AccessibleObject accessibleObject) {
        String[] names = getParanamer().lookupParameterNames(accessibleObject, false);
        return names;
    }
}
