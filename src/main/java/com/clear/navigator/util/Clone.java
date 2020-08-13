package com.clear.navigator.util;

import java.io.*;

/**
 * ClassName CopyMap
 *
 * @author qml
 * Date 2020/8/7 9:12
 * Version 1.0
 **/
public class Clone {
    public static Object deepClone(Object obj) {
        try {// 将对象写到流里
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            // 从流里读出来
            oo.writeObject(obj);
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            return (oi.readObject());
        } catch (Exception e) {
            return null;
        }
    }
}
