package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //persistence라는 애에서 시작한 거( 안 보이면 라이브러리 임포트가 잘 안된거. persistence unit name은 xml에 hello 라고 적음.)
        //<persistence-unit name="hello">(라고 xml에 적혀있다)
        //쿼리라도 한번 날리려면 entitymanagerfactory를 만들면 디비랑 연결되고 오ㅞㄴ만한거 다 됨
        //일단 문제 없는거 확인되면 entitymanagerfactory에서 createentitymanager 라는 애를 꺼내야 됨.
        //엔티티 매니저 꺼내고 실제 동작코드 작성한 다음(디비 관련) 실제 앱 완전히 끝나면 팩토리를 닫아줘야됨(앞으로 entitymanager는 em으로 줄여쓴다.)

        //그리고 디비에 넣을 객체, 테이블을 만든다.
        //member라는 테이블을 만들고 매핑을 해야 jpa가 코드를 동작한다.


        EntityManager em = emf.createEntityManager();
        //Entity ManagerFactory는 로딩시점에 딱 하나만 만들어야 실제 뭔가 디비 저장하거나 트랜잭션 할 떄


        EntityTransaction tx =em.getTransaction();
        //getTransaction()으로 트랜잭션 호출이 가능하다.

        tx.begin();
        //트랜잭션 시작

        try {
            Member member1 =new Member();
            member1.setUsername("A");

            Member member2 =new Member();
            member2.setUsername("B");

            Member member3 =new Member();
            member3.setUsername("C");

            System.out.println("--------------");

            //DB.SEQ =1;
            //DB SEQ =51;
            //처음 호출시 1 , 50개씩 써야됨
            //애플리케이션은 1쓰고 2 쓸텐데
            //DB SEQ=1 |1
            //DB SEQ=51 |2
            //DB SEQ=51 |3
            em.persist(member1);        //1, 최적화 51개로 맞추고
            //em.persist(member2);      //MEMORY호출
            //em.persist(member3);      //MEMORY호출


            System.out.println("member1 =" + member1.getId());
            System.out.println("member2 =" + member2.getId());
            System.out.println("member3 =" + member3.getId());


            System.out.println("--------------");
            tx.commit();
            //트랜잭션 커밋. 꼭 해줘야 반영
        }catch (Exception e) {
            tx.rollback();;
        } finally {
            em.close();
            //자원 다 쓰면 꼭 닫아줘야됨.
        }
        //일단 문제 없는거 확인되면 entitymanagerfactory에서 createentitymanager 라는 애를 꺼내야 됨.

        emf.close();
    }
}
