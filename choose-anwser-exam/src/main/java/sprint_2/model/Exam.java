package sprint_2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExam;
    private String examName;
    private String examDuration;
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("exam")
    private Collection<ResultExam> resultExamCollection;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "exam_question", joinColumns = @JoinColumn(name="idExam"), inverseJoinColumns = @JoinColumn(name="idQuestion"))
    @JsonIgnoreProperties("exams")
    private List<Question> questions;

    public Long getIdExam() {
        return idExam;
    }

    public void setIdExam(Long idExam) {
        this.idExam = idExam;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(String examDuration) {
        this.examDuration = examDuration;
    }

    public Collection<ResultExam> getResultExamCollection() {
        return resultExamCollection;
    }

    public void setResultExamCollection(Collection<ResultExam> resultExamCollection) {
        this.resultExamCollection = resultExamCollection;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
