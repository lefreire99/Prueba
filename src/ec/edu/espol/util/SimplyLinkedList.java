/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import java.util.Iterator;

/**
 *
 * @author lfrei
 */
public class SimplyLinkedList<E> implements List<E>,Iterable<E> {
    
    private Node<E> first;
    private Node<E> last;
    private int current;
    
    public SimplyLinkedList()
    {
        first=last=null;
        current = 0;
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private Node<E> p = first;
            @Override
            public boolean hasNext() {
                return p!=null;
            }

            @Override
            public E next() {
                E tmp = p.data;
                p = p.next;
                return tmp;
            }
        };
        return it;
    }
    //Ejercicio 1
    public Iterator<E> iteratorStep(int start, int step){
        Iterator<E> it = new Iterator<E>() {          
            private Node<E> p=searchNode(start);
            int salto=step;
            @Override
            public boolean hasNext() {
                return p!=null;
            }
            @Override
            public E next() {
                E tmp=p.data;
                p=searchNode(salto);
                salto=salto+step;
                return tmp;
            }
        };
        return it;
    }
    
    private Node<E>searchNode(int index){
        if(index>current||index<0) return null;
        else if(isEmpty()) return null;
        int i=0;
        Node<E> q=first;
        while(i!=index){
            q = q.next;
            i++;
        }
        return q;
    }
    
    private class Node<E>
    {
        private E data;
        private Node<E> next;
        
        public Node(E data)
        {
            this.data = data;
            this.next = null;
        }
       
    }

    @Override
    public boolean addFirst(E e) {
        if(e == null) return false;
        Node<E> n = new Node<>(e);
        if(isEmpty())
            first = last = n;
        else{
            n.next = first;
            first = n;
        }
        current++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if(e == null) return false;
        Node<E> n = new Node<>(e);
        if(isEmpty())
            first = last = n;
        else{
            last.next = n;
            last = n;
        }
        current++;
        return true;
    }

    @Override
    public E getFirst() {
        if(isEmpty())return null;
        return first.data;
    }

    @Override
    public E getLast() {
        if(isEmpty())return null;
        return last.data;
    }

    @Override
    public int indexOf(E e) {
        if(isEmpty())return -1;
        int i=0;
        Node<E> q=first;
        while(q!=null && !q.data.equals(e)){
            q = q.next;
            i++;
        }
        if(i==current) return -1;
        return i;
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()) return false;
        else if(first == last){
            last.data=null; // help GC
            first = last = null;
        }
        else
        {
            Node<E> prev = getPrevious(last);
            last.data = null; // help GC
            prev.next = null;
            last = prev;
        }
        current --;
        return true;
    }
    
    private Node<E> getPrevious(Node<E> p)
    {
        if(p == first) return null;
        Node<E> q = first;
        while(q!=null && q.next!=p)
            q = q.next;
        return q;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()) return false;
        if(first == last)
        {
            first.data = null;//help GC
            first = last = null;
            
        }
        else
        {
            Node<E> p = first;
            first = first.next;
            p.data = null;//help GC
            p.next = null;
        }
        current--;
        return true;
    }

    @Override
    public boolean insert(int index, E e) {
        if(index>current||index<0||e==null) return false;
        else if(isEmpty())return addFirst(e);
        int i=0;
        Node<E> q=first;
        while(i!=index){
            q = q.next;
            i++;
        }
        Node<E> p=new Node<>(e);
        Node<E> r=getPrevious(q);
        r.next=p;
        p.next=q;
        current++;
        return true;
    }

    @Override
    public boolean set(int index, E e) {
        if(isEmpty()&&index>=current&&index<0) return false;
        int i=0;
        Node<E> q=first;
        while(i!=index){
            q = q.next;
            i++;
        }
        q.data=e;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return first == null && last ==null;
    }

    @Override
    public E get(int index) {
        if(isEmpty()&&index>=current&&index<0)return null;
        int i=0;
        Node<E> q=first;
        E p=q.data;
        while(i!=index){
            q = q.next;
            p = q.data;
            i++;
        }
        return p;
    }

    @Override
    public boolean contains(E e) {
        if(isEmpty())return false;
        int i=0;
        Node<E> q=first;
        while(q!=null && !q.data.equals(e)){
            q = q.next;
            i++;
        }
        return i != current;
    }

    @Override
    public boolean remove(int index) {
        if(isEmpty()||index>=current||index<0) return false;
        int i=0;
        Node<E> q=first;
        while(i!=index){
            q = q.next;
            i++;
        }
        if(q==last)return removeLast();
        else if(q==first)return removeFirst();
        Node<E> p=getPrevious(q);
        p.next=q.next;
        q.data=null;
        q.next=null;
        current--;
        return true;
    }
    
    @Override
    public String toString()
    {
        if(isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Node<E> p = first; p!=last; p=p.next)//hasta el penultimo
        {
            sb.append(p.data);
            sb.append(",");
        }
        sb.append(last.data);
        sb.append("]");
        return sb.toString();
    }
    
}
