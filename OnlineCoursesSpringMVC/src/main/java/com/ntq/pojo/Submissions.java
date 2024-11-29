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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author QuocEzio
 */
@Entity
@Table(name = "submissions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Submissions.findAll", query = "SELECT s FROM Submissions s"),
    @NamedQuery(name = "Submissions.findBySubmissionId", query = "SELECT s FROM Submissions s WHERE s.submissionId = :submissionId"),
    @NamedQuery(name = "Submissions.findByAssignmentId", query = "SELECT s FROM Submissions s WHERE s.assignmentId = :assignmentId"),
    @NamedQuery(name = "Submissions.findByUsername", query = "SELECT s FROM Submissions s WHERE s.username = :username"),
    @NamedQuery(name = "Submissions.findBySubmittedAt", query = "SELECT s FROM Submissions s WHERE s.submittedAt = :submittedAt"),
    @NamedQuery(name = "Submissions.findByGrade", query = "SELECT s FROM Submissions s WHERE s.grade = :grade")})
public class Submissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "submission_id")
    private Integer submissionId;
    @Column(name = "assignment_id")
    private Integer assignmentId;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Column(name = "submitted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedAt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "grade")
    private Double grade;
    @Lob
    @Size(max = 65535)
    @Column(name = "feedback")
    private String feedback;

    public Submissions() {
    }

    public Submissions(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (submissionId != null ? submissionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Submissions)) {
            return false;
        }
        Submissions other = (Submissions) object;
        if ((this.submissionId == null && other.submissionId != null) || (this.submissionId != null && !this.submissionId.equals(other.submissionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntq.pojo.Submissions[ submissionId=" + submissionId + " ]";
    }
    
}
