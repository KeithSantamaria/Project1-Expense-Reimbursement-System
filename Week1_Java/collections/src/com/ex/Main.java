package com.ex;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//	    List<String> names = new ArrayList<>();
//	    names.add("August");
//	    names.add("Jesse");
//	    names.add("Stephen");
//
//	    List<Integer> numbers = new ArrayList<>();
//	    int[] moreNumbers = new int[10];
//
//	    numbers.add(1);
//		System.out.println(numbers.get(0));
//
//	    for(int i = 0; i < names.size(); i++) {
//            System.out.println(names.get(i));
//        }
//
//		System.out.println();
//	    for(String name : names) {
//			System.out.println(name);
//		}
//
//		System.out.println();
//		Iterator<String> itr = names.iterator();
//	    while(itr.hasNext()) {
//	    	String name = itr.next();
//			System.out.println(name);
//			if(name.equals("August")) {
//				System.out.println("Removing name " + name);
//				itr.remove();
//			}
//		}

//	    Set<Integer> income = new HashSet<>();
//	    income.add(10);
//	    income.add(120);
//	    income.add(55);
//	    income.add(1);
////	    income.add(10);
//
//	    for(int n : income) {
//			System.out.println(n);
//		}

//		System.out.println(income.contains(10000));
    	Map<String, Integer> personAges = new HashMap<>();
//    	personAges.put("August", 38);
//    	personAges.put("August!", 40);
//		System.out.println("August".hashCode());
//		System.out.println("August!".hashCode());
//
//		for(Map.Entry<String, Integer> persons : personAges.entrySet()) {
//			System.out.println(persons.getKey() + ": " + persons.getValue());
//		}

		Person p = new Person("August", 38);
    	Person p2 = new Person("Jesse", 34);
    	System.out.println(p.hashCode() == p2.hashCode());

    }


}

class Person {
	private String name;
	private int age;
	private String suffix;
	private String middleName;
	private Date dob;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return age == person.age && name.equals(person.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, age, suffix, middleName, dob);
	}
}