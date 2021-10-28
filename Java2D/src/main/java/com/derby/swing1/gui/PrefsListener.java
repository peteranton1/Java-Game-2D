package com.derby.swing1.gui;

public interface PrefsListener {
    void preferencesSet(String user,
                        String password,
                        int portNo);
}
