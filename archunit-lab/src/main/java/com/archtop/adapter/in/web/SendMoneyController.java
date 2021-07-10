//: com.archtop.adapter.in.web.SendMoneyController.java

package com.archtop.adapter.in.web;


import com.archtop.application.port.in.SendMoneyCommand;
import com.archtop.application.port.in.SendMoneyUseCase;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class SendMoneyController {

    private final SendMoneyUseCase sendMoneyUseCase;

    void sendMoney(Long amount) {
        SendMoneyCommand command = new SendMoneyCommand();
        sendMoneyUseCase.sendMoney(command);
    }

}///:~