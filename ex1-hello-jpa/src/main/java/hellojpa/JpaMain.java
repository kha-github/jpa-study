package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args){
        //DB당 매니저팩토리는 하나
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //고객의 요청 하나마다 엔티티 매니저 하나 생성
        //-> 쓰레드간에 절대 공유하지 않는다!!
        EntityManager em = emf.createEntityManager(); //정말 쉽게 생각하면 데이터베이스 커넥션 하나를 받는 것

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //트랜잭션 시작

        try{

//            //멤버 추가
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("helloB");
//            em.persist(member); //member를 저장

//            //멤버 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());
//
//            //따로 뭔가를 하지 않아도 setName만으로 update 쿼리를 날림
//            findMember.setName("HelloJPA");

            //JPQL 사용
            //JPQL은 테이블을 대상으로 절대 짜지 않는다! 객체를 기준으로 짤 것
            //JPQL은 객체 지향 쿼리이므로 일단 짜두면 SQL 방언에 맞춰서 알아서 바꿔준다 (oracle, mysql ..)
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result){
                System.out.println("member.name = " + member.getName());
            }

            //할 거 다 하면 트랜잭션 커밋
            tx.commit();
        }catch (Exception e){

            tx.rollback();

        }finally {

            //계속 커넥션을 물고 있기 때문에 할 일 다 하면 반드시 close
            em.close();

        }


        //application이 완전 종료되면 닫음
        emf.close();
    }
}
