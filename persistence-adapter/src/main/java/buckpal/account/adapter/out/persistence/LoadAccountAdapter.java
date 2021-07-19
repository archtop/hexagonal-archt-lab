//: buckpal.account.adapter.out.persistence.AccountPersistenceAdapter.java

package buckpal.account.adapter.out.persistence;


import buckpal.account.application.port.out.LoadAccountPort;
import buckpal.account.domain.Account;
import buckpal.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;


@PersistenceAdapter
@RequiredArgsConstructor
class LoadAccountAdapter implements LoadAccountPort {

    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account loadAccount(Account.AccountId accountId,
                               LocalDateTime baselineDate) {

        AccountJpaEntity account =
                accountRepository.findById(accountId.getValue())
                        .orElseThrow(EntityNotFoundException::new);

        List<ActivityJpaEntity> activities =
                activityRepository.findByOwnerSince(
                        accountId.getValue(),
                        baselineDate);

        Long withdrawalBalance = orZero(activityRepository
                .getWithdrawalBalanceUntil(
                        accountId.getValue(),
                        baselineDate));

        Long depositBalance = orZero(activityRepository
                .getDepositBalanceUntil(
                        accountId.getValue(),
                        baselineDate));

        return accountMapper.mapToDomainEntity(
                account,
                activities,
                withdrawalBalance,
                depositBalance);

    }

    private Long orZero(Long value){
        return value == null ? 0L : value;
    }

}///:~