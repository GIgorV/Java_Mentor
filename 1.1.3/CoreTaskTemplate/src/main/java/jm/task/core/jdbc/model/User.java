package jm.task.core.jdbc.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor

@Table
public class User {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Integer age;

}