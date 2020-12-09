package sprint_2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotEmpty;
import javax.persistence.*;
import java.util.Collection;

@Entity
public class User {
    public interface checkCreate {
    }

    public interface checkEdit {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @NotEmpty(message = "Vui lòng nhập tên đăng nhập", groups = checkCreate.class)
    private String username;
    @NotEmpty(message = "Vui lòng nhập mật khẩu", groups = checkCreate.class)

    private String password;
    @NotEmpty(message = "Vui lòng nhập họ và tên", groups = checkEdit.class)
    private String fullName;
    @NotEmpty(message = "Vui lòng nhập email", groups = checkEdit.class)
    private String email;
    @NotEmpty(message = "Vui lòng nhập dịa chỉ", groups = checkEdit.class)
    private String address;
    @NotEmpty(message = "Vui lòng nhập số điện thoại", groups = checkEdit.class)
    private String phoneNumber;
    private String image;

    @ManyToOne
    @JoinColumn(name = "idRole")
    @JsonIgnoreProperties("userCollection")
    private Role role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")


    private Collection<ResultExam> resultExamCollection;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<ResultExam> getResultExamCollection() {
        return resultExamCollection;
    }

    public void setResultExamCollection(Collection<ResultExam> resultExamCollection) {
        this.resultExamCollection = resultExamCollection;
    }

}