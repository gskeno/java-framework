package com.gson.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DoReturnWhen {

    @Spy
    private Repository repository;

    @Mock
    private Repository mockRepository;

    @Test
    public void testWhenThenReturn(){
        // 方法被真实调用
        Mockito.when(repository.getID()).thenReturn("1");
        String id = repository.getID();
        System.out.println(id);
    }

    @Test
    public void test(){
        Mockito.when(mockRepository.getPrefixStudent(Mockito.anyString())).thenReturn("1");
        Mockito.when(mockRepository.getPrefixStudent(null)).thenReturn("2");
        String prefixStudent1 = mockRepository.getPrefixStudent(null);
        String prefixStudent2 = mockRepository.getPrefixStudent("1");
        System.out.println(prefixStudent1);
        System.out.println(prefixStudent2);
    }
}
