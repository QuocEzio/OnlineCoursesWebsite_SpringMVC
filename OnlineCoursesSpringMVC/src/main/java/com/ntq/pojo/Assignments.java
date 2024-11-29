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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author QuocEzio
 */
@Entity
@Table(name = "assignments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assignments.findAll", query = "SELECT a FROM Assignments a"),
    @NamedQuery(name = "Assignments.findByAssignmentId", query = "SELECT a FROM Assignments a WHERE a.assignmentId = :assignmentId"),
    @NamedQuery(name = "Assignments.findByTitle", query = "SELECT a FROM Assignments a WHERE a.title = :title"),
    @NamedQuery(name = "Assignments.findByDueDate", query = "SELECT a FROM Assignments a WHERE a.dueDate = :dueDate")})
public class Assignments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "assignment_id")
    private Integer assignmentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id")
    @ManyToOne
    private Lessons lessonId;

    public Assignments() {
    }

    public Assignments(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Assignments(Integer assignmentId, String title) {
        this.assignmentId = assignmentId;
        this.title = title;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Lessons getLessonId() {
        return lessonId;
    }

    public void setLessonId(Lessons lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assignmentId != null ? assignmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assignments)) {
            return false;
        }
        Assignments other = (Assignments) object;
        if ((this.assignmentId == null && other.assignmentId != null) || (this.assignmentId != null && !this.assignmentId.equals(other.assignmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntq.pojo.Assignments[ assignmentId=" + assignmentId + " ]";
    }
    
}
