/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.omar.cms.ui;

import java.io.PrintStream;

/**
 *
 * @author carbo
 */
public class Greeter {

  public void greet(PrintStream to, String name) {
    to.println(createGreeting(name));
  }

  public String createGreeting(String name) {
    return "Hello, " + name + "!";
  }
}
