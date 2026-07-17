class Solution {
    public int reverse(int x) {
        int a = x;
        int digit, rem;
        long rev = 0;
        
        while(a>0 || a<0){
            if(a<0){
                a = -a;
            }
            digit = x%10;
            rev = rev * 10 + digit;
            rem = x/10;
            x = rem;
            a = x;
            }
            if( rev < Integer.MIN_VALUE || rev > Integer.MAX_VALUE){
            return 0;    
            }
            return (int) rev;
        }
        
}
