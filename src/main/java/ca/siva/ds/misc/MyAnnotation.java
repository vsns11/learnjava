package ca.siva.ds.misc;

import java.lang.annotation.*;

/**
 * YOU CANNOT USE COMPLEX DATATYPES INSIDE ANNOTATION
 * 3 types of compile time annotations
 * 1) Deprecated
 * 2) SuppressWarnings
 * 3) Contended
 * 4) Override
 *
 */

/**
 * possible RetentionPolicies:
 * 1. RetentionPolicy.CLASS
 *      default policy applied if none mentioned, it means annotation is stored in .class file
 *      but not available during runtime.
 * 2. RetentionPolicy.SOURCE
 *       annotation is only available in source file, not in .class file. not during runtime too.
 *       It is used mainly with build tools
 * 3. RetentionPolicy.RUNTIME
 *      Annotation is available during runtime.
 */
@Retention(RetentionPolicy.CLASS)
/**
 * ElementType.ANNOTATION_TYPE
 * ElementType.CONSTRUCTOR
 * ElementType.FIELD
 * ElementType.LOCAL_VARIABLE
 * ElementType.METHOD
 * ElementType.PACKAGE
 * ElementType.PARAMETER
 * ElementType.TYPE
 * ElementType.TYPE_PARAMETER
 * ElementType.TYPE_USE
 */
@Target(ElementType.TYPE)
/**
 * the subclass also inherits the annotation, when the super class applies this annotation.
 */
@Inherited
@interface MyAnnotation {

    String value() default "";

    String name();

    int age();

    String[] newNames();

}