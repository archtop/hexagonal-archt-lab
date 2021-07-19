//: buckpal.account.adapter.out.persistence.FakeAccountLock.java

package buckpal.account.adapter.out.persistence;


import buckpal.account.application.port.out.AccountLock;
import buckpal.account.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class FakeAccountLock implements AccountLock {

    @Override
    public void lockAccount(Account.AccountId accountId) {
        log.info(">>>>>>> Locking Account {}", accountId);
    }

    @Override
    public void releaseAccount(Account.AccountId accountId) {
        log.info(">>>>>>> Releasing the Lock of Account {}", accountId);
    }

}///:~