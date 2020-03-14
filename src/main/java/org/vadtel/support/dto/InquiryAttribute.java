package org.vadtel.support.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class InquiryAttribute {
    private Long id;

    private String name;

    private String value;

}
