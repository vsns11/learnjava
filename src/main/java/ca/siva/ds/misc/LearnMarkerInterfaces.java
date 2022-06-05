package ca.siva.ds.misc;

public class LearnMarkerInterfaces {
    public static void main(String[] args) throws CloneNotSupportedException {
        TestClone1 obj1 = new TestClone1();
        System.out.println(obj1.clone());
    }

}

class TestClone1  {
    public Integer field1;
    public Integer field2;

    public TestClone1() {

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}