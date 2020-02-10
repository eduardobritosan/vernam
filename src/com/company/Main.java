package com.company;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Introducir el mensaje original : ");
        Scanner scanner = new Scanner(System. in);
        String inputString = scanner. nextLine();

        Random rnd = new Random();
        byte[] MBytes = inputString.getBytes(StandardCharsets.US_ASCII);
        //byte[] K = { 60, 24, 115};
        byte[] K = new byte[MBytes.length];
        new SecureRandom().nextBytes(K);
        byte[] CBytes = new byte[MBytes.length];
        byte[] DCBytes = new byte[MBytes.length];

        //Cifrado
        vernamCipher(MBytes, K, CBytes);

        //Descifrado
        vernamCipher(CBytes, K, DCBytes);

        System.out.println("Mensaje original: " + inputString);
        System.out.println("Mensaje original en binario: " + BytesAsBinaryString(MBytes));
        System.out.println("Longitud del mensaje en binario: " + MBytes.length * 8);

        System.out.println("Clave aleatoria: " + BytesAsBinaryString(K));

        System.out.println("Mensaje cifrado en binario: " + BytesAsBinaryString(CBytes));
        System.out.println("Mensaje cifrado: " + BytesAsAsciiString(CBytes));
    }

    private static void vernamCipher(byte[] M, byte[] K, byte[] C){
        for (int i = 0; i<M.length; i++){
            C[i] = (byte) (M[i] ^ K[i]);
        }
    }

    private static String BytesAsBinaryString(byte[] byteArray){
        StringBuilder result = new StringBuilder();
        for (byte b : byteArray) {
            result.append(Integer.toBinaryString(b & 255 | 256).substring(1));
        }
        return result.toString();
    }

    private static String BytesAsAsciiString(byte[] byteArray){
        return new String(byteArray);
    }
}
