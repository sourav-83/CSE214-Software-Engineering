interface Airplane {
    void requestTakeoff();
    void requestLanding();
    void notifyAirTrafficControl(String message);
}

interface AirTrafficControlTower {
    void requestTakeoff(Airplane airplane);
    void requestLanding(Airplane airplane);
}

class CommercialAirplane implements Airplane {
    
    private AirTrafficControlTower mediator;

    public CommercialAirplane(AirTrafficControlTower mediator)
    {
        this.mediator = mediator;
    }

    public void requestTakeoff()
    {
        mediator.requestTakeoff(this);
    }

    public void requestLanding()
    {
        mediator.requestLanding(this);
    }

    public void notifyAirTrafficControl(String message)
    {
        System.out.println("Commercial Airplane:" + message);
    } 
}

class AirportControlTower implements AirTrafficControlTower {

    public void requestTakeoff(Airplane airplane)
    {
        airplane.notifyAirTrafficControl("Requesting takeoff clearance.");
    }

    public void requestLanding(Airplane airplane)
    {
        airplane.notifyAirTrafficControl("Requesting landing clearance.");
    }
}

public class mediator {
    public static void main(String [] args)
    {
        AirTrafficControlTower controlTower = new AirportControlTower();

        Airplane airplane1 = new CommercialAirplane(controlTower);
        Airplane airplane2 = new CommercialAirplane(controlTower);

        airplane1.requestTakeoff();
        airplane2.requestLanding();

    }
    
}
