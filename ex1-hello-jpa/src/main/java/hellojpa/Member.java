package hellojpa;

import javax.persistence.*;

@Entity
//@TableGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        table = "MY_SEQUENCES", //table generator 역할을 하는 테이블 이름
//        pkColumnValue = "MEMBER_SEQ", //그 테이블의 pk
//        initialValue = 1, allocationSize = 50) //초기 값 1, 증가값 50. 한번에 시퀀스 값을 가져와서 네트워크를 왔다갔다 하지 않음으로써 성능 향상의 꾀하는 방법
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//        @Column(name = "TEAM_ID")
//        private Long teamId;


    //ManyToOne쪽이 보통 연관관계의 주인이 됨
    //실테이블의 FK가 있는 곳이 주인
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;

        //한번에 team에도 member를 추가할 수 있도록
        //개발자의 실수를 방지하는 편의 메소드
        team.getMemberList().add(this);
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMemberList().add(this);
    }

    //
//        //정해준 시퀀스 전략을 사용
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
//    private Long id;
//
//    @Column(name = "name", nullable = false)
//    private String username;

//    private Integer age;
//
//    //어노테이션 없어도 자동으로 매핑됨
//    //매핑이 싫다면 Transient 사용
//    @Transient
//    private LocalDate testLocalDate1;
//
//    //기본적으로 DB는 enum타입이 없으므로 따로 기재
//    //ordinal이면 enum이 값으로 안나오고 0, 1, ... 처럼 나옴. 기본값이 ordinal이므로 반드시 변경할 것.
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    //큰 컨텐츠
//    //문자 타입이면 clob 타입으로 선언됨
//    @Lob
//    private String description;
//
//    public Member(){
//
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

}
