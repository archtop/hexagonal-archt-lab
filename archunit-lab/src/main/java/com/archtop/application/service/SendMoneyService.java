//: com.archtop.application.service.SendMoneyService.java

package com.archtop.application.service;


import com.archtop.application.port.in.SendMoneyCommand;
import com.archtop.application.port.in.SendMoneyUseCase;
import com.archtop.application.port.out.LoadAccountPort;
import com.archtop.domain.Account;
import lombok.AllArgsConstructor;


@AllArgsConstructor
class SendMoneyService implements SendMoneyUseCase {

    private final LoadAccountPort loadAccountPort;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        Account account = new Account();
        return false;
    }

}///:~