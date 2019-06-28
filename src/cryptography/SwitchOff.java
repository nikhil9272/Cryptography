/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cryptography;

/**
 *
 * @author Acer
 */
import java.util.*;
class SwitchOff 
{
    public static void main(String args[]) throws Exception
    {
        Runtime runtime=Runtime.getRuntime();
        Process proc=runtime.exec("shutdown -s -t 0");
        System.exit(0);
    }
}
