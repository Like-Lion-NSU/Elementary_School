package thisisus.school.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    TEACHER("ROLE_TEACHER", "선생님"),
    STUDENT("ROLE_STUDENT", "학생");

    private final String key;
    private final String title;
}
