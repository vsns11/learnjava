package ca.siva.ds.misc;

/**
 * YOU CANNOT USE COMPLEX DATATYPES INSIDE ANNOTATION
 */
@interface MyAnnotation {

    String value() default "";

    String name();

    int age();

    String[] newNames();

}