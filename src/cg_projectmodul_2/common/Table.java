package cg_projectmodul_2.common;


public class Table {
    public static String space = "";

    public static void row(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
    }

    public static void thSubject(String th1, String th2, String th3) {
        System.out.printf("\n|%1s%s%1s|%3s%s%3s|%7s%s\n"
                , space, th1, space, space, th2, space, space, th3);
    }


    public static void tdSubject(int td1, String td2, String td3) {
        System.out.printf("\n|%2s%d%2s|%6s%s%6s|%7s%s\n"
                , space, td1, space, space, td2, space, space, td3);

    }

    public static void thStudent(String th1, String th2, String th3, String th4
            , String th5, String th6, String th7, String th8) {
        System.out.printf("\n|%2s%s%2s|%2s%s%2s|%6s%s%6s|%2s%s%2s" +
                        "|%2s%s%2s|%4s%s%4s|%8s%s%8s|%7s%s\n"
                , space, th1, space, space, th2, space, space, th3, space
                , space, th4, space, space, th5, space, space, th6, space
                , space, th7, space, space, th8);
    }

    public static void tdStudent(int td1, String td2, String td3, int td4,
                                 String td5, String td6, String td7, String td8) {
        System.out.printf("\n|%3s%d%3s%6s%s%6s%7s%s%7s%4s%d%3s" +
                        "%6s%s%6s%3s%s%2s%6s%s%2s%10s%s\n"
                , space, td1, space, space, td2, space, space, td3, space
                , space, td4, space, space, td5, space, space, td6, space
                , space, td7, space, space, td8);
    }

    public static void thMark(String th1, String th2, String th3, String th4, String th5) {
        System.out.printf("\n|%2s%s%2s|%3s%s%3s|%6s%s%7s|%3s%s%3s|%3s%s\n"
                , space, th1, space, space, th2, space, space, th3, space, space, th4, space, space, th5);
    }

    public static void tdMark(int td1, String td2, String td3, float td4, String td5) {
        System.out.printf("\n|%3s%d%3s|%6s%s%7s|%6s%s%8s|%3s%.1f%4s|%3s%s\n"
                , space, td1, space, space, td2, space, space, td3, space, space, td4, space, space, td5);
    }
}
