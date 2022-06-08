package com.springboot.blog.demo.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author    : VienNN
 * @version    : 1.0
 * 12/1/2021
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "deleted_flag")
    protected Integer deletedFlag = 1;

    @Column(name = "created_at")
    @CreatedDate
    protected Date createdAt = new Date();


    @Column(name = "modified_at")
    @LastModifiedDate
    protected Date modifiedAt = new Date();

    @Column(name = "status")
    protected Integer status = 0;
}