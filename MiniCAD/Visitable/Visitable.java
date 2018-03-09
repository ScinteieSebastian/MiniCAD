package Visitable;

import Visitor.Visitor;

public interface Visitable {
    void accept(Visitor visit);

}