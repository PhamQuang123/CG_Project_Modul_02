package cg_projectmodul_2.java.controller;

import cg_projectmodul_2.common.FileName;
import cg_projectmodul_2.java.repository.IRepository;
import cg_projectmodul_2.java.entity.Student;
import cg_projectmodul_2.java.serviceImp.StudentServiceImp;
import cg_projectmodul_2.util.ConnectionDB;

import java.util.List;

public class StudentController {
    public static List<Student> listStudents;
    private StudentServiceImp studentServiceImp;

    public StudentController() {
        listStudents = ConnectionDB.readDataFromFile(FileName.FILE_NAME_STUDENT, listStudents);
        studentServiceImp = new StudentServiceImp();
    }

    public void studentManager() {

        do {
            System.out.println("\n***** Quản Lý Sinh Viên *****");
            System.out.println("1. Thêm mới thông tin 1 sinh viên");
            System.out.println("2. Danh sách sinh viên");
            System.out.println("3. Cập nhật thông tin sinh viên");
            System.out.println("4. Xoá sinh viên");
            System.out.println("5. Tìm kiếm sinh viên theo mã");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn là: ");
            try {
                int choice = Integer.parseInt(IRepository.scanner.nextLine());
                switch (choice) {
                    case 1:
                        studentServiceImp.inputData();
                        break;
                    case 2:
                        studentServiceImp.display();
                        break;
                    case 3:
                        studentServiceImp.update();
                        break;
                    case 4:
                        studentServiceImp.delete();
                        break;
                    case 5:
                        studentServiceImp.findStudentById();
                        break;
                    case 6:
                        ConnectionDB.writeDataToFile(FileName.FILE_NAME_STUDENT, listStudents);
                        return;
                }
                if (choice < 1 || choice > 6) {
                    System.err.println("Vui lòng chọn từ 1-6, nhập lại");
                }
            } catch (Exception e) {
                System.err.println("Dữ liệu nhập vào phải là ký tự số nguyên dương, nhập lại");
            }
        } while (true);

    }
}
