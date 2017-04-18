package edu.cmu.sv.isstac.sampling.exploration;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Objects;

import edu.cmu.sv.isstac.sampling.structure.Node;
import gov.nasa.jpf.vm.ChoiceGenerator;

/**
 * @author Kasper Luckow
 *
 */
public class Path implements Serializable {
  private LinkedList<Integer> store;
  
  public Path(Path other) {
    this.store = new LinkedList<>();
    for(int o : other.store) {
      this.store.add(o);
    }
  }
  
  public Path() {
    this.store = new LinkedList<>();
  }

  public Path(List<Integer> choices) {
    this.store = new LinkedList<>(choices);
  }

  public Path(ChoiceGenerator<?> cg) {
    this();
    if(cg != null) {
      for(ChoiceGenerator<?> c : cg.getAll()) {
        this.addChoice(c);
      }
    }
  }
  
  public Path(Node n) {
    this();
    Node node = n;
    while(node != null && node.getChoice() >= 0) {
      this.store.addFirst(node.getChoice());
      node = node.getParent();
    }
  }
  
  // same as copy constructor
  public Path copy() {
    return new Path(this);
  }
  
  public void addChoice(ChoiceGenerator<?> cg) {
    //BIG FAT WARNING: 
    //This is in general UNSAFE to do,
    //because there is NO guarantee that choices are selected
    //incrementally! However, there does not seem to be another
    //way of obtaining a lightweight representation of the path
    //i.e. a sequence of decisions (represented by ints)
    //I think it is safe for ThreadChoiceFromSet (currently our only nondeterministic choice)
    //and PCChoiceGenerator
    int choice = cg.getProcessedNumberOfChoices() - 1;
    assert choice >= 0;
    addChoice(choice);
  }

  //A bit expensive since store is a linked list at the moment
  public int getChoice(int index) {
    return this.store.get(index);
  }

  public boolean isPrefix(Path other) {
    if(other.length() > this.length()) {
      return false;
    }
    for(int i = 0; i < other.length(); i++) {
      if(!store.get(i).equals(other.store.get(i))) {
        return false;
      }
    }
    return true;
  }

  public int length() {
    return store.size();
  }

  public void addChoice(int choice) {
    store.add(choice);
  }

  public int removeLast() {
    return store.removeLast();
  }
  
  @Override
  public int hashCode() {
    return Objects.hashCode(store);
  }
  
  @Override
  public boolean equals(Object other) {
    if(other == null) return false;
    if(getClass() != other.getClass()) return false;
    Path otherPath = (Path) other;
    return Objects.equal(this.store, otherPath.store);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    Iterator<Integer> iter = store.iterator();
    while(iter.hasNext()) {
      sb.append(iter.next());
      if(iter.hasNext())
        sb.append(",");
    }
    sb.append("]");
    return sb.toString();
  }
}
