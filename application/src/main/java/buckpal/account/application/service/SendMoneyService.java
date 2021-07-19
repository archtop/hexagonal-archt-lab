//: buckpal.account.application.service.SendMoneyService.java

package buckpal.account.application.service;


import buckpal.account.application.port.in.SendMoneyCommand;
import buckpal.account.application.port.in.SendMoneyUseCase;
import buckpal.account.application.port.out.AccountLock;
import buckpal.account.application.port.out.LoadAccountPort;
import buckpal.account.application.port.out.UpdateAccountStatePort;
import buckpal.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;


@UseCase
@Transactional
@RequiredArgsConstructor
class SendMoneyService implements SendMoneyUseCase {

    private final AccountLock accountLock;

    private final LoadAccountPort loadAccountPort;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {

        // TODO: Validate business rules
        // TODO: Manipulate model state
        // TODO: Return output

        return false;
    }

}///:~