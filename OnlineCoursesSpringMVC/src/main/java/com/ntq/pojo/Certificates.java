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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "certificates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certificates.findAll", query = "SELECT c FROM Certificates c"),
    @NamedQuery(name = "Certificates.findByCertificateId", query = "SELECT c FROM Certificates c WHERE c.certificateId = :certificateId"),
    @NamedQuery(name = "Certificates.findByIssueDate", query = "SELECT c FROM Certificates c WHERE c.issueDate = :issueDate"),
    @NamedQuery(name = "Certificates.findByCertificateUrl", query = "SELECT c FROM Certificates c WHERE c.certificateUrl = :certificateUrl")})
public class Certificates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "certificate_id")
    private Integer certificateId;
    @Column(name = "issue_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;
    @Size(max = 255)
    @Column(name = "certificate_url")
    private String certificateUrl;
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    @ManyToOne
    private Courses courseId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users userId;

    public Certificates() {
    }

    public Certificates(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }

    public Courses getCourseId() {
        return courseId;
    }

    public void setCourseId(Courses courseId) {
        this.courseId = courseId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (certificateId != null ? certificateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certificates)) {
            return false;
        }
        Certificates other = (Certificates) object;
        if ((this.certificateId == null && other.certificateId != null) || (this.certificateId != null && !this.certificateId.equals(other.certificateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntq.pojo.Certificates[ certificateId=" + certificateId + " ]";
    }
    
}
