package structural;

class Computer
{
    private String CPU;
    private String Ram;
    private String Storage;


    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public void setRam(String Ram) {
        this.Ram = Ram;
    }

    public void setStorage(String Storage) {
        this.Storage = Storage;
    }

    public void DisplayInfo()
    {
        System.out.println("Computer Configuration:\n" + CPU + "\n" + Ram + "\n" + Storage);
    }
    
}

interface Builder
{
    void BuildCPU();
    void BuildRam();
    void BuildStorage();
    Computer GetResult();
}

class pcBuilder implements Builder
{   
    Computer computer = new Computer();
    public void BuildCPU()
    {
       computer.setCPU("Intel-i5");
    }
    public void BuildRam()
    {
        computer.setRam("8 GB RAM");
    }
    public void BuildStorage()
    {
        computer.setStorage("512 GB SSD");
    }
    public Computer GetResult()
    {
        return computer;
    }
}

class ComputerDirector
{
    public void Construct(Builder builder)
    {
        builder.BuildCPU();
        builder.BuildRam();
        builder.BuildStorage();
    }
}


public class BuilderMain {
    public static void main(String[] args)
    {
        pcBuilder PCbuilder = new pcBuilder();
        ComputerDirector director = new ComputerDirector();
        director.Construct(PCbuilder);
        Computer pc = PCbuilder.GetResult();

        pc.DisplayInfo();
    }
    
}
