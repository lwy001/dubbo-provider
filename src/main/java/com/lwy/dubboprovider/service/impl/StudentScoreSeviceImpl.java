package com.lwy.dubboprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lwy.dubboprovider.dao.StudentScoreRepository;
import com.lwy.pojo.Student;
import com.lwy.service.StudentScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@Service
//@Component
@org.springframework.stereotype.Service
public class StudentScoreSeviceImpl implements StudentScoreService {

    Logger logger = LoggerFactory.getLogger(StudentScoreSeviceImpl.class);
    @Override
    public String findByname(String name) {
        return "name:"+name;
    }

    @Autowired
    StudentScoreRepository ssr;

    @Autowired
    MongoTemplate mt;

    public void insert(Student student){
//        Student student = new Student();
//        student.setName("勇");
//        student.setAge(29);
//        student.setSix((char) 1);

        mt.insert(student);
//        ssr.save(student);
    }



    public List<Student> findAll(String key,String value){
        logger.info("进入 findAll 方法  info");
        logger.debug("进入 findAll 方法  debug");
        logger.error("进入 findAll 方法  error");
        logger.warn("进入 findAll 方法  warn");

//        List<Student> list = ssr.findAll();
//        return list;
        Query query = null;
        if(null != key && !key.isEmpty() && null != value && !value.isEmpty()){
            query = Query.query(Criteria.where(key).is(value));
            List<Student> students = mt.find(query, Student.class);
            return students;
        }else{
            List<Student> students = mt.findAll(Student.class);
            return students;
        }
    }



    public void delete(String key,String value){
//        ssr.deleteById(id);
        Query query = Query.query(Criteria.where(key).is(value));
        mt.remove(query,Student.class);
    }


    public void update(String key,String value){
//        Optional<Student> optional = ssr.findById(id);
//        if (optional.isPresent()){
//            Student student = optional.get();
//            student.setName("李卫勇");
//            ssr.save(student);
//        }
        Query query = Query.query(Criteria.where(key).is(value));
        Update update = new Update();
        update.set(key,value);
        mt.updateFirst(query,update,"stucent");
    }
}
