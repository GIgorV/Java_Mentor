package web.models;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private int id;
    private String model;
    private String number;
}
