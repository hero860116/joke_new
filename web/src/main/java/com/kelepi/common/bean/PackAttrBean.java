package com.kelepi.common.bean;

/**
 * 包装属性<2属性版>
 * 有时候需要将两个甚至多个相关属性包装到一起做为一组，然后又有多组进行传递处理
 * @author Administrator
 * 属性值不可为null  如果有需要再考虑容错，当前不可为null
 * @param <A>
 * @param <B>
 */
public class PackAttrBean<A, B> {
    private A a;
    private B b;


    public PackAttrBean(A a, B b) {
        super();
        this.a = a;
        this.b = b;
    }



    public PackAttrBean() {
        super();
    }



    public A getA() {
        return a;
    }
    public void setA(A a) {
        this.a = a;
    }
    public B getB() {
        return b;
    }
    public void setB(B b) {
        this.b = b;
    }

    public boolean equals(PackAttrBean<A,B> obj) {
        if (a.equals(obj.getA()) && b.equals(obj.getB())) {
            return true;
        } else {
            return false;
        }
    }
}
