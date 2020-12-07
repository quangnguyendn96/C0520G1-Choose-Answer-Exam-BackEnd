package sprint_2.model;


public class TopDTO {
    private String userName;
    private String sumPoint;
    private String countExam;

    public TopDTO() {
    }

    public TopDTO(String userName, String sumPoint, String countExam) {
        this.userName = userName;
        this.sumPoint = sumPoint;
        this.countExam = countExam;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSumPoint() {
        return sumPoint;
    }

    public void setSumPoint(String sumPoint) {
        this.sumPoint = sumPoint;
    }

    public String getCountExam() {
        return countExam;
    }

    public void setCountExam(String countExam) {
        this.countExam = countExam;
    }
}
