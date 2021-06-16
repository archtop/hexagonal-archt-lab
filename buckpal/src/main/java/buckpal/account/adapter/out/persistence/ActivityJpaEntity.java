//: buckpal.account.adapter.out.persistence.ActivityJpaEntity.java

package buckpal.account.adapter.out.persistence;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


/*
 * Contains all activities to a specific account
 */
@Entity
@Table(name = "activity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime timestamp;

    @Column
    private Long ownerAccountId;

    @Column
    private Long sourceAccountId;

    @Column
    private Long targetAccountId;

    @Column
    private Long amount;

}///:~