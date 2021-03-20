package tech.xiying.template.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author shanghao5
 */
@Entity
@Table(name="t_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Column(name = "u_name" , nullable = false)
    private String name;
    @Column(name = "u_phone" , nullable = false)
    private String phone;
    @ManyToOne(cascade= CascadeType.ALL,fetch = FetchType.EAGER )
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
