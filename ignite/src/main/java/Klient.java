import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Klient implements Serializable {
    private String imie;
    private String nazwisko;
    private String pesel;
}
