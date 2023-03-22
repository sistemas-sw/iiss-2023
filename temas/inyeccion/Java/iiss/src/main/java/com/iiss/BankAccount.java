package com.iiss;

import java.util.*;
import java.io.*;
import java.time.*;

public final class BankAccount implements Comparable<BankAccount> {
  private final String id;
  private LocalDate creationDate;
  private Comparator comparator;

  //Para las inyecciones mediante constructor
  public BankAccount(String number, Comparator<BankAccount> cmp, LocalDate date) {
    System.out.println("\nInstantiated via constructor\n");
    id = number;
    comparator = cmp;
    creationDate = date;
  }

  public BankAccount(String number) {
    id = number;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate date) {
    this.creationDate = date;
  }

  public String getId() {
    return id;
  }

  public void setComparator(Comparator cmp) {
    System.out.println("\nInstantiated via setter");
    comparator = cmp;
  }

  @Override
  public int compareTo(BankAccount other) {
    if (this == other)
      return 0;
    assert this.equals(other) : "compareTo inconsistent with equals.";
    return comparator.compare(this, other);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other)
      return true;
    if (!(other instanceof BankAccount))
      return false;
    BankAccount that = (BankAccount) other;
    return this.id.equals(that.getId());
  }

  @Override
  public String toString() {
    return id.toString();
  }
}

