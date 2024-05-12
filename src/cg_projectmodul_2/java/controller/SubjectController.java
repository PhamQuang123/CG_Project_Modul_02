package cg_projectmodul_2.java.controller;

import cg_projectmodul_2.common.FileName;
import cg_projectmodul_2.java.repository.IRepository;
import cg_projectmodul_2.java.entity.Subject;
import cg_projectmodul_2.java.service.ISubjectService;
import cg_projectmodul_2.java.serviceImp.SubjectServiceImp;
import cg_projectmodul_2.util.ConnectionDB;

import java.util.List;

public class SubjectController {
    public static List<Subject> listSubject;
    private ISubjectService subjectServiceImp;

    public SubjectController() {
        listSubject = ConnectionDB.readDataFromFile(FileName.FILE_NAME_SUBJECT, listSubject);
        subjectServiceImp = new SubjectServiceImp();
    }

    public void subjectManager() {
        do {
            System.out.println("\n***** Quản Lý Môn Học *****");
            System.out.println("1. Thêm mới thông tin 1 môn học");
            System.out.println("2. Danh sách môn học");
            System.out.println("3. Cập nhật thông tin môn học");
            System.out.println("4. Xoá môn học");
            System.out.println("5. Tìm kiếm môn học theo mã");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn là: ");
            try {
                int choice = Integer.parseInt(IRepository.scanner.nextLine());
                switch (choice) {
                    case 1:
                        listSubject.add((Subject) subjectServiceImp.inputData());
                        break;
                    case 2:
                        subjectServiceImp.display();
                        break;
                    case 3:
                        subjectServiceImp.update();
                        break;
                    case 4:
                        subjectServiceImp.delete();
                        break;
                    case 5:
                        subjectServiceImp.findById("id");
                        break;
                    case 6:
                        ConnectionDB.writeDataToFile(FileName.FILE_NAME_SUBJECT, listSubject);
                        return;
                }
                if (choice < 1 || choice > 6) {
                    System.err.println("Vui lòng chọn 1-6, nhập lại");
                }
            } catch (Exception e) {
                System.err.println("Dữ liệu nhập vào phải là ký tự số nguyên dương, nhập lại");
            }
        } while (true);
    }
}
