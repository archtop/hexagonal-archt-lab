//: com.archtop.application.port.in.SendMoneyUseCase.java

package com.archtop.application.port.in;


public interface SendMoneyUseCase {

    boolean sendMoney(SendMoneyCommand command);

}///:~