/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.pojo;

import java.io.Serializable;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author QuocEzio
 */
@Entity
@Table(name = "lessons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lessons.findAll", query = "SELECT l FROM Lessons l"),
    @NamedQuery(name = "Lessons.findByLessonId", query = "SELECT l FROM Lessons l WHERE l.lessonId = :lessonId"),
    @NamedQuery(name = "Lessons.findByTitle", query = "SELECT l FROM Lessons l WHERE l.title = :title"),
    @NamedQuery(name = "Lessons.findByOrderNum", query = "SELECT l FROM Lessons l WHERE l.orderNum = :orderNum")})
public class Lessons implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lesson_id")
    private Integer lessonId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Column(name = "order_num")
    private Integer orderNum;
    @OneToMany(mappedBy = "lessonId")
    private Set<Assignments> assignmentsSet;
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    @ManyToOne
    private Courses courseId;

    public Lessons() {
    }

    public Lessons(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Lessons(Integer lessonId, String title) {
        this.lessonId = lessonId;
        this.title = title;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @XmlTransient
    public Set<Assignments> getAssignmentsSet() {
        return assignmentsSet;
    }

    public void setAssignmentsSet(Set<Assignments> assignmentsSet) {
        this.assignmentsSet = assignmentsSet;
    }


    public Courses getCourseId() {
        return courseId;
    }

    public void setCourseId(Courses courseId) {
        this.courseId = courseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lessonId != null ? lessonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lessons)) {
            return false;
        }
        Lessons other = (Lessons) object;
        if ((this.lessonId == null && other.lessonId != null) || (this.lessonId != null && !this.lessonId.equals(other.lessonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntq.pojo.Lessons[ lessonId=" + lessonId + " ]";
    }
    
}
