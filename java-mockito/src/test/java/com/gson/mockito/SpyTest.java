package com.gson.mockito;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#13
 */
public class SpyTest {

    @Test
    public void testSpy1(){
        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");

        BookStore bookStore = new BookStore();
        BookStore spyBookStore = spy(bookStore);
        // addBook方法会发生真正调用
        when(spyBookStore.addBook(new Book())).thenReturn(null);
        System.out.println("--------");
        doReturn(new Book()).when(spyBookStore).addBook(new Book());
    }

    @Test
    public void testSpy2(){
        List list = new LinkedList();
        List spy = spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        when(spy.get(0)).thenReturn("foo");

        //You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);
    }
}
