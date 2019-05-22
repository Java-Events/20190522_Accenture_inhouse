package org.rapidpm.vaadin.v10.tb.demo;

import static java.lang.String.valueOf;


public class MyService implements Service {

  private int clickcount = 0;


  public String doSomeWork(){
    return valueOf(++clickcount);
  }



}
