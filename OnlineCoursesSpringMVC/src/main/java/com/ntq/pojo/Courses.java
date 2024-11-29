/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author QuocEzio
 */
@Entity
@Table(name = "courses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c"),
    @NamedQuery(name = "Courses.findByCourseId", query = "SELECT c FROM Courses c WHERE c.courseId = :courseId"),
    @NamedQuery(name = "Courses.findByTitle", query = "SELECT c FROM Courses c WHERE c.title = :title"),
    @NamedQuery(name = "Courses.findByLogo", query = "SELECT c FROM Courses c WHERE c.logo = :logo"),
    @NamedQuery(name = "Courses.findByPrice", query = "SELECT c FROM Courses c WHERE c.price = :price"),
    @NamedQuery(name = "Courses.findByCreatedAt", query = "SELECT c FROM Courses c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "Courses.findByUpdatedAt", query = "SELECT c FROM Courses c WHERE c.updatedAt = :updatedAt")})
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "course_id")
    private Integer courseId;
    @Basic(optional = false)
    @Size(min=1,max=255,message = "Course names must be between 1 and 255 characters")
    
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535,message = "The course description must not be blank")
    
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "logo")
    private String logo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    @NotNull(message = "Course price from 200.000VND to 700.000VND")
    @Min(value = 200000, message = "Course price from 200.000VND to 700.000VND")
    @Max(value = 700000,message = "Course price from 200.000VND to 700.000VND")
    private Long price;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "instructor_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users instructorId;
    @OneToMany(mappedBy = "courseId",cascade = CascadeType.ALL)
    private Set<Certificates> certificatesSet;
    @OneToMany(mappedBy = "courseId",cascade = CascadeType.ALL)
    private Set<Reviews> reviewsSet;
    @OneToMany(mappedBy = "courseId",cascade = CascadeType.ALL)
    private Set<Lessons> lessonsSet;

    @Transient
    private MultipartFile file;
    
    public Courses() {
    }

    public Courses(Integer courseId) {
        this.courseId = courseId;
    }

    public Courses(Integer courseId, String title) {
        this.courseId = courseId;
        this.title = title;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Users getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Users instructorId) {
        this.instructorId = instructorId;
    }

    @XmlTransient
    public Set<Certificates> getCertificatesSet() {
        return certificatesSet;
    }

    public void setCertificatesSet(Set<Certificates> certificatesSet) {
        this.certificatesSet = certificatesSet;
    }

    @XmlTransient
    public Set<Reviews> getReviewsSet() {
        return reviewsSet;
    }

    public void setReviewsSet(Set<Reviews> reviewsSet) {
        this.reviewsSet = reviewsSet;
    }

    @XmlTransient
    public Set<Lessons> getLessonsSet() {
        return lessonsSet;
    }

    public void setLessonsSet(Set<Lessons> lessonsSet) {
        this.lessonsSet = lessonsSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntq.pojo.Courses[ courseId=" + courseId + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
