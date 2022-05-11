package de.fahid.java;

import java.util.ResourceBundle;

public class LanguageManager {
    private static LanguageManager instance;
    public final static String LanguageResourceBundleString = "language";
    public static ResourceBundle Language = ResourceBundle.getBundle(LanguageResourceBundleString);

    public static String getString(String key) {
        String value = null;
        if(!Language.containsKey(key)) {
            value = Language.getString("StringNotFound");
        } else {
            value = Language.getString(key);
        }
        return value;
    }
}
