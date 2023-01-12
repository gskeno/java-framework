package com.gson.mapstruct;
import org.junit.Test;

public class MapStructTest {
    @Test
    public void test(){
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setName("A");
        DoctorDto doctorDto = DoctorMapper.INSTANCE.toDto(doctor);
        System.out.println(doctorDto);
    }
}
