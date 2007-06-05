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

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String TABLE = "DEPT";

    private int deptno;

    private String dname;

    private String loc;

    private int versionNo;

    public Department() {
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(final int deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(final String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(final String loc) {
        this.loc = loc;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(final int versionNo) {
        this.versionNo = versionNo;
    }

    public boolean equals(final Object other) {
        if (!(other instanceof Department)) {
            return false;
        }
        final Department castOther = (Department) other;
        return getDeptno() == castOther.getDeptno();
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append(deptno).append(", ");
        buf.append(dname).append(", ");
        buf.append(loc).append(", ");
        buf.append(versionNo);
        return buf.toString();
    }

    public int hashCode() {
        return getDeptno();
    }
}
