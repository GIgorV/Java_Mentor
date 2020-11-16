package hiber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    //Unidirectional @OneToOne primary key association: (Однонаправленный первичный ключ)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Car car;

    public User(String firstName, String lastName, String email, Car car) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.car = car;
    }
}

/*
Unidirectional @OneToMany primary key association:

@Entity
@Table(name="person")
public class person{

@Id
@Column(name="person_id")
@GeneratedValue
private Integer personId;

private String name;

@OneToOne(cascade=CascadeType.ALL)
@PrimaryKeyJoinColumn
private address address;

@OneToMany(cascade=CascadeType.ALL)
@JoinColumn(name = "person_id")
private List<car> cars;

 */