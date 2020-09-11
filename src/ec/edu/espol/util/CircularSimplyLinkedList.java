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
public class CircularSimplyLinkedList<E> implements List<E>,Iterable<E> {
    
    private Node<E> last;
    private int current;
    
    public CircularSimplyLinkedList()
    {
        last = null;
        current = 0;
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private Node<E> p = last.next;
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
        Node<E> n = new Node<>(e);
        if(e==null)return false;
        if(isEmpty()){
            last = n;
            last.next = last;
        }
        else
        {
            n.next = last.next;
            last.next = n;
        }
        current++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        Node<E> n = new Node<>(e);
        if(e==null)return false;
        if(isEmpty()){
            last = n;
            last.next = last;
        }
        else
        {
            n.next = last.next;
            last.next = n;
            last = n;
        }
        current++;
        return true;
    }

    @Override
    public E getFirst() {
        if(isEmpty()) return null;
        return last.next.data;
    }

    @Override
    public E getLast() {
        if(isEmpty()) return null;
        return last.data;
    }

    @Override
    public int indexOf(E e) {
        if(isEmpty())return -1;
        int i=0;
        Node<E> q=last.next;
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
        else if(last==last.next){
            last.data=null;
            last.next=null;
            last=null;
        }
        else{
            Node<E> q=getPrevious(last);
            q.next=last.next;
            last.data=null;
            last.next=null;
            last=q;
        }
        current--;
        return true;
    }
    
    private Node<E> getPrevious(Node<E> p)
    {
        Node<E> q = last;
        if(isEmpty()||p==null) return null;
        do{
            if(q.next==p)return q;
            q=q.next;
        }while(q!=last);
        return null;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()) return false;
        else if(last==last.next){
            last.data=null;
            last.next=null;
            last=null;
        }
        else
        {
            Node<E> p = last.next;
            last.next = p.next;
            p.data = null;//help GC
            p.next = null;
        }
        current--;
        return true;
    }

    @Override
    public boolean insert(int index, E e) {
        if(index>current||index<0||e==null) return false;
        else if(isEmpty()) return addLast(e);
        int i=0;
        Node<E> q=last.next;
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
        if(isEmpty()||index>=current||index<0) return false;
        int i=0;
        Node<E> q=last.next;
        while(i!=index){
            q = q.next;
            i++;
        }
        q.data=e;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return last == null;
    }

    @Override
    public E get(int index) {
        if(isEmpty()||index>=current||index<0)return null;
        int i=0;
        Node<E> q=last.next;
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
        Node<E> p = last.next;
        do{
            if(p.data.equals(e))
                return true;
            p=p.next;
        }while(p!=last.next);
        return false;
    }

    @Override
    public boolean remove(int index) {
        if(isEmpty()||index>=current||index<0) return false;
        int i=0;
        Node<E> q=last.next;
        while(i!=index){
            q = q.next;
            i++;
        }
        if(q.equals(last)) return removeLast();
        else if(q==last.next)return removeFirst();
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
        for(Node<E> p = last.next; p!=last; p=p.next)//hasta el penultimo
        {
            sb.append(p.data);
            sb.append(",");
        }
        sb.append(last.data);
        sb.append("]");
        return sb.toString();
    }
    
}
