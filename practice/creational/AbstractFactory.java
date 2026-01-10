interface Car{
    void Assemble();
}

interface CarSpecification{
    void Display();
}

class SUV implements Car
{
    public void Assemble()
    {
        System.out.println("Assembling SUV :)");
    }
}

class Luxury implements Car
{
    public void Assemble()
    {
        System.out.println("Assembling Luxury :)");
    }
}

class NorthAmericaSpecification implements CarSpecification
{
    public void Display()
    {
        System.out.println("Displaying North American Car Specification!");
    }
}

class EuropeSpecification implements CarSpecification
{
    public void Display()
    {
        System.out.println("Displaying European Car Specification!");
    }
}

interface CarFactory
{
    Car CreateCar();
    CarSpecification CreateSpecification();
}

class NorthAmericaCarFactory implements CarFactory
{
    public Car CreateCar()
    {
        return new SUV();
    }

    public CarSpecification CreateSpecification()
    {
        return new NorthAmericaSpecification();
    }
}

class EuropeCarFactory implements CarFactory
{
    public Car CreateCar()
    {
        return new Luxury();
    }

    public CarSpecification CreateSpecification()
    {
        return new EuropeSpecification();
    }
}

public class AbstractFactory
{
    public static void main(String[] args)
    {
        CarFactory northAmericaFactory = new NorthAmericaCarFactory();
        Car northAmericaCar = northAmericaFactory.CreateCar();
        CarSpecification northAmericaSpecification = northAmericaFactory.CreateSpecification();
        
        northAmericaCar.Assemble();
        northAmericaSpecification.Display();


        CarFactory europeCarFactory = new EuropeCarFactory();
        Car europeCar = europeCarFactory.CreateCar();
        CarSpecification europeSpecification = europeCarFactory.CreateSpecification();
        
        europeCar.Assemble();
        europeSpecification.Display();

    }
}