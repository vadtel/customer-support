package org.vadtel.support.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table(name = "attribute_of_inquiry",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "fk_inquiry"}))
public class InquiryAttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_inquiry")
    private InquiryEntity inquiryEntity;


    public InquiryAttributeEntity(String name, String value, InquiryEntity inquiryEntity) {
        this.name = name;
        this.value = value;
        this.inquiryEntity = inquiryEntity;
    }
}
