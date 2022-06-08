package com.storm.dataalgorithmdemo;


import androidx.annotation.VisibleForTesting;

import com.storm.dataalgorithmdemo.annimation.BindView;
import com.storm.dataalgorithmdemo.annimation.BindViewTest;
import com.storm.dataalgorithmdemo.annimation.Student;
import com.storm.dataalgorithmdemo.annimation.TestInheritedAnnotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

public class TestAnnotation {

    @Test
    public void  bindViewTest(){

        try{
         Field[] fields =  BindViewTest.class.getClassLoader()
                    .loadClass("com.storm.dataalgorithmdemo.annimation.BindViewTest")
                    .getDeclaredFields();

            // 获取
            for (Field field : fields) {
                System.out.println("获取的属性 " + field.toString());
                if (field.isAnnotationPresent(BindView.class)) {
                    //获取 field 上的注解对象信息
                    BindView annotation = field.getDeclaredAnnotation(BindView.class);
                    int value = annotation.value();
                    System.out.println("获取注解的值 --> " + value);

                }

            }

        } catch (Exception e){

        }finally {

        }

    }

    @Test
    public void test1() {
        Class<Student> aClass = Student.class;
        boolean has = aClass.isAnnotationPresent(TestInheritedAnnotation.class);
        System.out.println("是否存在--> " + has);
        Annotation[] annotations =
                aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());

        }

    }
}
