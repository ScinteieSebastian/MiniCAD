package FactoryShape;

import java.util.ArrayList;

import form.Shape;
//interfata pe catre o folosim pentru impementarea Factory Pattern
public interface IFactory {
    Shape createForm(ArrayList<String> list);
}