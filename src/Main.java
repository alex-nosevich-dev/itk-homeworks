import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PrettierStringBuilder sb = new PrettierStringBuilder();
        //String name = stringBuilder.append("Alexander").append(" ").append("Nosevich").toString();
        sb.append("Alexander");
        //System.out.println(sb.toString());
        System.out.println(sb);

        sb.append(" ");
        sb.append("Nosevich");
        System.out.println(sb);

        sb.append("bla-bla-bla");
        System.out.println(sb);

        sb.undo();
        System.out.println(sb);

    }
}