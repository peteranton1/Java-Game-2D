package com.derby.swing1;

import java.util.EventListener;

public interface FormListener extends EventListener {
    void formEventOccurred(FormEvent e);
}
