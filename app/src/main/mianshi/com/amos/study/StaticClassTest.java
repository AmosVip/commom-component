package com.amos.study;

import com.google.gson.internal.LinkedHashTreeMap;
import com.google.gson.internal.LinkedTreeMap;

import java.lang.ref.SoftReference;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: amos
 * @date: 2021/5/10 9:59
 * @description:
 */
class StaticClassTest {
    //SoftReference
    public final String strWord;
    public static String sWord;

    public StaticClassTest() {
        strWord = "22";
       // Proxy.newProxyInstance()
    }

    public static String num1 = "num1";

    public static void sum() {
        /*HashMap<String, Integer> temp = new HashMap<>();
        temp.put();
        Hashtable hashtable = new Hashtable();
        LinkedHashMap;
        LinkedTreeMap;
        TreeMap;
        LinkedHashTreeMap;
        ConcurrentHashMap;
        ArrayList;
        LinkedList linkedList = new LinkedList();
        linkedList.add();
        CopyOnWriteArrayList;
        AtomicInteger;*/



    }

    public final void testFinalMethod() {
        // StringBuffer
        new InnerClass() {

        };
    }


    class InnerClass {

        public void methods() {

        }
    }
}
