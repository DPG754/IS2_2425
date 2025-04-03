package es.unican.is2.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.java.ListaOrdenada;


public class ListaOrdenadaCajaBlanca {
	ListaOrdenada<Integer> sut;

    @BeforeEach
    public void setUp() {
        sut = new ListaOrdenada<>();
    }

    @Test
    public void testGetIndiceValido() {
        sut.add(1);
        sut.add(2);
        sut.add(3);
        assertEquals(1, sut.get(0));
        assertEquals(2, sut.get(1));
        assertEquals(3, sut.get(2));
    }

    @Test
    public void testGetIndiceInvalido() {
        sut.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(1));
    }


    @Test
    public void testAddElementoNulo() {
    	assertThrows(NullPointerException.class, () -> {
            sut.add(null);
        });
    }

    @Test
    public void testAddSinEntrarWhile() {
        sut.add(5); 
        assertEquals(1, sut.size());
        assertEquals(5, sut.get(0));
    }

    @Test
    public void testAddEntrarWhileUnaVez() {
        sut.add(3);
        sut.add(5);
        assertEquals(2, sut.size());
        assertEquals(3, sut.get(0));
        assertEquals(5, sut.get(1));
    }

    @Test
    public void testAddRecorrerTodaLaLista() {
        sut.add(5);
        sut.add(7);
        sut.add(9);
        sut.add(1);
        assertEquals(4, sut.size());
        assertEquals(1, sut.get(0));
    }

    @Test
    public void testRemoveIndiceValido() {
        sut.add(1);
        sut.add(2);
        assertEquals(2, sut.remove(1));
        assertEquals(1, sut.remove(0));
        assertEquals(0, sut.size());
    }

    @Test
    public void testRemoveIndiceInvalido() {
        sut.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(1));
    }


    @Test
    public void testClearListaVacia() {
        sut.clear(); 
        assertEquals(0, sut.size());
    }

    @Test
    public void testClearListaConUnElemento() {
        sut.add(1);
        sut.clear();
        assertEquals(0, sut.size());
    }

    @Test
    public void testClearListaConVariosElementos() {
        sut.add(1);
        sut.add(2);
        sut.add(3);
        sut.clear();
        assertEquals(0, sut.size());
    }
}
