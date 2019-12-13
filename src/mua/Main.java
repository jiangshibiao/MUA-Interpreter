package src.mua;
import src.mua.Parse;

import javax.xml.stream.events.Namespace;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //System.out.println("Mua stage 1");
        Scanner scan = new Scanner(System.in);
        Expression expr = new Expression(new NameSpace(scan));
        StringBuffer content = new StringBuffer("[ ");
        while (scan.hasNextLine()){
            String curLine = scan.nextLine();
            if (curLine.equals("Break")) break;
            int findNote = curLine.indexOf("//");
            if (findNote != -1)
                curLine = curLine.substring(0, findNote);
            content.append(curLine + " ");
            /*try {
                expr.runList(expr.generateList("[" + curLine + "]"));
            }
            catch (MyError e){
                System.out.println(e);
            }*/
        }
        content.append("]");
        try {
            expr.runList(expr.generateList(content.toString()));
        }
        catch (MyError e){
            System.out.println(e + "From:" + content.toString());
        }
    }
}