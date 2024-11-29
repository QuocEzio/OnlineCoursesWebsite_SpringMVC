/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author QuocEzio
 */
@Entity
@Table(name = "enrollments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enrollments.findAll", query = "SELECT e FROM Enrollments e"),
    @NamedQuery(name = "Enrollments.findByEnrollmentId", query = "SELECT e FROM Enrollments e WHERE e.enrollmentId = :enrollmentId"),
    @NamedQuery(name = "Enrollments.findByUsername", query = "SELECT e FROM Enrollments e WHERE e.username = :username"),
    @NamedQuery(name = "Enrollments.findByCourseId", query = "SELECT e FROM Enrollments e WHERE e.courseId = :courseId"),
    @NamedQuery(name = "Enrollments.findByEnrollmentDate", query = "SELECT e FROM Enrollments e WHERE e.enrollmentDate = :enrollmentDate"),
    @NamedQuery(name = "Enrollments.findByCompletionDate", query = "SELECT e FROM Enrollments e WHERE e.completionDate = :completionDate")})
public class Enrollments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "enrollment_id")
    private Integer enrollmentId;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "enrollment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enrollmentDate;
    @Column(name = "completion_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completionDate;

    public Enrollments() {
    }

    public Enrollments(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enrollmentId != null ? enrollmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enrollments)) {
            return false;
        }
        Enrollments other = (Enrollments) object;
        if ((this.enrollmentId == null && other.enrollmentId != null) || (this.enrollmentId != null && !this.enrollmentId.equals(other.enrollmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntq.pojo.Enrollments[ enrollmentId=" + enrollmentId + " ]";
    }
    
}
