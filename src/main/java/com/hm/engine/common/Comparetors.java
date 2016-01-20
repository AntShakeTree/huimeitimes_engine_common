package com.hm.engine.common;

import com.hm.engine.common.annotation.HmRequired;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * Created by ant_shake_tree on 15/11/17.
 */
public class Comparetors {


    public static void sorts(TreeSet set, Object o, double score) {

        set.add(new ComparetorObject(o, score));
    }

    public static <T> void sortList(List<T> collection, String propertyName) {
        if(collection==null||collection.size()==0)return;
        if (StringUtils.isEmpty(propertyName)) {
            if(collection.get(0) instanceof Comparable) {

               sort((List<Comparable>)collection);
            }
            return;
        }
        BeanComparator beanComparator = new BeanComparator(propertyName);
        List<ComparetorProperty<T>> list = new ArrayList<>(collection.size());
        for (T t : collection) {
            list.add(new ComparetorProperty(t, propertyName).beanComparator(beanComparator));
        }
        sort(list, (List<T>) collection);
    }

    /**
     * 输出List
     *
     * @param collection
     * @param propertyName
     * @param <T>
     */
    public static <T> List sortSet(Set<T> collection, String propertyName) {
        if (StringUtils.isEmpty(propertyName)) return Collections.EMPTY_LIST;
        BeanComparator beanComparator = new BeanComparator(propertyName);
        List<ComparetorProperty<T>> list = new ArrayList<>(collection.size());
        List<T> outL = new ArrayList<>(collection.size());
        for (T t : collection) {
            list.add(new ComparetorProperty(t, propertyName).beanComparator(beanComparator));
        }

        sortSet(list, outL);
        return outL;
    }

    public static class ComparetorObject implements Comparable<ComparetorObject>, Serializable {
        private Object object;
        private double score;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public ComparetorObject(Object object, double score) {
            this.object = object;
            this.score = score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ComparetorObject that = (ComparetorObject) o;

            return !(object != null ? !object.equals(that.object) : that.object != null);

        }

        @Override
        public int hashCode() {
            return object != null ? object.hashCode() : 0;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }


        @Override
        public int compareTo(ComparetorObject o) {
            if(this.object.equals(o.getObject())){
                return 0;
            }
            return (int) (this.getScore()-o.getScore());
        }
    }

    private static class ComparetorProperty<T> implements Comparable<ComparetorProperty> {
        private final T target;
        private final String property;
        private BeanComparator beanComparator;

        @Override
        public int compareTo(ComparetorProperty o) {
            if (o == null || this.property == null)
                return 0;
            if (this.beanComparator == null) {
                return 0;
            }
            if (this.target == null || o.getTarget() == null) return 0;
            return this.beanComparator.compare(this.target, o.getTarget());
        }

        public ComparetorProperty(T target, String property) {
            this.target = target;
            this.property = property;
        }

        public ComparetorProperty beanComparator(BeanComparator beanComparator) {
            this.beanComparator = beanComparator;
            return this;
        }

        public T getTarget() {
            return target;
        }

    }


    public static class TestClass {
        @HmRequired
        private Long id;
        @HmRequired
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 改写java 源代码中的sort
     *
     * @param list
     * @param c
     * @param <T>
     */
    private static <T> void sort(List<ComparetorProperty<T>> list, List<T> c) {
        Object[] a = list.toArray();
        Arrays.sort(a);
        ListIterator<T> ci = c.listIterator();
        for (int j = 0; j < a.length; j++) {
            ci.next();
            Comparetors.ComparetorProperty comparetorPropert = (Comparetors.ComparetorProperty) a[j];
            ci.set((T) comparetorPropert.getTarget());
        }
    }

    private static <T> void sortSet(List<ComparetorProperty<T>> list, List<T> c) {
        Object[] a = list.toArray();
        Arrays.sort(a);

        for (int j = 0; j < a.length; j++) {
            Comparetors.ComparetorProperty comparetorPropert = (Comparetors.ComparetorProperty) a[j];
            c.add((T) comparetorPropert.getTarget());
        }
    }
    private static <T extends Comparable<? super T>> void sort(List<T> list) {
        Object[] a = list.toArray();
        Arrays.sort(a);

        if(!(list instanceof LinkedList)&&!(list instanceof ArrayList)){
            list.clear();
            for (int j=0; j<a.length; j++) {
                list.add(j, (T) a[j]);
            }
        }else {
            ListIterator<T> i = list.listIterator();
            for (int j=0; j<a.length; j++) {
                i.next();
                i.set((T)a[j]);
            }
        }
    }


}
//test ====================================================================================
//    public static void main(String[] args) {
//        Set<TestClass> setT = Sets.newHashSet();
//        for (int i = 0; i < 100; i++) {
//            TestClass testClass = new TestClass();
//            testClass.setId(1l + i + RandomUtils.nextLong(1, 100));
//            testClass.setName("a" + i);
//            setT.add(testClass);
//        }
//
//        List<TestClass> list= Comparetors.sortSet(setT, "id");
//        for (TestClass testClass : list) {
//            System.out.println(testClass.getId());
//        }
//    }