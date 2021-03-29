import java.util.*;
import java.util.function.BooleanSupplier;
import java.lang.Math.max;
public class Rational{

    private final int x;
    private final int y;

    public Rational(int numerator, int denominator){
        x=numerator;
        y=denominator;
    }


    public int x(){
        return x;
    }
    public int y(){
        return y;
    }  
    public int gcd(int a,int b){
        int big=Math.max(a, b);
        int small=Math.min(a, b);
        int left=big % small;
        if (left==0) {return small;}
        else {return gcd(small,left);}
    }
    // public int gcd(int a,int b){
    //     int gcd=1;
    //     for (int k=1;k<=a &&k<=b;k++){
    //         if (a % k==0 && b % k==0){
    //             gcd=k;
    //         }
    //     }
    //     return gcd;
    // }
    public Rational plus(Rational b){
        int x1=this.x() * b.y() + this.y() * b.x();
        int y1=this.y() * b.y();
        Rational ans=new Rational(x1,y1);
        return ans;
    }
    public Rational minus(Rational b){
        int x1=this.x()*b.y()-this.y()*b.x();
        int y1=this.y*b.y();
        Rational ans=new Rational(x1,y1);
        return ans;
    }
    public Rational times(Rational b){
        int x1=this.x() * b.x();
        int y1=this.y() * b.y();
        Rational ans=new Rational(x1,y1);
        return ans;
    }
    public Rational divides(Rational b){
        int x1=this.x() * b.y();
        int y1=this.y() * b.x();
        Rational ans=new Rational(x1,y1);
        return ans;
    }
    public boolean equals(Rational b){
        int gcd1=gcd(x(),y());
        int x1=x()/gcd1;int y1=y()/gcd1;
        int gcd2=gcd(b.x(),b.y());
        int x2=b.x()/gcd2;int y2=b.y()/gcd2;
        if (x1==x2 && y1==y2) return true;
        else return false;
    }

    public String toString(){
        int x1=x()/gcd(x(),y());
        int y1=y()/gcd(x(),y());
        return x1 + "/" + y1;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Input a rational:");
        int numerator=in.nextInt();
        int denominator=in.nextInt();
        Rational first=new Rational(numerator, denominator);
        System.out.print("Input another rational:");
        int numerator0=in.nextInt();
        int denominator0=in.nextInt();
        Rational second=new Rational(numerator0, denominator0);
        in.close();
        Rational sum = first.plus(second);
        Rational minus = first.minus(second);
        Rational times = first.times(second);
        Rational divides = first.divides(second);
        boolean equals = first.equals(second);
        System.out.println(sum);
        System.out.println(minus);
        System.out.println(times);
        System.out.println(divides);
        System.out.println(equals);
    }
}