public class Main {
    public static void main(String[] args) {
        PrettierStringBuilder sb = new PrettierStringBuilder();

        sb.append("first world");
        sb.append("second world");
        sb.undo();
        String result = sb.toString();
        System.out.println(result);
    }
}