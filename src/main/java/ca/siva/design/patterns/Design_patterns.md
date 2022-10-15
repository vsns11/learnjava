

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
      
      when to use?
       - pros
       - cons
 
    > Prototype pattern
       what?
      
      when to use?
       - pros
       - cons

    > Factory method pattern
       what?
      
      when to use?
       - pros
       - cons

    > AbstractFactory pattern
       what?
      
      when to use?
       - pros
       - cons


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
