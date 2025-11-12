public class MaxFind
{
    public static void main(String[] args)
    {
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int n3 = Integer.parseInt(args[2]);
        int maxResult = n1;

        if(n2 > n1)
        {
            maxResult = n2;
            if(n3>n2)
                maxResult = n3;
        }
        else if (n3 > n1)
            maxResult = n3;

        System.out.println("Max number is " + maxResult);
    }
}
