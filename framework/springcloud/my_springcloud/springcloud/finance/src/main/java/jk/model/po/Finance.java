package jk.model.po;

import java.math.BigDecimal;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "finance")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Finance {
    @Id
    @Column(name = "user_id")
    private Long userId;

    private BigDecimal balance;
}