package assignments;
import java.util.Scanner;

public class MathMain
{
    public static void main(String[] args)
    {
        MathFun m = new MathFun();
        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.next());
        int num2 = Integer.parseInt(scan.next());

        System.out.println(m.add(num,num2));
        System.out.println(m.sub(num,num2));
        System.out.println(m.div(num,num2));
        System.out.println(m.mult(num,num2));
        System.out.println(m.mod(num,num2));
        System.out.println(m.fact(num));


    }
}
