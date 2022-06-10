package com.springboot.blog.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.blog.demo.payload.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();


}