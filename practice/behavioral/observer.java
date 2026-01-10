import java.util.ArrayList;
import java.util.List;


interface subscriber
{
    void update(String weather);
}

interface publisher
{
    void addSubscriber(subscriber Subscriber);
    void removeObserver(subscriber Subscriber);
    void notifySubscribers();
    void setStatus(String status);
}

class weatherStation implements publisher
{
    public List<subscriber> subscribers = new ArrayList<>();
    private String weather;

    public void addSubscriber(subscriber Subscriber)
    {
        subscribers.add(Subscriber);
    }

    public void removeObserver(subscriber Subscriber)
    {
        subscribers.remove(Subscriber);
    }

    public void notifySubscribers()
    {
        for (subscriber Subscriber: subscribers)
        {
            Subscriber.update(weather);
        }
    }

    public void setStatus(String Weather)
    {
        weather = Weather;
        notifySubscribers();
    }
}


class PhoneDisplay implements subscriber {
    private String weather;

    public void update(String weather) {
        this.weather = weather;
        display();
    }

    private void display() {
        System.out.println("Phone Display: Weather updated - " + weather);
    }
}

class TVDisplay implements subscriber {
    private String weather;

    public void update(String weather) {
        this.weather = weather;
        display();
    }

    private void display() {
        System.out.println("TV Display: Weather updated - " + weather);
    }
}

public class observer 
{
    public static void main(String[] args)
    {
        weatherStation ws = new weatherStation();

        subscriber phone1 = new PhoneDisplay();
        subscriber tv1 = new TVDisplay();

        ws.addSubscriber(tv1);
        ws.addSubscriber(phone1);

        ws.setStatus("foggy");
        ws.removeObserver(tv1);
        ws.setStatus("cloudy");
        
    }
}