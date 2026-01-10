interface SortStrategy
{
   void sort(String[] array);
}

class BubbleSortStrategy implements SortStrategy
{
    public void sort(String[] array)
    {
        System.out.println("sorting using Bubble sort\n");
    }
}

class MergeSortStrategy implements SortStrategy
{
    public void sort(String[] array)
    {
        System.out.println("sorting using Merge sort\n");
    }
}

class Context
{
    private SortStrategy s;

    public Context(SortStrategy s)
    {
        this.s = s;
    }
    public void SetStrategy(SortStrategy s)
    {
        this.s = s;
    }
    public void sort(String[] str)
    {
        s.sort(str);
    }
}
public class strategy {
    public static void main(String[] args)
    {
        Context context = new Context(new BubbleSortStrategy());
        String[] arr = {"cpp", "java", "python", "c"};
        context.sort(arr);
        context.SetStrategy(new MergeSortStrategy());
        context.sort(arr);


    }
    
}
