//: buckpal.account.application.port.out.LoadAccountPort.java

package buckpal.account.application.port.out;


import buckpal.account.domain.Account;

import java.time.LocalDateTime;


public interface LoadAccountPort {

    Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate);

}///:~