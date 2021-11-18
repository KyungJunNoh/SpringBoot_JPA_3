package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작

        try {
            Member member1 = new Member();
            member1.setUsername("관리자1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            em.persist(member2);

            em.flush();
            em.clear();

            String query = "SELECT function('group_concat', m.username) From Member m";

            List<String> resultList = em.createQuery(query, String.class).getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
            }
            /* Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(12);
            member.setTeam(team);
            member.setMemberType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select nullif(m.username, '관리자2') from Member m";
            List<String> resultList = em.createQuery(query, String.class).getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
            }*/

            /* Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member");
            member.setAge(10);
            member.setTeam(team);
            member.setMemberType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m.username,'HELLO',TRUE,m.memberType from Member m where m.memberType = :userType"; // on 절을 이용하여 조건을 걸어줌
            List<Object[]> resultList = em.createQuery(query)
                    .setParameter("userType", MemberType.ADMIN)
                    .getResultList();

            for (Object[] objects : resultList) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[1] = " + objects[1]);
                System.out.println("objects[2] = " + objects[2]);
                System.out.println("objects[3] = " + objects[3]);
            }*/

            /*Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m from Member m left join m.team t on t.name = 'teamA'"; // on 절을 이용하여 조건을 걸어줌
            List<Member> resultList = em.createQuery(query, Member.class)
                    .getResultList();

            System.out.println("resultList.size() = " + resultList.size());*/

            /* for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(i);
                em.persist(member);
            }

            em.flush();
            em.clear();

            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("resultList.size() = " + resultList.size());
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }*/

            /* Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();// 엔티티 프로젝션에서 나온 결과든은 영속성 컨텍스트가 관리를 해준다.

            MemberDTO memberDTO = resultList.get(0);

            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());*/


            /* Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class); // 타입정보를 명확하게 가지고있음.
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class); // 타입정보를 명확하게 가지고 있음.
            Query query3 = em.createQuery("select m.username, m.age from Member m"); // 타입정보를 명확하게 가지고 있지 않음.

            List<Member> resultList = query1.getResultList();

            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }*/

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close(); // em 종료
        }
    }
}
