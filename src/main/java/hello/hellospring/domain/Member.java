package hello.hellospring.domain;

import javax.persistence.*;

@Entity // jpa가 관리하는 entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // DB가 알아서 생성해주는 것을 'Identity'라 함
    private Long id;

//    @Column(name = "username") // DB에 있는 column은 username으로 mapping됨
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
