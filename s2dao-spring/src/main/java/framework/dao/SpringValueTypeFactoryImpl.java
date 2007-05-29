package framework.dao;

import org.seasar.dao.ValueTypeFactory;
import org.seasar.extension.jdbc.ValueType;
import org.seasar.extension.jdbc.types.ValueTypes;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class SpringValueTypeFactoryImpl implements ValueTypeFactory, BeanFactoryAware {

    private BeanFactory beanFactory;

    public ValueType getValueTypeByName(String name) {
        return (ValueType) beanFactory.getBean(name);
    }

    public ValueType getValueTypeByClass(Class clazz) {
        return ValueTypes.getValueType(clazz);
    }

    public void setBeanFactory (BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

}
