//: buckpal.account.adapter.out.persistence.SpringDataAccountRepository.java

package buckpal.account.adapter.out.persistence;


import org.springframework.data.jpa.repository.JpaRepository;


public interface SpringDataAccountRepository extends JpaRepository<AccountJpaEntity, Long> {

}///:~