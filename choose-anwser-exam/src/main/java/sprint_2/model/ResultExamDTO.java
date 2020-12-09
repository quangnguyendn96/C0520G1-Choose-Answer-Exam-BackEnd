package sprint_2.model;

public class ResultExamDTO {
    private String takenDate;
    private String takenDuration;
    private String mark;
    private Long userId;
    private Long examId;

    public ResultExamDTO() {
    }

    public ResultExamDTO(String takenDate, String takenDuration, String mark, Long userId, Long examId) {
        this.takenDate = takenDate;
        this.takenDuration = takenDuration;
        this.mark = mark;
        this.userId = userId;
        this.examId = examId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    @Override
    public String toString() {
        return "ResultExamDTO{" +
                "takenDate='" + takenDate + '\'' +
                ", takenDuration='" + takenDuration + '\'' +
                ", mark='" + mark + '\'' +
                ", userId=" + userId +
                ", examId=" + examId +
                '}';
    }
}
