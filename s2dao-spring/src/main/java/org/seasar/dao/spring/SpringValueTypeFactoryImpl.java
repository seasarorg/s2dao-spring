/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.dao.spring;

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
