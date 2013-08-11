package com.kelepi.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kelepi.common.bean.EquivalenceProperty;
import com.kelepi.common.bean.KeyValue;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;


public class ListUtil {

    /**
     * 查找集合中指定属性的集合
     * 如果为null不加入返回集合
     * @param <T>
     * @param <K>
     * @param objs
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T, K> List<T> getPropertiesFromList(List<K> objs, String name) {
        String start = name.substring(0, 1);
        String methodName = "get" + start.toUpperCase() + name.substring(1);

        List<T> list = new LinkedList<T>();
        try {
            for (Object obj : objs) {

                T filed = (T) obj.getClass()
                        .getDeclaredMethod(methodName, null).invoke(obj, null);

                if (filed != null) {
                    list.add(filed);
                }
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T, K> Set<T> getPropertySetFromList(List<K> objs, String name) {
        String start = name.substring(0, 1);
        String methodName = "get" + start.toUpperCase() + name.substring(1);

        Set<T> set = new HashSet<T>();
        try {
            for (Object obj : objs) {

                T filed = (T) obj.getClass()
                        .getDeclaredMethod(methodName, null).invoke(obj, null);

                if (filed != null) {
                    set.add(filed);
                }
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return set;
        }
    }

    /**
     * 查找一个指定属性值得对象
     * @param <T>
     * @param <K>
     * @param objs
     * @param name
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T, K> T getObjectFromList(List<T> objs, String name, K value) {
        String start = name.substring(0, 1);
        String methodName = "get" + start.toUpperCase() + name.substring(1);

        T ret = null;
        try {
            for (T obj : objs) {
                K filed = (K) obj.getClass()
                        .getDeclaredMethod(methodName, null).invoke(obj, null);

                if (filed.equals(value)) {
                    ret = obj;
                    break;
                }
            }
        } catch (IllegalArgumentException e) {

        } catch (SecurityException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        } catch (NoSuchMethodException e) {
        }

        return ret;
    }

    /**
     * 查找第一个非指定属性值的对象
     * 如果找不到，则返回第一个对象
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T, K> T getObjectNoProperty(List<T> objs, String name, K value) {
        if (objs.size() == 0) {
            return null;
        }

        String start = name.substring(0, 1);
        String methodName = "get" + start.toUpperCase() + name.substring(1);

        T ret = objs.get(0);
        try {
            for (T obj : objs) {
                K filed = (K) obj.getClass()
                        .getDeclaredMethod(methodName, null).invoke(obj, null);

                if (!filed.equals(value)) {
                    ret = obj;
                    break;
                }
            }
        } catch (IllegalArgumentException e) {

        } catch (SecurityException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        } catch (NoSuchMethodException e) {
        }

        return ret;
    }

    /**
     * 查找指定属性值的集合
     * @param <T>
     * @param <K>
     * @param objs
     * @param name
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T, K> List<T> getObjectsFromList(List<T> objs, String name, K value) {
        String start = name.substring(0, 1);
        String methodName = "get" + start.toUpperCase() + name.substring(1);

        List<T> rets = new LinkedList<T>();
        try {
            for (T obj : objs) {
                K filed = (K) obj.getClass()
                        .getDeclaredMethod(methodName, null).invoke(obj, null);

                if (filed.equals(value)) {
                    rets.add(obj);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } catch (SecurityException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (InvocationTargetException e) {
            System.out.println(e);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }

        return rets;
    }

    /**
     * 查找指定属性值的集合
     * @param <T>
     * @param <K>
     * @param objs
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T, K> List<T> getObjectsFromListByKeyValues(List<T> objs, String name, List<K> values) {
        String start = name.substring(0, 1);
        String methodName = "get" + start.toUpperCase() + name.substring(1);

        List<T> rets = new LinkedList<T>();
        try {
            for (T obj : objs) {
                K filed = (K) obj.getClass()
                        .getDeclaredMethod(methodName, null).invoke(obj, null);


                if (values.contains(filed)) {
                    rets.add(obj);
                }
            }
        } catch (IllegalArgumentException e) {

        } catch (SecurityException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        } catch (NoSuchMethodException e) {
        }

        return rets;
    }


    /**
     * 查找一个指定属性值得对象
     * @param <T>
     * @param <K>
     * @param objs
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T, K> T getObjectFromList(List<T> objs, List<KeyValue> keyValues) {

        T ret = null;
        try {
            for (T obj : objs) {

                boolean right = true;
                for (KeyValue keyValue : keyValues) {
                    K filed = (K) obj.getClass()
                            .getDeclaredMethod(getMethodNameForGet(keyValue.getKey()), null).invoke(obj, null);

                    if (filed == null && keyValue.getValue() == null) {
                        continue;
                    }
                    if (!filed.equals(keyValue.getValue())) {
                        right = false;
                        break;
                    }
                }

                if (right) {
                    ret = obj;
                    break;
                }
            }
        } catch (IllegalArgumentException e) {

        } catch (SecurityException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        } catch (NoSuchMethodException e) {
        }

        return ret;
    }

    /**
     * 查找指定属性值的集合
     * @param <T>
     * @param <K>
     * @param objs
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T, K> List<T> getObjectsFromList(List<T> objs, List<KeyValue> keyValues) {
        List<T> rets = new LinkedList<T>();
        try {
            for (T obj : objs) {

                boolean right = true;
                for (KeyValue keyValue : keyValues) {
                    K filed = (K) obj.getClass()
                            .getDeclaredMethod(getMethodNameForGet(keyValue.getKey()), null).invoke(obj, null);

                    if (!filed.equals(keyValue.getValue())) {
                        right = false;
                        break;
                    }
                }

                if (right) {
                    rets.add(obj);
                }
            }
        } catch (IllegalArgumentException e) {

        } catch (SecurityException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        } catch (NoSuchMethodException e) {
        }

        return rets;
    }

    private static String getMethodNameForGet(String property) {
        String start = property.substring(0, 1);
        String methodName = "get" + start.toUpperCase() + property.substring(1);

        return methodName;
    }

    private static String getMethodNameForSet(String property) {
        String start = property.substring(0, 1);
        String methodName = "set" + start.toUpperCase() + property.substring(1);

        return methodName;
    }

    public static <K> Number getProductSumForList(List<K> objList, String... pros) {
        List<Object> listFirst = new LinkedList<Object>();
        Map<String, List> paras = new HashMap<String, List>();
        paras.put("first", listFirst);
        Number productSum = 0;
        for (K obj : objList) {
            Number produce = 1;
            for (int i = 0; i < pros.length; i++){
                try {
                    String pro = pros[i];
                    Number proValue = (Number)obj.getClass().getDeclaredMethod(getMethodNameForGet(pro), null).invoke(obj, null);
                    produce = produce.doubleValue() * proValue.doubleValue();

                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            productSum = productSum.doubleValue() + produce.doubleValue();
        }

        return productSum;
    }

    public static <K, T> T togetherCategoryByProperties(List<K> objList, String... pros) {
        List<Object> listFirst = new LinkedList<Object>();
        Map<String, List> paras = new HashMap<String, List>();
        paras.put("first", listFirst);
        for (K obj : objList) {
            List<String> proValues = new LinkedList<String>();
            for (int i = 0; i < pros.length; i++){
                String pro = pros[i];
                String preKey = StringUtils.join(proValues, "_");

                List preList = paras.get(preKey);
                if (i == 0) {
                    preList = listFirst;
                }

                try {
                    String proValue = "" + obj.getClass().getDeclaredMethod(getMethodNameForGet(pro), null).invoke(obj, null);
                    proValues.add(proValue);
                    String currKey = StringUtils.join(proValues, "_");
                    List listT = paras.get(currKey);
                    if(listT == null) {
                        listT = new LinkedList<Object>();

                        if (i == pros.length - 1) {
                            listT.add(obj);
                        }

                        preList.add(listT);
                        paras.put(currKey, listT);
                    } else {
                        if (i == pros.length - 1) {
                            listT.add(obj);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return (T)listFirst;
    }

    @SuppressWarnings("unchecked")
    public static <T, K> Map<K, List<T>> togetherMapByProperty(List<T> objs, String name) {
        String start = name.substring(0, 1);
        String methodName = "get" + start.toUpperCase() + name.substring(1);

        Map<K, List<T>> sameMap = new HashMap<K, List<T>>();

        try {
            for (T obj : objs) {
                K filed = (K) obj.getClass().getDeclaredMethod(methodName, null).invoke(obj, null);

                if (filed != null) {
                    if (sameMap.containsKey(filed)) {
                        sameMap.get(filed).add(obj);
                    } else {
                        List<T> rets = new LinkedList<T>();
                        rets.add(obj);
                        sameMap.put(filed, rets);
                    }
                }
            }
        } catch (IllegalArgumentException e) {

        } catch (SecurityException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        } catch (NoSuchMethodException e) {
        }

        return sameMap;
    }

    public static <T, K> Map<K, T> toMapByProperty(Collection<T> objs, String name) {
        String start = name.substring(0, 1);
        String methodName = "get" + start.toUpperCase() + name.substring(1);

        Map<K, T> sameMap = new HashMap<K, T>();

        try {
            for (T obj : objs) {
                K filed = (K) obj.getClass().getDeclaredMethod(methodName, null).invoke(obj, null);

                if (filed != null) {
                    sameMap.put(filed, obj);
                }
            }
        } catch (IllegalArgumentException e) {

        } catch (SecurityException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        } catch (NoSuchMethodException e) {
        }

        return sameMap;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<List<T>> togetherListByPrperties(List<T> objs, String... propertiesNames) {

        Map<String, List<T>> sameMap = new HashMap<String, List<T>>();
        List<List<T>> sameList = new LinkedList<List<T>>();

        try {
            for (T obj : objs) {
                String key = "";
                for (String propertiesName : propertiesNames) {
                    String filed = obj.getClass().getDeclaredMethod(getMethodNameForGet(propertiesName), null).invoke(obj, null).toString();
                    if (filed != null) {
                        key += filed;
                    }
                }

                if (!"".equals(key)) {
                    if (sameMap.containsKey(key)) {
                        sameMap.get(key).add(obj);
                    } else {
                        List<T> rets = new LinkedList<T>();
                        rets.add(obj);
                        sameMap.put(key, rets);

                        sameList.add(rets);
                    }
                }
            }
        } catch (IllegalArgumentException e) {

        } catch (SecurityException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        } catch (NoSuchMethodException e) {
        }

        return sameList;
    }

    @SuppressWarnings("unchecked")
    public static <K, T> Map<K, T> toMapFromList(List<T> objects, String property) {
        Map<K, T> map = new HashMap<K, T>();

        for (T obj : objects) {
            try {
                K filed = (K) obj.getClass()
                        .getDeclaredMethod(getMethodNameForGet(property), null).invoke(obj, null);
                map.put(filed, obj);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return map;
    }

    @SuppressWarnings("unchecked")
    public static <T, K> K getListFromCategoryList(List<T> objs) {

        List<Object> allList = new LinkedList<Object>();

        addList(allList, objs);

        return (K)allList;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void addList(List list, Object b) {
        if (b instanceof List) {
            List c = (List)b;
            for (Object d : c) {
                addList(list, d);
            }
        } else {
            list.add(b);
        }
    }

    /**
     * 将源列表合并到目标列表中，按property相等，然后设值到目标objectName中
     * @param <T>
     * @param <K>
     * @param srcList
     * @param destList
     * @param propertyName
     * @param objectName
     */
    public static <T, K> void mergerTwoListByProperties(List<T> srcList, List<K> destList, String propertyName, String objectName) {
        for (K destObject : destList) {
            for (T srcObject : srcList) {
                try {
                    Object srcFiled = srcObject.getClass().getDeclaredMethod(getMethodNameForGet(propertyName)).invoke(srcObject);
                    Object destFiled = destObject.getClass().getDeclaredMethod(getMethodNameForGet(propertyName)).invoke(destObject);

                    if (srcFiled.equals(destFiled)) {
                        destObject.getClass().getDeclaredMethod(getMethodNameForSet(objectName), srcObject.getClass()).invoke(destObject, srcObject);
                        //srcList.remove(srcObject);
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将源列表合并到目标列表中，按property相等，然后设值到目标objectName中
     * @param <T>
     * @param <K>
     * @param srcList
     * @param destList
     * @param objectName
     */
    public static <T, K> void mergerTwoListByEquivalenceProperty(List<T> srcList, List<K> destList, String objectName, EquivalenceProperty... properties) {
        for (K destObject : destList) {
            for (T srcObject : srcList) {
                try {
                    boolean isEquivalence = true;
                    for (EquivalenceProperty property : properties) {

                        Object srcFiled = srcObject.getClass().getDeclaredMethod(getMethodNameForGet(property.getSrcName())).invoke(srcObject);
                        Object destFiled = destObject.getClass().getDeclaredMethod(getMethodNameForGet(property.getDestName())).invoke(destObject);
                        if (!srcFiled.equals(destFiled)) {
                            isEquivalence = false;
                            break;
                        }
                    }

                    if (isEquivalence) {
                        destObject.getClass().getDeclaredMethod(getMethodNameForSet(objectName), srcObject.getClass()).invoke(destObject, srcObject);
                        break;
                    }


                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param <T>
     * @param objList  待排序列表
     * @param order    排序方式("desc", "asc")
     * @param properties  排序的字段，按循序来（不支持对象排序，每个属性值只能为基本类型（bollean类型安字符类型处理））
     * @return
     */
    public static  <T> List<T> orderByProperties(List<T> objList, String order, String... properties) {
        List<T> returnList = new LinkedList<T>();
        List<String> PropertyList = new ArrayList<String>();
        PropertyList.addAll(Arrays.asList(properties));
        orderBy(returnList, objList, order, PropertyList);

        return returnList;
    }

    private static <T, K> void orderBy(List<T> list, List<T> objlist, String order, List<String> propertyList) {
        if (propertyList.size() > 0 && objlist.size() > 0) {
            String property = propertyList.remove(0);

            K filed;
            try {
                filed = (K) objlist.get(0).getClass().getDeclaredMethod(getMethodNameForGet(property), null).invoke(objlist.get(0), null);

                if (filed instanceof Integer) {
                    Set<Integer> tttsSet = getPropertySetFromList(objlist, property);
                    List<Integer> ttts = new LinkedList<Integer>(tttsSet);
                    if ("desc".equals(order)) {
                        Collections.sort(ttts, Collections.reverseOrder());
                    } else {
                        Collections.sort(ttts);
                    }

                    Map<Integer, List<T>> maps = togetherMapByProperty(objlist, property);

                    for(Integer str : ttts) {
                        List<T> aab = maps.get(str);

                        orderBy(list, aab, order, propertyList);
                    }
                } else if (filed instanceof Byte) {
                    Set<Byte> tttsSet = getPropertySetFromList(objlist, property);
                    List<Byte> ttts = new LinkedList<Byte>(tttsSet);
                    if ("desc".equals(order)) {
                        Collections.sort(ttts, Collections.reverseOrder());
                    } else {
                        Collections.sort(ttts);
                    }

                    Map<Byte, List<T>> maps = togetherMapByProperty(objlist, property);

                    for(Byte str : ttts) {
                        List<T> aab = maps.get(str);

                        orderBy(list, aab, order, propertyList);
                    }
                } else if (filed instanceof Short) {
                    Set<Short> tttsSet = getPropertySetFromList(objlist, property);
                    List<Short> ttts = new LinkedList<Short>(tttsSet);
                    if ("desc".equals(order)) {
                        Collections.sort(ttts, Collections.reverseOrder());
                    } else {
                        Collections.sort(ttts);
                    }

                    Map<Short, List<T>> maps = togetherMapByProperty(objlist, property);

                    for(Short str : ttts) {
                        List<T> aab = maps.get(str);

                        orderBy(list, aab, order, propertyList);
                    }
                } else if (filed instanceof Character) {
                    Set<Character> tttsSet = getPropertySetFromList(objlist, property);
                    List<Character> ttts = new LinkedList<Character>(tttsSet);
                    if ("desc".equals(order)) {
                        Collections.sort(ttts, Collections.reverseOrder());
                    } else {
                        Collections.sort(ttts);
                    }

                    Map<Character, List<T>> maps = togetherMapByProperty(objlist, property);

                    for(Character str : ttts) {
                        List<T> aab = maps.get(str);

                        orderBy(list, aab, order, propertyList);
                    }
                } else if (filed instanceof Double) {
                    Set<Double> tttsSet = getPropertySetFromList(objlist, property);
                    List<Double> ttts = new LinkedList<Double>(tttsSet);
                    if ("desc".equals(order)) {
                        Collections.sort(ttts, Collections.reverseOrder());
                    } else {
                        Collections.sort(ttts);
                    }

                    Map<Double, List<T>> maps = togetherMapByProperty(objlist, property);

                    for(Double str : ttts) {
                        List<T> aab = maps.get(str);

                        orderBy(list, aab, order, propertyList);
                    }
                } else if (filed instanceof Float) {
                    Set<Float> tttsSet = getPropertySetFromList(objlist, property);
                    List<Float> ttts = new LinkedList<Float>(tttsSet);
                    if ("desc".equals(order)) {
                        Collections.sort(ttts, Collections.reverseOrder());
                    } else {
                        Collections.sort(ttts);
                    }

                    Map<Float, List<T>> maps = togetherMapByProperty(objlist, property);

                    for(Float str : ttts) {
                        List<T> aab = maps.get(str);

                        orderBy(list, aab, order, propertyList);
                    }
                } else if (filed instanceof Long) {
                    Set<Long> tttsSet = getPropertySetFromList(objlist, property);
                    List<Long> ttts = new LinkedList<Long>(tttsSet);
                    if ("desc".equals(order)) {
                        Collections.sort(ttts, Collections.reverseOrder());
                    } else {
                        Collections.sort(ttts);
                    }

                    Map<Long, List<T>> maps = togetherMapByProperty(objlist, property);

                    for(Long str : ttts) {
                        List<T> aab = maps.get(str);

                        orderBy(list, aab, order, propertyList);
                    }
                } else if (filed instanceof Boolean) {
                    Set<Boolean> tttsSet = getPropertySetFromList(objlist, property);
                    List<Boolean> ttts = new LinkedList<Boolean>(tttsSet);
                    if ("desc".equals(order)) {
                        Collections.sort(ttts, Collections.reverseOrder());
                    } else {
                        Collections.sort(ttts);
                    }

                    Map<Boolean, List<T>> maps = togetherMapByProperty(objlist, property);

                    for(Boolean str : ttts) {
                        List<T> aab = maps.get(str);

                        orderBy(list, aab, order, propertyList);
                    }
                } else if (filed instanceof String) {
                    Set<String> tttsSet = getPropertySetFromList(objlist, property);
                    List<String> ttts = new LinkedList<String>(tttsSet);
                    if ("desc".equals(order)) {
                        Collections.sort(ttts, Collections.reverseOrder());
                    } else {
                        Collections.sort(ttts);
                    }

                    Map<String, List<T>> maps = togetherMapByProperty(objlist, property);

                    for(String str : ttts) {
                        List<T> aab = maps.get(str);

                        orderBy(list, aab, order, propertyList);
                    }
                } else if (filed instanceof Date) {
                    Set<Date> tttsSet = getPropertySetFromList(objlist, property);
                    List<Date> ttts = new LinkedList<Date>(tttsSet);
                    if ("desc".equals(order)) {
                        Collections.sort(ttts, Collections.reverseOrder());
                    } else {
                        Collections.sort(ttts);
                    }

                    Map<Date, List<T>> maps = togetherMapByProperty(objlist, property);

                    for(Date str : ttts) {
                        List<T> aab = maps.get(str);

                        orderBy(list, aab, order, propertyList);
                    }
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            list.addAll(objlist);
        }
    }

    /**
     * 如果src不足位数，用comp补全
     * @param src
     * @param length
     * @return
     */
    public static String getStringCompletion(String src, int length, String comp) {
        int len = length - src.length();
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                src += comp;
            }
        }

        return src;
    }

    public static int getListSizeFromCategoryList(Object lists) {
        if (lists instanceof List){
            return getListSizeFromCategoryList(0, (List)lists);
        }
        return 0;
    }

    private static int getListSizeFromCategoryList(int size, List<Object> lists) {

        if (lists.isEmpty()) {
            return size;
        }
        if (lists.get(0) instanceof List) {
            for (Object obj : lists) {
                size = getListSizeFromCategoryList(size, (List<Object>)obj);
            }
        } else {
            size += 1;
        }

        return size;
    }

    public static int getItemSizeFromCategoryList(List<Object> lists) {
        return getItemSizeFromCategoryList(0, lists);
    }

    private static int getItemSizeFromCategoryList(int size, List<Object> lists) {
        if (lists.isEmpty()) {
            return size;
        }
        if (lists.get(0) instanceof List) {
            for (Object obj : lists) {
                size = getItemSizeFromCategoryList(size, (List<Object>)obj);
            }
        } else {
            size += lists.size();
        }

        return size;
    }

    /**
     * 将对象列表转换为keyavalue列表，通常供前端页面调用
     * @param <T>
     * @param objs
     * @param keyName
     * @param valueName
     * @return
     */
    public static <T> List<KeyValue> getKeyValues(List<T> objs, String keyName, String valueName) {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();

        String keyNameForGet = getMethodNameForGet(keyName);
        String valueNameForGet = getMethodNameForGet(valueName);


        try {
            for (T obj : objs) {


                String key = (String) obj.getClass().getDeclaredMethod(keyNameForGet, null).invoke(obj, null);

                Object value = obj.getClass().getDeclaredMethod(valueNameForGet, null).invoke(obj, null);

                if (key != null) {
                    KeyValue keyValue = new KeyValue(key, value);
                    keyValues.add(keyValue);
                }


            }
        } catch (IllegalArgumentException e) {

        } catch (SecurityException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        } catch (NoSuchMethodException e) {
        }

        return keyValues;
    }
}
