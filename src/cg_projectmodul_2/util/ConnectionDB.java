package cg_projectmodul_2.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDB {

    // Đọc và ghi dữ liệu từ file
    public static <T> void writeDataToFile(String nameFile, List<T> list) {
        File file = new File(nameFile);
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.flush();
            fos.close();
            oos.close();
            System.out.println("Đã lưu dữ liệu thành công");
        } catch (Exception ex) {
            System.err.println("Có lỗi trong quá trình ghi file " + nameFile);
        }
    }

    public static <T> List<T> readDataFromFile(String nameFile, List<T> listName) {
        File file = new File(nameFile);
        if (file.exists()) {
            FileInputStream fis;
            ObjectInputStream ois;
            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listName = (List<T>) ois.readObject();
                ois.close();
                fis.close();

                return listName;
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Có lỗi trong quá trình đọc file " + nameFile);
            }
        } else {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
}
