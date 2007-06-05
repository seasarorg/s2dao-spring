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
import java.sql.Timestamp;
import java.util.Date;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String TABLE = "EMP";

    public static final int department_RELNO = 0;

    public static final String timestamp_COLUMN = "tstamp";

    private long empno;

    private String ename;

    private String job;

    private Short mgr;

    private java.util.Date hiredate;

    private Float sal;

    private Float comm;

    private int deptno;

    private Timestamp timestamp;

    private Department department;

    public Employee() {
    }

    public Employee(final long empno) {
        this.empno = empno;
    }

    public long getEmpno() {
        return empno;
    }

    public void setEmpno(final long empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(final String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(final String job) {
        this.job = job;
    }

    public Short getMgr() {
        return mgr;
    }

    public void setMgr(final Short mgr) {
        this.mgr = mgr;
    }

    public java.util.Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(final Date hiredate) {
        this.hiredate = hiredate;
    }

    public Float getSal() {
        return sal;
    }

    public void setSal(final Float sal) {
        this.sal = sal;
    }

    public Float getComm() {
        return comm;
    }

    public void setComm(final Float comm) {
        this.comm = comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(final int deptno) {
        this.deptno = deptno;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(final Department department) {
        this.department = department;
    }

    public boolean equals(final Object other) {
        if (!(other instanceof Employee)) {
            return false;
        }
        final Employee castOther = (Employee) other;
        return getEmpno() == castOther.getEmpno();
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append(empno).append(", ");
        buf.append(ename).append(", ");
        buf.append(job).append(", ");
        buf.append(mgr).append(", ");
        buf.append(hiredate).append(", ");
        buf.append(sal).append(", ");
        buf.append(comm).append(", ");
        buf.append(deptno).append(", ");
        buf.append(timestamp).append(" {");
        buf.append(department).append("}");
        return buf.toString();
    }

    public int hashCode() {
        return (int) getEmpno();
    }
}