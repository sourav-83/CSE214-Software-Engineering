abstract class BevarageMaker
{
    public final void MakeBevarage()
    {
        BoilWater();
        brew();
        PourInCup();
        AddCondiments();
    }

    void BoilWater()
    {
        System.out.println("boiling water\n");
    }
    abstract void brew();
    void PourInCup()
    {
        System.out.println("pouring in cup\n");
    }
    abstract void AddCondiments();
}

class TeaMaker extends BevarageMaker
{
    void brew()
    {
        System.out.println("brewing tea\n");
    }
    void AddCondiments()
    {
        System.out.println("adding lemon\n");
    }
}

class CoffeeMaker extends BevarageMaker
{
    void brew()
    {
        System.out.println("brewing coffee\n");
    }
    void AddCondiments()
    {
        System.out.println("adding sugar and milk\n");
    }
}

class ColdCoffeeMaker extends BevarageMaker
{   
    void BoilWater()
    {
        System.out.println("Preparing iced water\n");
    }
    void brew()
    {
        System.out.println("brewing coffee\n");
    }
    void AddCondiments()
    {
        System.out.println("adding sugar and milk\n");
    }
}

public class template {
    public static void main(String[] args)
    {
        BevarageMaker teaMaker = new TeaMaker();
        teaMaker.MakeBevarage();

        BevarageMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.MakeBevarage();

        BevarageMaker coldCoffeeMaker = new ColdCoffeeMaker();
        coldCoffeeMaker.MakeBevarage();
    }
    
}
