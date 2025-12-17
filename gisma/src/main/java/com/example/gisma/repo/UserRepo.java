package com.example.gisma.repo;


import com.example.gisma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    User findByMobileNumber(String mobileNumber);

    List<User> findByRole(Integer role);

    List<User> findByOwnerUserId(Long ownerUserId);

    @Query(value = "select * from unit_user uu left join unit u on u.unit_id=uu.unit_id left join user us on us.user_id=uu.user_id where uu.unit_id=?2 and uu.user_id=?1", nativeQuery = true)
    User getusersByUnitIdUserId(Long userId, Long unitId);

    @Query(value="select u from User u where lower(u.userName) like lower(concat('%', :usename, '%'))")
    List<User> getuserid(@Param("usename") String pehi);
}
