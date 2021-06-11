//: buckpal.account.application.port.in.SendMoneyUseCase.java

package buckpal.account.application.port.in;


public interface SendMoneyUseCase {

    boolean sendMoney(SendMoneyCommand command);

}///:~