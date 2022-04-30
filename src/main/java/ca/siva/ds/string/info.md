
> By default, from Java9 onwards JVM stores characters in UTF-16 format (2 or 4 bytes for a character).
> A String is immutable once created.
> You cannot use protected, private modifiers on interface methods. Because by default all methods are implicitly public.
> It is allowed to expand accessibility on the overriding methods.
  e.g. superclass has a protected method, and the same method in subclass overriding it can accept public.
