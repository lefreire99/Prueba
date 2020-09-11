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
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
    
    private Node<E> first;
    private Node<E> last;
    private int current;
    
    public DoublyLinkedList()
    {
        first = last = null;
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
    
    public ListIterator<E> listiterator(){
        ListIterator<E> lit = new ListIterator<E>(){
            private Node<E> p = first;
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
                if(p==first){
                   Node<E>q=p.next;
                   first=q;
                   q.previous=null;
                   //p.data=null;
                   //p.previous=null;
                   //p.next=null;
                   p=q;
                }else if(p==last){
                    Node<E> q=last.previous;
                    q.next=null;
                    //last.data=null;
                    //last.next=null;
                    //last.previous=null;
                    last=q;
                    p=q;
                }else if(last==first){
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
                if(last==first){
                    last=q;
                    first=q;
                }else if(p==first){
                    q.previous=null;
                    q.next=first;
                    first.previous=q;
                    first=q;
                }else if(p==last){
                    q.previous=last;
                    q.next=null;
                    last.next=q;
                    last=q;
                }
                else{
                    q.previous=p.previous;
                    q.previous.next=q;
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
                if(p==first){
                   Node<E>q=p.next;
                   first=q;
                   q.previous=null;
                   //p.data=null;
                   //p.previous=null;
                   //p.next=null;
                   p=q;
                }else if(p==last){
                    Node<E> q=last.previous;
                    q.next=null;
                    //last.data=null;
                    //last.next=null;
                    //ast.previous=null;
                    last=q;
                    p=q;
                }else if(last==first){
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
                if(last==first){
                    last=q;
                    first=q;
                }else if(p==first){
                    q.previous=null;
                    q.next=first;
                    first.previous=q;
                    first=q;
                }else if(p==last){
                    q.previous=last;
                    q.next=null;
                    last.next=q;
                    last=q;
                }
                else{
                    q.previous=p.previous;
                    q.previous.next=q;
                    p.previous=q;
                    q.next=p;
                    //p=q;
                }
            }
        };
        return lit;
    }
    
    private class Node<E>{
        private E data;
        private Node<E> next;
        private Node<E> previous;
        
        public Node(E data)
        {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
       
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

    @Override
    public boolean addFirst(E e) {
        Node<E> n=new Node<>(e);
        if(e==null) return false;
        else if(isEmpty())
            first=last=n;
        else{
            n.next=first;
            n.next.previous=n;
            first=n;
            
        }
        current++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        Node<E> n = new Node<>(e);
        if(e == null) return false;
        else if(isEmpty())
            first = last = n;
        else
        {
            last.next = n;
            n.previous = last;
            last = n;
        }
        current++;
        return true;
    }

    @Override
    public E getFirst() {
        if(isEmpty()) return null;
        return first.data;
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
        else if(first == last)
        {
            last.data = null;//help GC
            first = last = null;
        }
        else
        {
            Node<E> prev = last.previous;
            prev.next = null;
            last.previous = null;
            last.data = null;// help GC
            last = prev;
        }
        current--;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()) return false;
        else if(first==last){
            last.data = null;//help GC
            first = last = null;
        }
        else{
            Node<E> q=first;
            first=q.next;
            first.previous=null;
            q.next=null;
            q.data=null;
        }
        current--;
        return true;
    }

    @Override
    public boolean insert(int index, E e) {
        if(index>current||index<0||e==null) return false;
        else if(isEmpty()) return addFirst(e);
        int i=0;
        Node<E> q=first;
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
        if(isEmpty()) return false;
        if(index>=current) return false;
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
        return first == null && last == null;
    }

    @Override
    public E get(int index) {
        if(isEmpty()||index>=current||index<0)return null;
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
        for(Node<E> p = first; p!=last; p=p.next)//hasta el penultimo
        {
            sb.append(p.data);
            sb.append(",");
        }
        sb.append(last.data);
        sb.append("]");
        return sb.toString();
    }
    //Ejercicio 1
    public DoublyLinkedList<E> slicing(int start, int end){
        if(isEmpty() || end>current || start<0 || end<0 || start==end) return null;
        DoublyLinkedList<E> nueva=new DoublyLinkedList<>();
        int inicio=start;
        while(inicio!=end){
            E a=searchNode(inicio).data;
            nueva.addLast(a);
            inicio++;
        }
        return nueva;
    }
}
