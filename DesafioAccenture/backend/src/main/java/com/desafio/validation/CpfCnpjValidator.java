package com.desafio.validation;

public class CpfCnpjValidator {
  public static boolean isCpf(String doc) {
    String d = doc.replaceAll("\\D", "");
    return d.length() == 11 && !d.chars().allMatch(ch -> ch == d.charAt(0)) && validateCpfDigits(d);
  }
  public static boolean isCnpj(String doc) {
    String d = doc.replaceAll("\\D", "");
    return d.length() == 14 && !d.chars().allMatch(ch -> ch == d.charAt(0)) && validateCnpjDigits(d);
  }
  private static boolean validateCpfDigits(String d) {
    int sum1=0,sum2=0;
    for(int i=0;i<9;i++){ int n=d.charAt(i)-'0'; sum1+=n*(10-i); sum2+=n*(11-i); }
    int dv1 = (sum1%11<2)?0:11-(sum1%11);
    sum2 += dv1*2;
    int dv2 = (sum2%11<2)?0:11-(sum2%11);
    return dv1==d.charAt(9)-'0' && dv2==d.charAt(10)-'0';
  }
  private static boolean validateCnpjDigits(String d) {
    int[] w1={5,4,3,2,9,8,7,6,5,4,3,2}, w2={6,5,4,3,2,9,8,7,6,5,4,3,2};
    int sum1=0,sum2=0;
    for(int i=0;i<12;i++){ int n=d.charAt(i)-'0'; sum1+=n*w1[i]; sum2+=n*w2[i]; }
    int dv1=(sum1%11<2)?0:11-(sum1%11);
    sum2+=dv1*w2[12];
    int dv2=(sum2%11<2)?0:11-(sum2%11);
    return dv1==d.charAt(12)-'0' && dv2==d.charAt(13)-'0';
  }
}
