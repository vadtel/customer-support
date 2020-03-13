package org.vadtel.support.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@Table(name = "attribute_of_inquiry")
public class InquiryAttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value", nullable = false)
    private String value;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_inquiry", nullable = false)
    private InquiryEntity inquiryEntity;
}
