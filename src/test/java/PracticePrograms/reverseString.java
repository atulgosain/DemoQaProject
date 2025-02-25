package PracticePrograms;


public class reverseString {

    public static void main(String[] args){
        String str = "any string with spaces!";
        String reverse = reverseStr(str);
        System.out.println("Original : "+str);
        System.out.println("Reversed : "+reverse);

    }

    public static String reverseStr(String str){
        String reversedString="";
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length-1;
        while(left < right){
            if(chars[left] == ' ')    {
                left--;
                continue;
            }
            else if(chars[right] ==' '){
                right++;
                continue;
            }

                char temp = chars[left];
                chars[left]= chars[right];
                chars[right]=temp;
                left++;
                right--;

        }

        return new String(chars);
    }

}
