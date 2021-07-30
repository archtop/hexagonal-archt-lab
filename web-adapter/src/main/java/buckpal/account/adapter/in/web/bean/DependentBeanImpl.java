//: buckpal.account.adapter.in.web.bean.DependentBeanImpl.java

package buckpal.account.adapter.in.web.bean;


import lombok.AllArgsConstructor;


@AllArgsConstructor
public class DependentBeanImpl implements DependentBean {

    private final SimpleBean simpleBean;

    @Override
    public SimpleBean getSimpleBean() {
        return this.simpleBean;
    }

}///:~