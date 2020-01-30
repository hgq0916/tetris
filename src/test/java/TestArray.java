import org.junit.Test;

public class TestArray {

  @Test
  public void fun1(){
    int[][] arr = {
        {1,2,3,4}
    };
    print(arr);

    int [][] newArr = new int[arr[0].length][arr.length];

    for(int i=0;i<newArr.length;i++){
      for(int j=0;j<newArr[i].length;j++){
        newArr[i][j] = arr[newArr[i].length-1-j][i];
      }
    }

    System.out.println();
    print(newArr);
  }

  public static void print(int[][] arr){
    for(int i=0;i<arr.length;i++){
      for(int j=0;j<arr[i].length;j++){
        System.out.print(arr[i][j]+" ");
      }
      System.out.println();
    }
  }

}
