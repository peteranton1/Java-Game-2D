package com.derby.swing1.gui;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
class ServerInfo {
    private int id;
    private String name;
    private String location;
    private boolean checked;

    public String toString() {
        return name;
    }
}