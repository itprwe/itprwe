package itprwe.base;

import java.util.*;

/**
 * 类集框架（数据结构与算法）
 *
 * 	Collections
 *
 * 		熟练掌握封装数组与链表之后的高级语言特性，List、Map、Set、Quene、Stack等数据结构API的使用与基本算法（打表等）。
 *
 * 			Basic
 * 				hashmap
 * 					实现原理
 * 						链表散列的数据结构，即数组和链表的结合体
 * 						hashmap底层是一个数组结构，数组的每一项又是一个链表。新来的entry插入到链表时，使用头插法。使用头插法的原因是，hashmap的作者任务后插入的entry被查到的可能性比较大
 * 						put entry,通过hash算法确定 保存在数组的位置，再根据equals方法确定 在该数组位置上的链表中的存储位置
 * 						get entry,通过hash算法找到在数组中存储位置，在使用equals方法从此位置的链表中却出entry
 * 						当链表过长时，java8引入 红黑树来解决效率问题
 * 					线程不安全
 * 						在多线程情况下，会导致hashmap出现链表闭环，一旦进入了闭环get数据，程序就会进入死循环，所以导致HashMap是非线程安全的。
 * 						根本原因是：没有锁的机制，一个线程的修改会影响另一个线程的操作
 * 						高并发下使用更加安全的ConcurrentHashMap
 * 				arrayList
 *
 * 		熟练掌握Guava、Apache等开源类库提供的集合包装与基本数据类型创建、排序、过滤、判空、判重等API。
 *
 * 		基本了解常用API的原理实现,了解常用API时间与空间复杂度
 *
 * 		以上均不区分语言,搞清楚C#与Java API的映射关系即可熟练掌握。
 */
public class Base {

    public static void main(String[] args) {
        System.out.println("hhhh");

        //数组初始化后大小不可变

        String[] ss = new String[3];

//        1.实现接口与实现类相分离，List与ArrayList，LinkList等
//        2.支持泛型
//        3.java访问集合通过统一方式，迭代器Iterator
//        4. a,b,c,d,e,f....元素类似数组一样有序排列
        ArrayList<String> arrayList= new ArrayList<>();
//        1.链表，HEAD -> a|~ -> b|~ -> c|~ 每个元素包含一个数据和一个指向下一个元素的指针
        LinkedList<String> linkedList = new LinkedList<>();

        arrayList.add("aa");
        arrayList.add("bb");
        arrayList.add("cc");
        arrayList.add("cc");
        arrayList.add(1,"iiii");

        for (Iterator<String> it = arrayList.iterator();it.hasNext();){
            System.out.println(it.next());
        }

        //ok
        for (String s : arrayList){
            System.out.println(s);
        }

        System.out.println(arrayList.get(0)+arrayList.get(1)+arrayList.get(2)+arrayList.get(3));

        arrayList.remove(1);
        System.out.println(arrayList.get(0)+arrayList.get(1)+arrayList.get(2)+arrayList.get(3));

        System.out.println("============================================");

        linkedList.add("aa");
        linkedList.addFirst("first");
        linkedList.add(1,"index");
        linkedList.addLast("last");
        linkedList.remove(1);
        for (String ls : linkedList){
            System.out.println(ls);
        }


        HashSet hs = new HashSet();
        LinkedHashSet lhs = new LinkedHashSet();
        TreeSet ts =  new TreeSet();
        hs.add("aaa");
        hs.add("aaa");

        System.out.println(hs);



    }
}
