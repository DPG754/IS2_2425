package es.unican.is2.test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.java.ListaOrdenada;

public class ListaOrdenadaTest {

	ListaOrdenada<Integer> sut;

    @BeforeEach
    public void setUp() {
        sut = new ListaOrdenada<>();
    }
    
	@Test
	public void testGet() {
		sut.add(1);
		sut.add(2);
		sut.add(3);
		
		assertTrue(sut.get(0).equals(1), "El get(0) no funciona");
		assertTrue(sut.get(sut.size() - 1).equals(3), "El get(num - 1) no funciona");
		assertTrue(sut.get(1).equals(2), "El get(1) no funciona");
		
        assertThrows(IndexOutOfBoundsException.class, () -> {
            sut.get(sut.size());
        });
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            sut.get(-1);
        });
		
	}
	
	@Test
	public void testAdd() {

		sut.add(1);
		
		assertTrue(1 == sut.size());
		assertTrue(sut.get(0).equals(1), "No exito");
		
		assertThrows(NullPointerException.class, () -> {
            sut.add(null);
        });
        
	}
	
	@Test
	public void testRemove() {

		sut.add(1);
		sut.add(2);
		sut.add(3);
		
		assertTrue(sut.remove(1).equals(2));
		assertTrue(sut.remove(sut.size()-1).equals(3));
		assertTrue(sut.remove(0).equals(1));
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
            sut.get(sut.size());
        });
        
		assertThrows(IndexOutOfBoundsException.class, () -> {
            sut.get(-1);
        });
	}
	
}
