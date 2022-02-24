package com.tt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AcademyDTO extends CommonDTO {

    private String academyName;

    private String academyLocation;

    private String academyContact;
}
