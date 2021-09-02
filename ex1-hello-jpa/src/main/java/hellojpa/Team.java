package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")

    private Long id;
    private String name;

    //mappedBy : 어떤 entity의 변수에 매핑되어있는지 기재
    //mappedBy를 적으면 연관관계의 주인이 아님 == 오직 읽기만 가능하다 == 외래키는 Member가 관리해준다
    @OneToMany(mappedBy = "team")
    private List<Member> memberList = new ArrayList<>();

    //Team이나 Member 중에 하나를 잡아서 편의 메소들을 한쪽에 몰아서 셋팅
    public void addMember(Member member){
        member.setTeam(this);
        memberList.add(member);
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

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
