package hiber.model;

import lombok.*;

import javax.persistence.*;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String model;
    @Column(length = 20)
    private int series;

//    @OneToOne(mappedBy = "owner")
////    @JoinColumn(name = "owner_id")
//    private User owner;

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }
}
/*
Определяет однозначную связь с другой сущностью, имеющую однозначную множественность.
Обычно нет необходимости явно указывать связанный целевой объект, поскольку он обычно может быть выведен из типа объекта,
на который имеется ссылка. Если отношение является двунаправленным, не владеющая сторона должна использовать элемент
mappedBy аннотации OneToOne, чтобы указать поле отношения или свойство стороны-владельца.
Аннотация OneToOne может использоваться внутри встраиваемого класса, чтобы указать связь между встраиваемым классом
и классом сущности. Если отношение является двунаправленным и сущность, содержащая встраиваемый класс,
находится на стороне-владельце отношения, сторона, не являющаяся владельцем, должна использовать элемент mappedBy
аннотации OneToOne, чтобы указать поле отношения или свойство встраиваемого класса.
Синтаксис записи с точкой (".") Должен использоваться в элементе mappedBy для обозначения атрибута взаимосвязи
во встроенном атрибуте. Значение каждого идентификатора, используемого с точечной нотацией,
является именем соответствующего встроенного поля или свойства.
1. Однозначная ассоциация, которая отображает столбец внешнего ключа:
// On Customer class:

    @OneToOne(optional=false)
    @JoinColumn(
        name="CUSTREC_ID", unique=true, nullable=false, updatable=false)
    public CustomerRecord getCustomerRecord() { return customerRecord; }

    // On CustomerRecord class:

    @OneToOne(optional=false, mappedBy="customerRecord")
    public Customer getCustomer() { return customer; }

2. Однозначная связь, предполагающая, что и источник, и цель имеют одинаковые значения первичного ключа:
 // On Employee class:

    @Entity
    public class Employee {
        @Id Integer id;

        @OneToOne @MapsId
        EmployeeInfo info;
        ...
    }

    // On EmployeeInfo class:

    @Entity
    public class EmployeeInfo {
        @Id Integer id;
        ...
    }

3. Однозначная ассоциация из встраиваемого класса с другой сущностью:
@Entity
    public class Employee {
       @Id int id;
       @Embedded LocationDetails location;
       ...
    }

    @Embeddable
    public class LocationDetails {
       int officeNumber;
       @OneToOne ParkingSpot parkingSpot;
       ...
    }

    @Entity
    public class ParkingSpot {
       @Id int id;
       String garage;
       @OneToOne(mappedBy="location.parkingSpot") Employee assignedTo;
        ...
    }
 */
