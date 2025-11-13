package assignments;

public class MathFun
{
    public int add(int n, int n2)
    {
        return n + n2;
    }
    public int sub(int n, int n2)
    {
        return n - n2;
    }
    public int div(int n, int n2)
    {
        return n / n2;
    }
    public int mult(int n, int n2)
    {
        return n * n2;
    }
    public int mod(int n, int n2)
    {
        return n % n2;
    }
    public int fact(int n)
    {
        if (n==1)
            return n;
        return n * fact(n-1);
    }
}
