package de.fahid.java.pc_verwaltung;

import de.fahid.java.LanguageManager;

public class PCVerwaltungException extends Exception {
    public PCVerwaltungException(String message) {
        super(message);
    }
    public PCVerwaltungException(String message, Throwable cause) {
        super(message, cause);
    }
    public PCVerwaltungException(Throwable cause) {
        super( cause);
    }
    public PCVerwaltungException() {
        super( LanguageManager.getString("UnknownPcManagementException") );
    }
}
