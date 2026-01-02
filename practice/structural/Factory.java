package structural;

abstract class Vehicle
{
    public abstract void Display();
}

class Cycle extends Vehicle
{
    public void Display()
    {
        System.out.println("This is a cycle");
    }
}

class Bike extends Vehicle
{
    public void Display()
    {
        System.out.println("This is a Bike");
    }
}

interface VehicleFactory
{
    Vehicle CreateVehicle();
}

class CycleFactory implements VehicleFactory
{
    public Vehicle CreateVehicle()
    {
        return new Cycle();
    }
}

class BikeFactory implements VehicleFactory
{
    public Vehicle CreateVehicle()
    {
        return new Bike();
    }
}

class Client
{
    private Vehicle vehicle;

    public Client(VehicleFactory factory)
    {
        vehicle = factory.CreateVehicle();
    }

    public Vehicle GetVehicle()
    {
        return vehicle;
    }
}

public class Factory
{
    public static void main(String[] args)
    {
        VehicleFactory cycleFactory = new CycleFactory();
        Client cycleClient = new Client(cycleFactory);
        Vehicle cycle = cycleClient.GetVehicle();
        cycle.Display();

        VehicleFactory bikeFactory = new BikeFactory();
        Client bikeClient = new Client(bikeFactory);
        Vehicle bike = bikeClient.GetVehicle();
        bike.Display();

    }
}

