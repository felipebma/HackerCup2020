import java.util.Scanner;

public class A {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int nCases = in.nextInt();
        for(int i=1;i<=nCases;i++){
            System.out.printf("Case #%d:\n", i);
            solveCase(in);
        }
        in.close();
    }

    private static void solveCase(Scanner in){
        int n = in.nextInt();
        char[] inbound = in.next().toCharArray(), outbound = in.next().toCharArray();
        for(int i=0;i<n;i++){
            boolean[] possible = new boolean[n];
            possible[i] = true;
            if(outbound[i] == 'Y'){
                for(int j = i - 1; j >= 0; j--){
                    if(inbound[j] == 'N' || outbound[j+1] == 'N') break;
                    possible[j] = true;
                }
                for(int j = i + 1; j < n; j++){
                    if(inbound[j] == 'N' || outbound[j-1] == 'N') break;
                    possible[j] = true;
                }
            }
            System.out.println(convert(possible));
        }
    }

    private static String convert(boolean[] possible){
        StringBuilder result = new StringBuilder();
        for(boolean p:possible) result.append((p)?'Y':'N');
        return result.toString();
    }

}