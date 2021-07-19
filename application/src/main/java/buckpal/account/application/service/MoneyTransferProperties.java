//: buckpal.account.application.service.MoneyTransferProperties.java

package buckpal.account.application.service;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import buckpal.account.domain.Money;


/**
 * Configuration properties for money transfer use cases.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyTransferProperties {

    private Money maximumTransferThreshold = Money.of(1_000_000L);

}///:~