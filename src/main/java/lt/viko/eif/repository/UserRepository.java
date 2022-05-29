package lt.viko.eif.repository;

import lt.viko.eif.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getByUsername(String username);

    boolean existsUserByUsername(String username);

    @Override
    <S extends User> S save(S entity);

    void deleteByUsername(String username);
}
