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
 * @author lfrei
 */
public class CircularDoublyLinkedList<E> implements List<E>,Iterable<E> {
    
    private Node<E> last;
    private int current;
    
    public CircularDoublyLinkedList(){
        last=null;
        current=0;
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
    
    public ListIterator<E> listiterator(){
        ListIterator<E> lit = new ListIterator<E>(){
            private Node<E> p = last.next;
            private int i = 0;
            @Override
            public boolean hasNext() {
                return p!=null;
            }

            @Override
            public E next() {
                E tmp = p.data;
                p = p.next;
                i++;
                return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return p!=null;
            }

            @Override
            public E previous() {
                E tmp = p.data;
                p = p.previous;
                i--;
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
                if(p==last.next){
                   Node<E>q=p.next;
                   last.next=q;
                   q.previous=last;
                   //p.data=null;
                   //p.previous=null;
                   //p.next=null;
                   p=q;
                }else if(p==last){
                    Node<E> q=last.previous;
                    q.next=last.next;
                    //last.data=null;
                    //last.next=null;
                    //last.previous=null;
                    last=q;
                    last.next.previous=last;
                    p=q;
                }else if(last==last.next){
                    last.data=null;
                    last.next=null;
                    last.previous=null;
                    last=null;
                }else{
                    Node<E>q=p.next;
                    q.previous=p.previous;
                    q.previous.next=q;
                    //p.data=null;
                    //p.next=null;
                    //p.previous=null;
                    p=q;
                }
                i--;
            }

            @Override
            public void set(E arg0) {
                p.data=arg0;
            }

            @Override
            public void add(E arg0) {
                Node<E> q=new Node<>(arg0);
                if(last==null){
                    last=q;
                    last.next=q;
                    last.previous=q;
                }else{
                    p.previous.next=q;
                    q.previous=p.previous;
                    p.previous=q;
                    q.next=p;
                    //p=q;
                }
                
            }
        };
        return lit;
    }
    
    public ListIterator<E> listiterator(int index)
    {
        ListIterator<E> lit = new ListIterator<E>(){
            private Node<E> p = searchNode(index);
            private int i = index;
            @Override
            public boolean hasNext() {
                return p!=null;
            }

            @Override
            public E next() {
                E tmp=p.data;
                p=p.next;
                i++;
                return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return p!=null;
            }

            @Override
            public E previous() {
                E tmp = p.data;
                p=p.previous;
                i--;
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
                if(p==last.next){
                   Node<E>q=p.next;
                   last.next=q;
                   q.previous=last;
                   //p.data=null;
                   //p.previous=null;
                   //p.next=null;
                   p=q;
                }else if(p==last){
                    Node<E> q=last.previous;
                    q.next=last.next;
                    //last.data=null;
                    //last.next=null;
                    //last.previous=null;
                    last=q;
                    last.next.previous=last;
                    p=q;
                }else if(last==last.next){
                    last.data=null;
                    last.next=null;
                    last.previous=null;
                    last=null;
                }else{
                    Node<E>q=p.next;
                    q.previous=p.previous;
                    q.previous.next=q;
                    //p.data=null;
                    //p.next=null;
                    //p.previous=null;
                    p=q;
                }
                i--;
            }

            @Override
            public void set(E arg0) {
                p.data=arg0;
            }

            @Override
            public void add(E arg0) {
                Node<E> q=new Node<>(arg0);
                if(last==last.next){
                    last=q;
                    last.next=q;
                    last.previous=q;
                }else{
                    p.previous.next=q;
                    q.previous=p.previous;
                    p.previous=q;
                    q.next=p;
                    //p=q;
                }
            }
        };
        return lit;
    }
    
    private class Node<E>
    {
        private E data;
        private Node<E> next;
        private Node<E> previous;
        
        public Node(E data)
        {
            this.data = data;
            this.next = null;
            this.previous=null;
        }        
    }
    
    private Node<E>searchNode(int index){
        if(index>current||index<0) return null;
        else if(isEmpty()) return null;
        int i=0;
        Node<E> q=last.next;
        while(i!=index){
            q = q.next;
            i++;
        }
        return q;
    }
    
    @Override
    public boolean addFirst(E e) {
        Node<E> n = new Node<>(e);
        if(e==null)return false;
        if(isEmpty()){
            last = n;
            last.next = last;
            last.previous=last;
        }
        else
        {
            n.next = last.next;
            n.previous=last;
            last.next.previous=n;
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
            last.previous=last;
        }
        else
        {
            n.next = last.next;
            n.previous=last;
            last.next.previous=n;
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
            last.previous=null;
            last=null;
        }
        else{
            Node<E> q=last.previous;
            q.next=last.next;
            last.next.previous=q;
            last.data=null;
            last.next=null;
            last.previous=null;
            last=q;
            
        }
        current--;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()) return false;
        else if(last==last.next){
            last.data=null;
            last.next=null;
            last.previous=null;
            last=null;
        }
        else
        {
            Node<E> p = last.next;
            last.next = p.next;
            p.next.previous=last;
            p.data = null;//help GC
            p.next = null;
            p.previous=null;
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
        Node<E> r=q.previous;
        r.next=p;
        p.next=q;
        p.previous=r;
        q.previous=p;
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
        Node<E> p=q.previous;
        p.next=q.next;
        p.next.previous=p;
        q.data=null;
        q.next=null;
        q.previous=null;
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
    
    //Taller Lista
    public void switchFirstAndLast(){
        Node<E> first=last.next;
        Node<E> ultimo=last;
        last.next.next.previous=ultimo;
        last.previous.next=first;
        ultimo.next=last.next.next;
        first.previous=last.previous;
        last.next=ultimo;
        last=first;
    }
}
