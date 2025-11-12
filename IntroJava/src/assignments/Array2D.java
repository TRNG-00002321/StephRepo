package assignments;

public class Array2D
{
    public static void main(String[] args)
    {
        int[][] arr =
                {
                        {1,2,-20},
                        {4,10,6},
                        {-5,8,9}
                };


        System.out.println();
        //System.out.println(arrSum(arr));
        //System.out.println(max(arr));
        //System.out.println(min(arr));
        //rcSums(arr);
        //transpose(arr);
        int[][] resultNum = add(arr,arr);
        String result = "";
        for(int i = 0; i<resultNum.length;i++)
        {
            for(int j = 0; j<resultNum[i].length; j++)
            {
                result += resultNum[i][j] + " ";

                if(j == 2) result += "\n";
            }
        }
        System.out.println(result);

    }

    public static int arrSum(int[][] arr)
    {
        int sum = 0;
        for(int i = 0; i<arr.length;i++)
        {
            for(int j = 0; j<arr[i].length; j++)
            {
                sum += arr[i][j];
            }
        }
        return sum;
    }

    public static int max(int[][] arr)
    {
        int max = 0;

        for(int i = 0; i<arr.length;i++)
        {
            for(int j = 0; j<arr[i].length; j++)
            {
               if(arr[i][j] > max)
                   max = arr[i][j];

            }
        }

        return max;
    }

    public static int min(int[][] arr)
    {
        int min = 0;

        for(int i = 0; i<arr.length;i++)
        {
            for(int j = 0; j<arr[i].length; j++)
            {
                if(arr[i][j] < min)
                    min = arr[i][j];

            }
        }

        return min;
    }

    public static void rcSums(int[][] arr)
    {
        for(int i = 0; i<arr.length;i++)
        {
            int rowSum =0;
            int colSum =0;
            for(int j = 0; j<arr[i].length; j++)
            {
                rowSum+=arr[i][j];
                colSum+=arr[j][i];
            }
            System.out.println("Row " + (i+1) + " sum is: " + rowSum);
            System.out.println("Column " + (i+1) + " sum is: " + colSum);
        }
    }

    public static void transpose(int[][] arr)
    {
        int[][] newArr = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[i].length; j++)
            {
                newArr[j][i] = arr[i][j];
            }
        }

//        String result = "";
//        for(int i = 0; i<newArr.length;i++)
//        {
//            for(int j = 0; j<newArr[i].length; j++)
//            {
//                result += newArr[i][j] + " ";
//
//                if(j == 2) result += "\n";
//            }
//        }
//        System.out.println(result);
    }

    public static int[][] add(int[][] arr1, int[][] arr2)
    {
        int[][] sumArr = new int[arr1.length][arr1[0].length];

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                sumArr[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
        return sumArr;
    }


}
