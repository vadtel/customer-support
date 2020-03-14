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

}
