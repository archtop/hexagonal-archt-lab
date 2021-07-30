//: buckpal.account.adapter.in.web.beans.SimpleBeanImpl.java

package buckpal.account.adapter.in.web.bean;


import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SimpleBeanImpl implements SimpleBean {

    public SimpleBeanImpl() {
        log.info(">>>>>>> [SimpleBeanImpl Instantiation]");
    }

    @Override
    public String toString() {
        return String.format(">>>>>>> SimpleBeanImpl [code: %s]",
                this.hashCode());
    }

}///:~