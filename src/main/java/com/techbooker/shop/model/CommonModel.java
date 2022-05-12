package com.techbooker.shop.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class CommonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_user", nullable = false, updatable = false)
    private String createdUser;

    @Column(name = "last_updated_user", nullable = false)
    private String lastUpdatedUser;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date_time", nullable = false, updatable = false)
    private Date createdDateTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date_time", nullable = false)
    private Date updatedDateTime;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
