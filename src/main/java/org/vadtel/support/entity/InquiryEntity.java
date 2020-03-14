package org.vadtel.support.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table(name = "inquiry")
public class InquiryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "customer_name")
    private String customerName;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_topic")
    private TopicEntity topicEntity;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "inquiryEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InquiryAttributeEntity> attributeEntityList;


}
