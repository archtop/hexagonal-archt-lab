//: buckpal.account.adapter.out.persistence.UpdateAccountStateAdapter.java

package buckpal.account.adapter.out.persistence;


import buckpal.account.application.port.out.UpdateAccountStatePort;
import buckpal.account.domain.Account;
import buckpal.account.domain.Activity;
import buckpal.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;


@PersistenceAdapter
@RequiredArgsConstructor
public class UpdateAccountStateAdapter implements UpdateAccountStatePort {

    private final AccountMapper accountMapper;
    private final ActivityRepository activityRepository;

    @Override
    public void updateActivities(Account account) {
        for (Activity activity : account.getActivityWindow().getActivities()) {
            if (activity.getId() == null) {
                activityRepository.save(accountMapper.mapToJpaEntity(activity));
            }
        }
    }

}///:~