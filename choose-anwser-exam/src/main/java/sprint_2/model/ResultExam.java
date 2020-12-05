package sprint_2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class ResultExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResultExam;
    private String takenDate;
    private String takenDuration;
    private String mark;
    @ManyToOne
    @JoinColumn(name = "user")
    @JsonIgnoreProperties("resultExamCollection")
    private User user;
    @ManyToOne
    @JoinColumn(name = "exam")
    @JsonIgnoreProperties("resultExamCollection")
    private Exam exam;

    public Long getIdResultExam() {
        return idResultExam;
    }

    public void setIdResultExam(Long idResultExam) {
        this.idResultExam = idResultExam;
    }

    public String getTakenDate() {
        return takenDate;
    }

    public void setTakenDate(String takenDate) {
        this.takenDate = takenDate;
    }

    public String getTakenDuration() {
        return takenDuration;
    }

    public void setTakenDuration(String takenDuration) {
        this.takenDuration = takenDuration;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
