package ca.siva.ds.annotation;



@StudentInfo(name = "Siva", age = 25)
public class Data {

    public static void main(String[] args) {
        Data d = new Data();
        Class<? extends Object> clazz = d.getClass();
        StudentInfo x = clazz.getAnnotation(StudentInfo.class);
        System.out.println(x.age());
        System.out.println(x.name());
    }

}
