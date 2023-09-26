package thisisus.school.socket.model;

import lombok.Getter;

@Getter
public enum MessageType {
    Enter,
    Talk;

    private String value;
}