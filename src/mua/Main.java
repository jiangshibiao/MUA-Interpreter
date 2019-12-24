package src.mua;
import src.mua.Parse;

import javax.xml.stream.events.Namespace;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
        Expression expr = new Expression(new NameSpace(scan));
        expr.runList(expr.generateList("[ make \"pi 3.14159 make \"run [[a] [repeat 1 :a]] ]"));
        StringBuffer content = new StringBuffer("[ ");
        while (scan.hasNextLine()){
            String curLine = scan.nextLine();
            if (curLine.equals("Break")) break;
            int findNote = curLine.indexOf("//");
            if (findNote != -1)
                curLine = curLine.substring(0, findNote);
            content.append(curLine + " ");
        }
        content.append("]");
        try {
            expr.runList(expr.generateList(content.toString()));
        }
        catch (MyError e) {
            System.out.println(e + "    From:   " + content.toString());
        }
    }
}