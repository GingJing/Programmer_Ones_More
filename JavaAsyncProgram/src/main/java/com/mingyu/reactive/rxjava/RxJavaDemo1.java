package com.mingyu.reactive.rxjava;

import com.mingyu.reactive.domain.Person;
import io.reactivex.rxjava3.core.Flowable;

import java.util.ArrayList;
import java.util.List;

/**
 * rxjava demo1
 *
 * @date: 2020/9/24 8:39
 * @author: GingJingDM
 * @version: 1.0
 */
public class RxJavaDemo1 {

    public static void main(String[] args) {
        List<Person> personList = personList();

        Flowable.fromArray(personList.toArray(new Person[0]))
                .filter(person -> person.getAge() % 100 == 0)
                .map(Person::getName)
                .subscribe(System.out::println);
    }

    private static List<Person> personList() {
        List<Person> personList = new ArrayList<>();
        for (int i = 1; i < 1001; i++) {
            Person person = new Person("p" + i, i);
            personList.add(person);
        }
        return personList;
    }
}
