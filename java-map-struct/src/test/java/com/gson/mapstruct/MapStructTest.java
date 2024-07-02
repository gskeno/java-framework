package com.gson.mapstruct;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapStructTest {
    @Test
    public void test(){
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setName("A");
        doctor.setAge("18");
        doctor.setMoney("17.8");
        doctor.setSalary(new BigDecimal("11.2"));
        doctor.setStartTime("2024-06-30 10:00:00");
        doctor.setEndTime("2024-06-30 23:00:00");
        DoctorDto doctorDto = DoctorMapper.INSTANCE.toDto(doctor);
        System.out.println(doctorDto);
    }

    @Test
    public void test1(){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setStartTime(new Date());
        doctorDto.setEndTime(LocalDateTime.now());
        Doctor doc = DoctorMapper.INSTANCE.toDoc(doctorDto);
        System.out.println(doc);
    }

    @Test
    public void testPerson(){
        Person person = new Person();
        person.setId("1");
        person.setName("gs");

        Person person2 = new Person();
        person2.setId("2");
        person2.setName("hj");
        List<Person> personList = new ArrayList<>();
        personList.add(person2);
        person.setChildren(personList);

        PersonDTO dto = PersonMapper.INSTANCE.toDto(person);
        System.out.println(dto);


    }
}
