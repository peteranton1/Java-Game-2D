package com.derby.swing1.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Message {
    private String title;
    private String content;
}
