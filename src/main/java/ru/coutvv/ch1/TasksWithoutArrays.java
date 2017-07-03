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

    //1.1.17
    // z == 2 nok(a,b), cause m*u + n*v == 2ab in all iteration of algorithm
    // and nod(a,b)*nok(a,b) == ab
    static void edejkstra(int a, int b) {
        int m = a, n = b, u = b, v = a;
        while(m !=0 && n != 0) {
            if(m >= n) {
                m -= n; v += u;
            } else {
                n -= m; u += v;
            }
        }
        int z;
        if( m == 0 ) z = v;
        else z = u;
        System.out.println(z);
    }

    // 1.1.18 euclid algorithm with equotions:
    // nod(2a,2b) == 2nod(a,b)
    // nod(2a,b) = nod(a,b)
    static void algEuclidVarEq(int a, int b) {
        int m = a, n = b, d = 1;
        //nod(a,b) = d nod(m,n)
        while(m!=0 && n != 0) {
            if(m%2 == 0 && n % 2 == 0) {
                m/=2; n/=2; d*=2;
            } else if(m%2 == 0) {
                m/=2;
            } else if(n%2 == 0) {
                n/=2;
            } else {
                if(m>n) {
                    m-=n;
                } else
                    n-=m;
            }
        }
        int result = (m==0 ? n : m) * d;
        System.out.println(result);

    }

    //1.1.19 added searching x,y in prev alg
    static void algEuclidVarEqPQRS(int a, int b) {
        int p = 1, q = 0, r = 0, s = 1;
        int im = 1, in = 1;
        int m = a, n = b, d = 1;
        //nod(a,b) = d nod(m,n)
        while(m!=0 && n != 0) {
            if(m%2 == 0 && n % 2 == 0) {
                m/=2; n/=2; d*=2;
                if(r%2 != 0 || s %2 != 0) {
                    r = r + b;
                    s = s - a;
                }
                p /= 2; q /= 2;
                if(r%2 != 0 || s %2 != 0) {
                    r = r + b;
                    s = s - a;
                }
                r /= 2; s /= 2;
            } else if(m%2 == 0) {
                m/=2;
                if(p%2!=0 || q%2!=0) {
                    p = p + b;
                    q = q - a;
                }
                p/=2; q /= 2;
            } else if(n%2 == 0) {
                n/=2;
                if(r%2 != 0 || s %2 != 0) {
                    r = r + b;
                    s = s - a;
                }
                r /= 2; s /= 2;
            } else {
                if(m>=n) {
                    m -= n;
                    p-=r; q-=s;
                } else {
                    n -= m;
                    r-=p; s-=q;
                }
            }
        }

        int result = n*d;
        int x = r, y = s;
        if(n == 0) {
            result = m*d;
            x = p; y = q;
        }
        System.out.println(result);
        System.out.println("x = " + x + "; y = " + y);
        System.out.println(a*x+b*y);
    }

    //1.1.20 print all ^2 from 0 to N
    static void printQuadro(int n){
        for(int i = 0; i <= n; i++) {
            System.out.print(i*i + " ");
        }
    }

    //1.1.21 prev, but without *: +- only
    static void printQuadroWithoutMulti(int n) {
        int square = 0;
        System.out.print(square + " ");
        for(int i = 1; i <= n; i++) {
            square = square + i + i - 1;
            System.out.print(square + " ");
        }
    }

    //1.1.21 v2 without minus
    static void printQuadroWithoutMinusMulti(int n) {
        int square = 0;
        System.out.print(square + " ");
        for(int i = 0; i < n; i++ ){
            square += i + i + 1;
            System.out.println(square);
        }
    }

    //1.1.22 factoring
    static void factoring(int n) {
        while(n != 1) {
            int l = 2;
            while(n % l != 0)
                l++;
            System.out.print(l + " ");
            n/=l;
        }
    }
    //1.1.23
    static void factoring2(int n) {
        int l = 2;
        while(n!= 1) {
            if(n%l == 0) {
                n/=l;
                System.out.print(l + " ");
            } else {
                if(l*l > n)
                    l=n;
                else
                    l++;
            }
        }
    }

    //1.1.24
    static boolean isPrime(int n) {
        int k = 2;
        while(k*k < n) {
            if(n%k == 0 )
                return false;
            k++;
        }
        return true;
    }

    //1.1.25[a] checking primeries to gauss number a+bi
    static boolean isGaussPrime(int a, int b) {
        a = Math.abs(a); b = Math.abs(b);
        for(int c = 1; c < a || c < b; c++) {
            for(int d = 1; d < a || d < b; d++) {
                if((a*c+b*d)%(c*c+d*d) == 0 && (a*d + b*c)%(c*c+d*d) == 0)
                    return false;
            }
        }
        return true;
    }

    //1.1.25[b] print factoring of number
    static void factoringGaussNumber(int a,int b) {

        a = Math.abs(a); b = Math.abs(b);
        for(int c = 0; (c <= a || c <= b) ;) {
            for(int d = 0; d <= a || d <= b;) {
                if(c==0 && d == 0 ||
                        c == 1 && d == 0 ||
                        c == 0 && d == 1) {
                    d++;
                    //nothing
                } else if((a == c && b == d || a == d && b == c) && isGaussPrime(a,b)) {
                    System.out.print("("+a + ", " + b + "); ");
                    a = 1; b = 0;
                } else if((a*c+b*d)%(c*c+d*d) == 0 && (a*d + b*c)%(c*c+d*d) == 0 && isGaussPrime(c,d)) {
                    System.out.print("("+c + ", " + d + "); ");
                    System.out.print("("+c + ", -" + d + "); ");
                    Complex next = divide(new Complex(a,b), new Complex(c,d));
                    next = divide(next, new Complex(c,-d));
                    a = next.a; b = next.b;
                } else
                    d++;
            }
            c++;
        }
    }

    static Complex divide(Complex c1, Complex c2) {
        int real = (c1.a*c2.a + c1.b*c2.b) / (c2.a*c2.a + c2.b*c2.b);
        int image = (- c1.a*c2.b + c1.b*c2.a) / (c2.a*c2.a + c2.b*c2.b);
        return new Complex(real, image);
    }

    static class Complex {
        int a,b;
        public Complex(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
//        System.out.println(isGaussPrime(0,-2));
        factoringGaussNumber(136,0);
    }
}
