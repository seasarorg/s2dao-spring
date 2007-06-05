package org.seasar.dao.spring;

import java.io.File;
import java.io.IOException;

import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ResourceUtil;
import org.springframework.beans.factory.FactoryBean;

public class EmbeddedHsqldbUrlFactoryBean implements FactoryBean {

    private String urlSuffix;
    private String referenceClassName;

    public Object getObject() throws Exception {
        return getUrl();
    }

    public Class getObjectType() {
        return String.class;
    }

    public boolean isSingleton() {
        return true;
    }

    private String getUrl() {
        try {
            final File f = getBuildDir();
            final String canonicalPath = f.getCanonicalPath();
            final String url = "jdbc:hsqldb:file:"
                + canonicalPath.replace('\\', '/') + urlSuffix;
            System.out.println(url);
            return url;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File getBuildDir() {
        final Class clazz = ClassUtil.forName(referenceClassName);
        return ResourceUtil.getBuildDir(clazz);
    }

    public void setReferenceClassName(final String referenceClassName) {
        this.referenceClassName = referenceClassName;
    }

    public void setUrlSuffix(final String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }

}
