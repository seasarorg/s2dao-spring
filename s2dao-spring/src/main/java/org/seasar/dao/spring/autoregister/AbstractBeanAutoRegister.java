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
package org.seasar.dao.spring.autoregister;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.seasar.framework.aop.javassist.AspectWeaver;
import org.seasar.framework.container.autoregister.AutoNaming;
import org.seasar.framework.container.autoregister.ClassPattern;
import org.seasar.framework.container.autoregister.DefaultAutoNaming;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.MethodUtil;
import org.seasar.framework.util.ClassTraversal.ClassHandler;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.XmlReaderContext;

public abstract class AbstractBeanAutoRegister extends AbstractAutoRegister
		implements ClassHandler {

	protected static final String CLASS_SUFFIX = ".class";

	private static final Method IS_BRIDGE_METHOD = getIsBridgeMethod();

	private AutoNaming autoNaming = new DefaultAutoNaming();

	private String autowire = "byType";

	private String scope = "singleton";

	private String[] interceptorNames = new String[0];

	public void setAutoNaming(AutoNaming autoNaming) {
		this.autoNaming = autoNaming;
	}

	public void setAutowire(String autowire) {
		this.autowire = autowire;
	}

	public void setInterceptorNames(String[] interceptorNames) {
		this.interceptorNames = interceptorNames;
	}

	public void processClass(final String packageName,
			final String shortClassName) {
		if (isIgnore(packageName, shortClassName)) {
			return;
		}

		for (int i = 0; i < getClassPatternSize(); ++i) {
			final ClassPattern cp = getClassPattern(i);
			if (cp.isAppliedPackageName(packageName)
					&& cp.isAppliedShortClassName(shortClassName)) {
				register(packageName, shortClassName);
			}
		}
	}

	protected void register(final String packageName,
			final String shortClassName) {

		final String className = ClassUtil.concatName(packageName,
				shortClassName);

		Class targetClass = ClassUtil.forName(className);
		Class enhancedClass;

		AspectWeaver weaver = new AspectWeaver(targetClass, null);

		Method[] methods = targetClass.getMethods();
		for (int i = 0; i < methods.length; ++i) {
			Method method = methods[i];
			if (isBridgeMethod(method)) {
				continue;
			}

			List interceptorList = new ArrayList();
			for (int j = 0; j < interceptorNames.length; j++) {
				MethodInterceptor interceptor = (MethodInterceptor) getBeanFactory()
						.getBean(interceptorNames[j]);
				interceptorList.add(interceptor);
			}
			if (!isApplicableAspect(method)) {
				continue;
			}
			if (interceptorList.size() == 0) {
				weaver.setInterceptors(method, new MethodInterceptor[0]);
			} else {
				weaver.setInterceptors(method,
					(MethodInterceptor[]) interceptorList
							.toArray(new MethodInterceptor[interceptorList.size()]));
			}
		}
		enhancedClass = weaver.generateClass();

		BeanDefinitionParserDelegate delegate = new BeanDefinitionParserDelegate(
				new XmlReaderContext(null, null, null, null, null, null));
		int autowireMode = delegate.getAutowireMode(autowire);

		RootBeanDefinition bd = new RootBeanDefinition();
		bd.setBeanClass(enhancedClass);
		bd.setAutowireMode(autowireMode);
		bd.setScope(scope);

		String beanName;
		if (autoNaming != null) {
			beanName = autoNaming.defineName(packageName, shortClassName);
		} else {
			beanName = className;
		}
		getBeanFactory().registerBeanDefinition(beanName, bd);

	}

	private boolean isApplicableAspect(Method method) {
		int mod = method.getModifiers();
		return !Modifier.isFinal(mod) && !Modifier.isStatic(mod);
	}

	private boolean isBridgeMethod(final Method method) {
		if (IS_BRIDGE_METHOD == null) {
			return false;
		}
		return ((Boolean) MethodUtil.invoke(IS_BRIDGE_METHOD, method, null))
				.booleanValue();
	}

	private static Method getIsBridgeMethod() {
		try {
			return Method.class.getMethod("isBridge", null);
		} catch (NoSuchMethodException e) {
			return null;
		}
	}

}