/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author eduardo
 */
public class ArrayList<E> implements List<E>,Iterable<E>{
    private E[] array;
    private int capacity;
    private int current;
    
    public ArrayList(){
        capacity = 10;
        array = (E[]) new Object[capacity];
        current = 0;
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private E p = array[0];
            int index=0;
            @Override
            public boolean hasNext() {
                return p!=null;
            }

            @Override
            public E next() {
                E tmp = p;
                index++;
                p =array[index] ;
                return tmp;
            }
        };
        return it;
    }
    
    public ListIterator<E> listiterator(){
        ListIterator<E> lit = new ListIterator<E>(){
            private E p = array[0];
            private int index = 0;
            @Override
            public boolean hasNext() {
                return p!=null;
            }

            @Override
            public E next() {
                E tmp = p;
                index++;
                p = array[index];
                return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return p!=null;
            }

            @Override
            public E previous() {
                E tmp = p;
                index--;
                p = array[index];
                return tmp;
            }

            @Override
            public int nextIndex() {
                return index+1;
            }

            @Override
            public int previousIndex() {
                return index-1;
            }

            @Override
            public void remove() {
                for(int i=index;i<current-1;i++){
                    array[i]=array[i+1];
                }
                current--;
                p=array[index];
            }

            @Override
            public void set(E arg0) {
                array[index]=arg0;
            }

            @Override
            public void add(E arg0) {
                if(current==capacity) addCapacity();
                for(int i = current-1;i>=index;i--){
                    array[i+1]=array[i];
                }
                array[index]=arg0;
                current++;
                //p=array[index];
            }
        };
        return lit;
    }
    
    public ListIterator<E> listiterator(int index)
    {
        ListIterator<E> lit = new ListIterator<E>(){
            private E p = array[index];
            private int i = index;
            @Override
            public boolean hasNext() {
                return p!=null;
            }

            @Override
            public E next() {
                E tmp = p;
                i++;
                p = array[i];
                return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return p!=null;
            }

            @Override
            public E previous() {
                E tmp = p;
                i--;
                p = array[i];
                return tmp;
            }

            @Override
            public int nextIndex() {
                return i+1;
            }

            @Override
            public int previousIndex() {
                return i-1;
            }

            @Override
            public void remove() {
                for(int i=index;i<current-1;i++){
                    array[i]=array[i+1];
                }
                current--;
                p=array[index];
            }

            @Override
            public void set(E arg0) {
                array[i]=arg0;
            }

            @Override
            public void add(E arg0) {
                if(current==capacity) addCapacity();
                for(int n = current-1;n>=index;n--){
                    array[n+1]=array[n];
                }
                array[i]=arg0;
                current++;
                //p=array[i];
            }
        };
        return lit;
    }

    @Override
    public boolean addFirst(E e) {
        if(e == null) return false;
        if(current == capacity) addCapacity();
        for(int i = current-1;i>=0;i--)
            array[i+1] = array[i];
        array[0] = e;
        current++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if(e == null) return false;
        if(current == capacity) addCapacity();
        array[current++] = e;
        return true;
    }
    private void addCapacity()
    {
        E[] tmp = (E[])new Object[(capacity*3)/2];
        for(int i=0;i<current;i++)
            tmp[i] = array[i];//deep copy
        array = tmp;//shallow copy
        capacity = (capacity * 3)/2;
    }
    @Override
    public boolean removeLast() {
        E[] tmp = (E[])new Object[(capacity)];
        if(isEmpty()) return false;
        for(int i=0;i<(current-1);i++){
            tmp[i]=array[i];
        }
        array=tmp;
        current--;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()) return false;
        for(int i=0;i<current-1;i++){
            array[i]=array[i+1];
        }
        current--;
        return true;
    }

    @Override
    public boolean insert(int index, E e) {
        if(index<0 || index>current || e==null) return false;
        if(current==capacity) addCapacity();
        for(int i = current-1;i>=index;i--){
            array[i+1]=array[i];
        }
        array[index]=e;
        current++;
        return true;
    }

    @Override
    public boolean set(int index, E e) {
        if(index<0 || index>current) return false;
        array[index]=e;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return current == 0;
    }

    @Override
    public E get(int index) {
        if(index<0 || index>=current) throw new IllegalArgumentException("valor no valido");
        return array[index];
    }

    @Override
    public boolean contains(E e) {
        for(int i=0; i<current; i++)
        {
            if(array[i].equals(e))
                return true;
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        if(isEmpty() || index>=current || index<0) return false;
        for(int i=index;i<current-1;i++){
            array[i]=array[i+1];
        }
        current--;
        return true;
    }

    @Override
    public E getFirst() {
        if(isEmpty())throw new IllegalArgumentException("no hay contenido");
        return array[0];
    }

    @Override
    public E getLast() {
        if(isEmpty())throw new IllegalArgumentException("no hay contenido");
        return array[current-1];
    }

    @Override
    public int indexOf(E e) {
        if(isEmpty())throw new IllegalArgumentException("no hay contenido");
        for(int i=0;i<current;i++){
            if(e.equals(array[i])) return i;
        }
        throw new IllegalArgumentException("no hay valor");
    }

    @Override
    public int size() {
        return current;
    }
    
    @Override
    public String toString()
    {
        if(isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0;i<current-1;i++)//hasta el penultimo elemento lleva coma
        {
            sb.append(array[i]);
            sb.append(",");
        }    
        sb.append(array[current-1]);//agrego el ultimo elemento sin coma
        sb.append("]");
        return sb.toString();
    }
}
