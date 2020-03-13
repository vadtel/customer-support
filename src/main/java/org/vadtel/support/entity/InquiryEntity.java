package org.vadtel.support.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "inquiry")
public class InquiryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(optional=false, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_topic", nullable=false)
    private TopicEntity topicEntity;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "inquiryEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InquiryAttributeEntity> attributeEntityList;


}
