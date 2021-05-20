package com.amos.study.kotlin;

import java.io.Serializable;

/**
 * @author: amos
 * @date: 2021/4/6 12:52
 * @description:
 */

class JavaStudy implements Serializable {
     public static void main(String[] args){
         Outer.Inner inner = new Outer().new Inner();
         inner.showAge();
     }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}


class Outer {
    public int age = 18;

    class Inner {
        public int age = 20;

        public void showAge() {
            int age = 25;
            System.out.println(age);//空1
            System.out.println(this.age);//空2
            System.out.println(Outer.this.age);//空3
        }
    }
}
