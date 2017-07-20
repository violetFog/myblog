package com.rainy.repository;

import com.rainy.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by PC on 2017/7/20.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message,Integer>{
}
