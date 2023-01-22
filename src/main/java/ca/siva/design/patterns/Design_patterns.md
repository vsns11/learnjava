

    Creational Patterns
    > Builder pattern
      what?
         1) Encapsulate building a complex objects
         2) Abstract builder class, then used by concreted child builder classes to override the methods
         3) (Optional to have) Director controls building these builder objects
         4) if no director, client can consumer builder class directly.
         5) Main caveats is, it creates objects step by step, whereas abstract factory returns object in onego.
        e.g., StringBuilder
    Pros?

     Cons?
         
```java
public abstract class AircraftBuilder {

    public void buildEngine() {

    }

    public void buildWings() {

    }

    public void buildCockpit() {

    }

    public void buildBathrooms() {

    }

    abstract public IAircraft getResult();
}
```

```java
public class Boeing747Builder extends AircraftBuilder {

    Boeing747 boeing747;

    @Override
    public void buildCockpit() {

    }

    @Override
    public void buildEngine() {

    }

    @Override
    public void buildBathrooms() {
        
    }

    @Override
    public void buildWings() {

    }

    public IAircraft getResult() {
        return boeing747;
    }
}
/* Concrete builders*/
public class F16Builder extends AircraftBuilder {

    F16 f16;

    @Override
    public void buildEngine() {
        // get F-16 an engine
        // f16.engine = new F16Engine();
    }

    @Override
    public void buildWings() {
        // get F-16 wings
        // f16.wings = new F16Wings();
    }

    @Override
    public void buildCockpit() {
        f16 = new F16();
        // get F-16 a cockpit
        // f16.cockpit = new F16Cockpit();
    }

    public IAircraft getResult() {
        return f16;
    }
}
```

```java
/* Director */
public class Director {

    AircraftBuilder aircraftBuilder;

    public Director(AircraftBuilder aircraftBuilder) {
        this.aircraftBuilder = aircraftBuilder;
    }

    public void construct(boolean isPassenger) {
        aircraftBuilder.buildCockpit();
        aircraftBuilder.buildEngine();
        aircraftBuilder.buildWings();

        if (isPassenger)
            aircraftBuilder.buildBathrooms();
    }
}
```

```java
/* Client will consume directors and builders as above*/
public class Client {

    public void main() {

        F16Builder f16Builder = new F16Builder();
        Director director = new Director(f16Builder);
        director.construct(false);

        IAircraft f16 = f16Builder.getResult();
    }
}
```

    > Singleton pattern
       what?
       1) Create only 1 instance of the class. To acheive that, make constructor private
        so only class members can access the private constructor. And Global access to it exists thru a static variable.
       2) Multithreading and singleton - to avoid multiple threads to create multiple objects, 
            2.1.Use synchronized at the method level
            2.2 Use static initialization of the instance, which is always thread-safe.
          To optimize 2.1 further, use Double-Checked Locking, (anti-pattern)
        
      when to use?
       - pros
       - cons
            
```java
/*Singleton example*/
public class AirforceOne {

    // The sole instance of the class
    private static AirforceOne onlyInstance;

    // Make the constructor private so its only accessible to
    // members of the class.
    private AirforceOne() {
    }

    public void fly() {
        System.out.println("Airforce one is flying...");
    }

    // Create a static method for object creation
    public static AirforceOne getInstance() {

        // Only instantiate the object when needed.
        if (onlyInstance == null) {
            onlyInstance = new AirforceOne();
        }

        return onlyInstance;
    }
}

public class Client {

    public void main() {
        AirforceOne airforceOne = AirforceOne.getInstance();
        airforceOne.fly();
    }
}
```    
```java
/* Applied doublchecked locking to prevent synchronization overhead
 */
public class AirforceOneWithDoubleCheckedLocking {

    // The sole instance of the class. Note its marked volatile
    private volatile static AirforceOneWithDoubleCheckedLocking onlyInstance;

    // Make the constructor private so its only accessible to
    // members of the class.
    private AirforceOneWithDoubleCheckedLocking() {
    }

    public void fly() {
        System.out.println("Airforce one is flying...");
    }

    // Create a static method for object creation
    synchronized public static AirforceOneWithDoubleCheckedLocking getInstance() {

        // Only instantiate the object when needed.
        if (onlyInstance == null) {
            // Note how we are synchronizing on the class object
            synchronized (AirforceOneWithDoubleCheckedLocking.class) {
                if (onlyInstance == null) {
                    onlyInstance = new AirforceOneWithDoubleCheckedLocking();
                }
            }
        }

        return onlyInstance;
    }
}
```

    > Prototype pattern
       what?
      1) Prototype pattern means cloning an object, this is because sometimes object creation is more expensive than cloning.
      2) Number of classes will be greatly reduced by following this approach; as values can be altered upon cloning.
      3) Constructor is inaccessible to the client.

      when to use?
       1) It can be used when there's a change in few fields to create another variant of class.
        a single object(e.g., F16 and it's different variants ) can be used to derive those other objects upon cloning the prototype object and altering the needed fields.

```java
/* Prototype interface */
public interface IAircraftPrototype {

    void fly();

    IAircraftPrototype clone();

    void setEngine(F16Engine f16Engine);
}

```
```java
public class F16 implements IAircraftPrototype {

    // default engine
    F16Engine f16Engine = new F16Engine();

    @Override
    public void fly() {
        System.out.println("F-16 flying...");
    }

    @Override
    public IAircraftPrototype clone() {
        // Deep clone self and return the product
        return new F16();
    }

    public void setEngine(F16Engine f16Engine) {
        this.f16Engine = f16Engine;
    }
}
```
```java
/* Client instantiates the object */
public class Client {

    public void main() {

        IAircraftPrototype prototype = new F16();

        // Create F16-A
        IAircraftPrototype f16A = prototype.clone();
        f16A.setEngine(new F16AEngine());

        // Create F16-B
        IAircraftPrototype f16B = prototype.clone();
        f16B.setEngine(new F16BEngine());
    }
}
```
       - pros
         1) Eliminates subclassing variantes as they all are tweaked by cloning the prototype object.
         2) Dynamic loading????
       - cons
         1) Implementing a clone method can be challening because of circular references.

    > Factory method pattern
       what?
        > Instead of tightly coupling the code with the new ups, delegate actual instantiation of objects to subclasses.
        > Basic approach can be used is to follow simple factory pattern by giving another class for object creation.
        > In this pattern, delegate the object creation to the extended child classes by extending the base variant.
        > The factory method pattern might seem very similar to the simple or static factory, however, 
          the primary difference is that simple factories can't produce varying products through inheritance as a factory method pattern can.**
```java
/* Tightly coupling code to concrete impl class as it violates
    code to an interface and not to an implementation       
 */
SomeClass someClassObject = new SomeClass();
```
```java
public class F16 {

    IEngine engine;
    ICockpit cockpit;

    protected F16 makeF16() {
        engine = new F16Engine();
        cockpit = new F16Cockpit();
        return this;
    }

    public void taxi() {
        System.out.println("F16 is taxing on the runway !");
    }

    public void fly() {
        // Note here carefully, the superclass F16 doesn't know
        // what type of F-16 variant it was returned.
        F16 f16 = makeF16();
        f16.taxi();
        System.out.println("F16 is in the air !");
    }
}

public class F16A extends F16 {

    @Override
    public F16 makeF16() {
        super.makeF16();
        engine = new F16AEngine();
        return this;
    }
}

public class F16B extends F16 {

    @Override
    public F16 makeF16() {
        super.makeF16();
        engine = new F16BEngine();
        return this;
    }
}
```
```java
/* Client */
public class Client {
    public void main() {
        Collection<F16> myAirForce = new ArrayList<F16>();
        F16 f16A = new F16A();
        F16 f16B = new F16B();
        myAirForce.add(f16A);
        myAirForce.add(f16B);

        for (F16 f16 : myAirForce) {
            f16.fly();
        }
    }
}
```
      when to use?
       - pros
       - cons
           1) End up in creating too many subclasses.
           2) Downcasting may cause run time failure, where parent cannot be downcasted to the child class object.


    > AbstractFactory pattern
       what?
        1) Create interfaces for the corresponding class properties(fields) and main class
        2) Don't Delegate consumer to new up the object; instead consider having a factory class as a paremeter to the client main method.
        3) Factory method pattern responsible for creating a single product, whereas abstract factory takes care of all of the family products
```java
/*Factory interface used by all the factory classes*/
public interface IAircraftFactory {

    IEngine createEngine();

    IWings createWings();

    ICockpit createCockpit();
}
```
```java
/* Factory methods have their own implementation*/
public class F16Factory implements IAircraftFactory {

    @Override
    public IEngine createEngine() {
        return new F16Engine();
    }

    @Override
    public IWings createWings() {
        return new F16Wings();
    }

    @Override
    public ICockpit createCockpit() {
        return new F16Cockpit();
    }
}

public class Boeing747Factory implements IAircraftFactory {

    @Override
    public IEngine createEngine() {
        return new Boeing747Engine();
    }

    @Override
    public IWings createWings() {
        return new Boeing747Wings();
    }

    @Override
    public ICockpit createCockpit() {
        return new Boeing747Cockpit();
    }
}


```
```java
public class Aircraft {

    IEngine engine;
    ICockpit cockpit;
    IWings wings;
    IAircraftFactory factory;

    public Aircraft(IAircraftFactory factory) {
        this.factory = factory;
    }

    protected Aircraft makeAircraft() {
        engine = factory.createEngine();
        cockpit = factory.createCockpit();
        wings = factory.createWings();
        return this;
    }

    private void taxi() {
        System.out.println("Taxing on runway");
    }

    public void fly() {
        Aircraft aircraft = makeAircraft();
        aircraft.taxi();
        System.out.println("Flying");
    }
}

```
```java
public class Client {

    public void main() {

        // Instantiate a concrete factory for F-16
        F16Factory f16Factory = new F16Factory();
        
        // Instantiate a concrete factory for Boeing-747
        Boeing747Factory boeing747Factory = new Boeing747Factory();
        
        // Lets create a list of all our airplanes
        Collection<Aircraft> myPlanes = new ArrayList<>();
        
        // Create a new F-16 by passing in the f16 factory
        myPlanes.add(new Aircraft(f16Factory));

        // Create a new Boeing-747 by passing in the boeing factory
        myPlanes.add(new Aircraft(boeing747Factory));

        // Fly all your planes
        for (Aircraft aircraft : myPlanes) {
            aircraft.fly();
        }

    }
}

```
      when to use?
       - pros
         1) It is mainly useful for building the frameworks
       - cons
         1) Inclusion of different concrete factory can be done by creating a sub interface (extends main factory inteface), 
            by doing so, all of the in eligible factory has to declare those methods and return null value. 
         2) Concrete methods can be best represented as a singleton object.




================================

    Structural Patterns:
    > Adaptor pattern
    > Bridge pattern
    > Composite pattern
    > Decorator pattern
    > Facade pattern
    > Flyweight pattern
    > Proxy pattern


================================

    Behavioral Patterns
    > Chain of Responsiblity pattern
    > Observer pattern
    > Interpreter pattern
    > Command pattern
    > Iterator pattern
    > Mediator pattern
    > Memento pattern
    > State pattern
    > Template method
    > Strategy pattern
    > Visitor pattern

```
      when to use?
       - pros
         1) It is mainly useful for building the frameworks
       - cons
         1) Inclusion of different concrete factory can be done by creating a sub interface (extends main factory inteface), 
            by doing so, all of the in eligible factory has to declare those methods and return null value. 
         2) Concrete methods can be best represented as a singleton object.