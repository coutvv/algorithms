package ru.coutvv.ch1;

/**
 * 1.1 Задачи без массивов
 *
 * Created by coutvv on 30.06.2017.
 */
public class TasksWithoutArrays {

    // 1.1.8
    static long fact(int n) {
        return n == 0 ? 1 : fact(n-1)*n;
    }

    // 1.1.9
    static long fibonacci(int n) {
        int a0 = 0, a1 = 1;
        if( n == 0 ) return a0;
        for(int i = 2; i <= n; i++) {
            int sum = a0 + a1;
            a0 = a1;
            a1 = sum;
        }
        return a1;
    }

    //1.1.10
    static long fibonacciLogN(int n) {
        if( n == 0) return 0;
        int main[][] = {{1, 0}, {0, 1}};
        int multiplicator[][] = {{1, 1}, {1, 0}};
        int k = n - 1 ;
        while(k > 0) {
            if( k % 2 == 0) {
                k /= 2;
                multiplicator = multMatrix(multiplicator, multiplicator);
            } else {
                k--;
                main = multMatrix(main, multiplicator);
            }
        }
        return main[0][0];
    }
    //for fibonacciLogN
    static int[][] multMatrix(int[][] A, int [][] B) {
        int[][] result = {
                {A[0][0]*B[0][0] + A[0][1]*B[1][0], A[0][0]*B[1][0] + A[0][1]* B[1][1]},
                {A[1][0]*B[0][0] + A[1][1]*B[0][1], A[1][0]*B[1][0] + A[1][1]* B[1][1]}
        };
        return result;
    }

    // 1.1.11   1/0! + 1/1! + ... + 1/n!
    static double onePerFactorial(int n){
        double result = 0;
        for(int i =0; i<= n; i++) {
            result += 1.0 / fact(i);
        }
        return result;
    }

    // 1.1.12
    static double onePerFactorialN(int n) {
        double result = 1; long mass = 1;
        for(int i = 1; i <= n; i++) {
            mass *= i;
            result += 1.0/mass;
        }
        return result;
    }

    // 1.1.13 euclid alg for NOD: nod(a,b) == nod(a-b,b) == nod(a,b-a);
    static int nod(int a, int b) {
        int m = a, n = b;
        while(m != 0 && n != 0) {
            if(m < n) {
                n = n - m;
            } else {
                m = m - n;
            }
        }
        return m == 0 ? n : m;
    }

    // 1.1.14 nod(a,b) == nod(a%b,b)
    static int modifyNod(int a, int b) {
        int m = a, n = b;
        while(m != 0 && n != 0) {
            if(m < n) {
                n = n % m;
            } else {
                m = m % n;
            }
        }
        return m == 0 ? n : m;
    }

    // 1.1.15
    static void nodXY(int a, int b) {
        int m = a, n = b, p = 1, q = 0, r = 0, s = 1;
        while(m != 0 && n != 0) {
            if(m < n) {
                n -= m;
                r -= p;
                s -= q;
            } else {
                m -= n;
                p -= r;
                q -= s;
            }
        }
        int d,x,y;
        if(m == 0) {
            d = n; x = r; y = s;
        } else {
            d = m; x = p; y = q;
        }
        System.out.println("d = " + d + "; x = " + x + "; y = " + y);
    }

    //1.1.16
    static void nodXYMod(int a, int b) {
        int m = a, n = b, p = 1, q = 0, r = 0, s = 1;
        while(m != 0 && n != 0) {
            if(m < n) {
                int k = n/m;
                n %= m;
                r -= p*k;
                s -= q*k;
            } else {
                int k = m/n;
                m %= n;
                p -= r*k;
                q -= s*k;
            }
        }
        int d,x,y;
        if(m == 0) {
            d = n; x = r; y = s;
        } else {
            d = m; x = p; y = q;
        }
        System.out.println("d = " + d + "; x = " + x + "; y = " + y);
    }

    public static void main(String[] args) {
        nodXYMod(17, 13);
    }
}
