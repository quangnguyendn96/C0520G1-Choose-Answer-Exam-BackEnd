package sprint_2.dto;

public class ExamHistoryDTO {

    private String subject;

    private String examName;

    private String mark;

    private String takenDate;

    public ExamHistoryDTO() {
    }

    public ExamHistoryDTO(String subject, String examName, String mark, String takenDate) {
        this.subject = subject;
        this.examName = examName;
        this.mark = mark;
        this.takenDate = takenDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTakenDate() {
        return takenDate;
    }

    public void setTakenDate(String takenDate) {
        this.takenDate = takenDate;
    }
}
