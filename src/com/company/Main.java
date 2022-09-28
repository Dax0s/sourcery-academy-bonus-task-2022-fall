package com.company;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            ArrayList<String> data = readFile("input.txt");

            writeFile("output.txt", data);
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        }
    }

    public static ArrayList<String> readFile(String location) throws FileNotFoundException
    {
        File file = new File(location);
        Scanner scanner = new Scanner(file);

        ArrayList<String> list = new ArrayList<String>();

        while(scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            list.add(line);
        }

        scanner.close();
        return list;
    }

    public static void writeFile(String fileName, ArrayList<String> data)
    {
        try
        {
            File file = new File(fileName);
            file.createNewFile();
        }
        catch (IOException e)
        {
            System.out.println(e);
            return;
        }

        try
        {
            FileWriter writer = new FileWriter(fileName);

            for (int i = 0; i < data.toArray().length; i++)
            {
                if (checkIfMoreThan10(data.get(i)))
                {
                    System.out.println(data.get(i));
                    writer.write(data.get(i) + "\n");
                }
            }

            writer.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public static boolean checkIfMoreThan10(String text)
    {
        int number = 0;
        int length = 0;
        for (int i = 0; i < text.length(); i++)
        {
            String c = Character.toString(text.charAt(i));

            if (checkIfNumber(c))
            {
                int num = Integer.parseInt(c);
                if (length > 0)
                {
                    if (num == 0)
                    {
                        number *= 10;
                    }
                    else
                    {
                        number += num * Math.pow(10, length);
                    }
                }
                else
                {
                    number += num;
                }

                if (length > 0 || num != 0)
                {
                    length++;
                }
            }
            else
            {
                number = 0;
                length = 0;
            }

            if (number >= 10)
            {
                return true;
            }

        }
        return false;
    }

    public static boolean checkIfNumber(String text)
    {
        try
        {
            int num = Integer.parseInt(text);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}
