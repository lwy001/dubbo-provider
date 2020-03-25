package com.lwy.dubboprovider.dao;

import com.lwy.pojo.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentScoreRepository extends MongoRepository<Student,String> {

}
