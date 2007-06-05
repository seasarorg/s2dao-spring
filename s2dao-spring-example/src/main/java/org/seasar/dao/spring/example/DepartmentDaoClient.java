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
package org.seasar.dao.spring.example;

import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

public class DepartmentDaoClient {

    public static void main(String[] args) {
        BeanFactoryLocator locator = ContextSingletonBeanFactoryLocator.getInstance();
        BeanFactoryReference ref = locator.useBeanFactory("context");
        ApplicationContext context = (ApplicationContext) ref.getFactory();

        
        try {
            DepartmentDao dao = (DepartmentDao) context
                    .getBean("departmentDao");
            Department dept = new Department();
            dept.setDeptno(99);
            dept.setDname("foo");
            dao.insert(dept);
            dept.setDname("bar");
            System.out
                    .println("before update versionNo:" + dept.getVersionNo());
            dao.update(dept);
            System.out.println("after update versionNo:" + dept.getVersionNo());
            dao.delete(dept);
        } finally {
            ref.release();
        }

    }
}