package org.vadtel.support.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Inquiry {

    private Long id;

    private String topicName;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd hh-mm-ss")
    private Date createDate;

    private String customerName;

    private List<InquiryAttribute> attributeEntityList;
}
