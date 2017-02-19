package com.lohika.myazin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;

public class ListOf<T> extends ForwardingList<T> implements RandomAccess {

  private ArrayList<T> list = Lists.newArrayList();
  
  public ListOf() {
  }

  public ListOf(ListOf<T> listToCopy) {
    list = Lists.newArrayList(listToCopy);
  }

  @Override
  protected List<T> delegate() {
    return list;
  }

  public ListOf<T> withAppended(T item) {
    ListOf<T> newItems = new ListOf<T>();
    newItems.list = Lists.newArrayList(this.list);
    newItems.list.add(item);
    return newItems;
  }

  public ListOf<T> without(T item) {
    ListOf<T> newItems = new ListOf<T>();
    newItems.list = Lists.newArrayList(this.list);
    newItems.list.remove(item);
    return newItems;
  }

  public ListOf<T> withPrepended(T item) {
    ListOf<T> newItems = new ListOf<T>();
    newItems.list = Lists.newArrayList();
    newItems.list.add(item);
    newItems.list.addAll(this.list);
    return newItems;
  }

  public T getSome() {
    if (size() == 0) {
      return null;
    } else {
      return list.get(new Random().nextInt(size()));
    }
  }
  
}
