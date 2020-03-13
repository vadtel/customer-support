package org.vadtel.support.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Topic {
    private Long id;
    private String name;
}
