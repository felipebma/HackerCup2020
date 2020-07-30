import java.util.Scanner;

public class B {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nCases = in.nextInt();
        for(int i=1;i<=nCases;i++){
            System.out.printf("Case #%d: %s\n", i, solveCase(in));
        }
        in.close();
    }

    public static String solveCase(Scanner in){
        int n = in.nextInt();
        String shards = in.next();
        int counter = 0;
        for(int i=0;i<n;i++) counter += (shards.charAt(i) == 'A')?1:-1;
        return (Math.abs(counter)==1)?"Y":"N";
    }
}