package ca.siva.design.patterns;

// Base class (Parent)
class Vehicle {
    private String name;
    private String model;
    Vehicle(String name, String model) {
        this.name = name;
        this.model = model;
    }
    public void getName() {
        System.out.print("The car is a " + name + " " + model);
    }
}

// Single inheritance
// FuelCar class extending from Vehicle class
// Derived class (Child)
class FuelCar extends Vehicle {
    private String combustType;
    FuelCar(String name, String model, String combustType) {
        super(name, model);
        this.combustType = combustType;
    }
    public void getFuelCar() {
        getName();
        System.out.print(", combust type is " + combustType);
    }
}

// Hierarchical inheritance
// Alongside the FuelCar class, the ElectricCar class is also extending from Vehicle class
// Another Derived class (Child)
class ElectricCar extends Vehicle {
    private String batteryPower;
    ElectricCar(String name, String model, String batteryPower) {
        super(name, model);
        this.batteryPower = batteryPower;
    }
    public void getElectricCar() {
        getName();
        System.out.print(", battery power is " + batteryPower);
    }
}

// Multi-level inheritance
// GasolineCar class is derived from the FuelCar class, which is further derived from the Vehicle class
// Derived class (Grandchild)
class GasolineCar extends FuelCar {
    private String combustType;
    private String gasCapacity;
    GasolineCar(String name, String model, String combustType, String gasCapacity) {
        super(name, model, combustType);
        this.gasCapacity = gasCapacity;
    }
    public void getGasolineCar() {
        getName();
        System.out.print(", gas capacity is " + gasCapacity);
    }
}

// Java does not support Multiple inheritance via classes

// main
public class Inheritance
{
    public static void main(String[] args) {
        System.out.println("Single inheritance:");
        FuelCar Fuel = new FuelCar("Honda", "Accord", "Petrol");
        Fuel.getFuelCar();
        System.out.println("\n");

        System.out.println("Hierarchical inheritance:");
        ElectricCar Electric = new ElectricCar("Tesla", "ModelX", "200MWH");
        Electric.getElectricCar();
        System.out.println("\n");

        System.out.println("Multi-Level inheritance:");
        GasolineCar Gasoline = new GasolineCar("Toyota", "Corolla", "Gasoline", "30 liters");
        Gasoline.getGasolineCar();


        System.out.println("\n");
        System.out.println("Java does not support Multiple Inheritance through classes");
    }
}
