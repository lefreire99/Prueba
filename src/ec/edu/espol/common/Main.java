/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.common;
import ec.edu.espol.util.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
//import java.util.Iterator;
/**
 *
 * @author eduardo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Ejercicio 1
        DoublyLinkedList<Integer> lista1=new DoublyLinkedList<>();
        lista1.addFirst(1);
        lista1.addLast(2);
        lista1.addLast(3);
        lista1.addLast(4);
        System.out.println(lista1);
        lista1.slicing(0, 3);
        System.out.println(lista1.slicing(0, 3));
        
        SimplyLinkedList<Integer> lista2=new SimplyLinkedList<>();
        lista2.addFirst(1);
        lista2.addLast(2);
        lista2.addLast(3);
        lista2.addLast(4);
        lista2.addLast(5);
        lista2.addLast(6);
        lista2.addLast(7);
        lista2.addLast(8);
        lista2.addLast(9);
        Iterator<Integer> it=lista2.iteratorStep(0, 4);
        while(it.hasNext()){
            System.out.println(it.next());
            
        }
        
        //Ejercicio 2
        List<String> lista3=new ArrayList<>();
        lista3.addLast("abc");
        lista3.addLast("aa");
        lista3.addLast("aa");
        lista3.addLast("cd");
        System.out.println(eliminarPalabrasRepetidas(lista3));
        
        List<String> lista4=new ArrayList<>();
        lista4.addLast("juan");
        lista4.addLast("juan");
        lista4.addLast("juan");
        lista4.addLast("pedro");
        lista4.addLast("juan");
        System.out.println(eliminarPalabrasRepetidas(lista4));
        
        //Ejercicio 3
        Queue<Mail> emails=new LinkedList<>();
        LinkedList<Mail> l=new LinkedList<>();
        l.add(new Mail("pacifico.com","lfreire@gmail.com",0,"hola"));
        l.add(new Mail("hola.edu","samaniego@gmail.com",0,"jajaja"));
        l.add(new Mail("ecotec.html","lfreire@gmail.com",0,"nooo"));
        emails.addAll(l);
        
        
    }
    //Ejercicio 2
    public static List<String> eliminarPalabrasRepetidas(List<String> palabras){
        Stack<String> pilaPalabras=new Stack<>();
        ArrayList<String> pala=(ArrayList) palabras;
        List<String> palabrasUnicas=new ArrayList<>();
        String pal="";
        for(String p:pala){
            if(p.equals(pal) && !pilaPalabras.empty()){
                pilaPalabras.pop();
            }else{
                pilaPalabras.push(p);
            }
            pal=p;
        }
        while(!pilaPalabras.empty()){
            palabrasUnicas.addLast(pilaPalabras.pop());
        }
        return palabrasUnicas;
    }
    //Ejercicio 3
    public static Map<String, ArrayList<LinkedList<Mail>>> distribuirEmails (Queue<Mail> emails, Map<String, Integer> filtroSpam){
        PriorityQueue<Mail> mails=new PriorityQueue<>((Mail m1,Mail m2)->m2.getImportancia()-m1.getImportancia());
        mails.addAll(emails);
        Map<String,ArrayList<LinkedList<Mail>>> distribuir=new HashMap();
        while(!mails.isEmpty()){
            Mail m=mails.poll();
            String remi=m.getRemitente();
            String desti=m.getDestinatario();
            String mensa=m.getMensaje();
            ArrayList<LinkedList<Mail>> guardados=new ArrayList<>();
            int riesgo=filtroSpam.get(remi);
            if(distribuir.containsKey(desti)){
                ArrayList<LinkedList<Mail>> g=distribuir.get(desti);
                ArrayList<LinkedList<Mail>> gua=new ArrayList<>();
                LinkedList<Mail> me=g.get(0);
                LinkedList<Mail> sp=g.get(1);
                if(riesgo==0){
                    me.add(m);
                }else sp.add(m);
                gua.addLast(me);guardados.addLast(sp);
                distribuir.put(desti,gua);
            }else{
                LinkedList<Mail> mensaje=new LinkedList<>();
                LinkedList<Mail> spam=new LinkedList<>();
                if(riesgo==0){
                    mensaje.add(m);
                }else spam.add(m);
                guardados.addLast(mensaje);guardados.addLast(spam);
                distribuir.put(desti, guardados);
            }
        }
        return distribuir;
    }
    
}
