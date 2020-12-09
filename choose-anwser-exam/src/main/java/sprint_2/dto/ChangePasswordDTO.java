package sprint_2.dto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ChangePasswordDTO {
    private long id;

    @NotEmpty(message = "Vui lòng nhập mật khẩu")
    private String oldPassword;

    @NotEmpty(message = "Vui lòng nhập mật khẩu")
    @Pattern(regexp = "^[a-z0-9]{6,30}$", message = "Mật khẩu không hợp lệ")
    private String newPassword;

    private String message;

    public ChangePasswordDTO(long id, String oldPassword, String newPassword) {
        this.id = id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ChangePasswordDTO(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}