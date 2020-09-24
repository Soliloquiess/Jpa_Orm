package hellojpa;

import javax.persistence.*;

@Entity //클래스와 동일한 이름 기본값 사용 일반적으로 굳이 이렇게 안 써도 됨.
@SequenceGenerator(    //
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 50)
        //디비에 50개 씩 올려두고 메모리 상에 디비는 51번으로 세팅해둠
        //그리고 넥스트 호출하면 100번으로 바로 감.
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

   @Column(name = "name" ,nullable = false) //데이터베이스 컬럼명은 name으로 이해
    private String username;

    public Member() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}