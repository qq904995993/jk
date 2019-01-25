package jk.model.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "finance_log")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FinanceLog {
    @Id
    @Column(name = "finance_log_id")
    private Long financeLogId;

    @Column(name = "user_id")
    private Long userId;

    private String type;

    private String content;

    private BigDecimal balance;

    @Column(name = "create_time")
    private Date createTime;
}