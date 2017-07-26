package com.rainy.repository;



import com.rainy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



/**
 * Created by PC on 2017/7/26.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    @Query("from User u where u.userName=?1 and u.password=?2")
    User queryByUserNameAndPassword(String userName,String password);
}
