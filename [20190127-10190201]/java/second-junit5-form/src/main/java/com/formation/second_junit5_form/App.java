package com.formation.second_junit5_form;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String chaine = "kayak";
        StringUtil su = new StringUtil();
        System.out.println("kayak palindorme? " + su.estPalindrome(chaine));
    }
}
