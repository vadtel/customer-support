package org.vadtel.support.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class InquiryAttribute {
    @EqualsAndHashCode.Exclude
    private Long id;

    private String name;

    private String value;

    public InquiryAttribute(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
}
