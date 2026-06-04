package rvt.diagram;

import java.lang.reflect.Array;
import java.util.ArrayList;

class A implements IA{}
class B extends A implements IB{}
class D {IA ia;}
class E {ArrayList<C> c;}
class C extends B implements IC {ArrayList<E> e;}
interface IA {}
interface IB {}
interface IC {}
