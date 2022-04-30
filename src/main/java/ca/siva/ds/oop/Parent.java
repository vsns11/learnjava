package ca.siva.ds.oop;

public interface Parent {
    String getParentData();

    Child getChild();

    interface Child {
        String getChildData();
    }
}

class Dummy implements Parent{
    @Override
    public String getParentData() {
        return null;
    }

    @Override
    public Child getChild() {
        return null;
    }
}
