package structural;

public class Singleton {
    private static Singleton Obj;

    private Singleton() {}

    public static Singleton GetInstance()
    {
        if (Obj == null) Obj = new Singleton();

        return Obj;
    }


}


// public class Singleton {
//     private static Singleton Obj = newSingleton();

//     private Singleton() {}

//     public static Singleton GetInstance()
//     {
//         return Obj;
//     }


// }