package de.fahid.java.datenbank;

import de.fahid.java.LanguageManager;

public class DbConnectorException extends Exception {
    public DbConnectorException(String message) {
        super(message);
    }

    public DbConnectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbConnectorException(Throwable cause) {
        super(cause);
    }

    public DbConnectorException() {
        super(LanguageManager.getString("UnknownDbConnectorException"));
    }
}
