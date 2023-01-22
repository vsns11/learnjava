package ca.siva.ds.misc;

public class DummyItemValue implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public DummyItemValue() {}

    public DummyItemValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DummyItemValue{" +
                "value='" + value + '\'' +
                '}';
    }

    String value;
}
