package cg_projectmodul_2.java.controller;

import cg_projectmodul_2.common.FileName;
import cg_projectmodul_2.java.repository.IRepository;
import cg_projectmodul_2.java.entity.Mark;

import cg_projectmodul_2.java.service.IMarkService;
import cg_projectmodul_2.java.serviceImp.MarkServiceImp;
import cg_projectmodul_2.util.ConnectionDB;

import java.util.List;

public class MarkController {
    public static List<Mark> listMarks;
    private IMarkService markServiceImp;

    public MarkController() {
        listMarks = ConnectionDB.readDataFromFile(FileName.FILE_NAME_MARK, listMarks);
        markServiceImp = new MarkServiceImp();
    }

    public void markManagement() {
        do {
            System.out.println("\n***** Quản Lý Điểm Thi *****");
            System.out.println("1. Thêm mới điểm thi cho 1 sinh viên");
            System.out.println("2. Hiển thị danh sách tất cả điểm thi theo thứ tự điểm tăng dần");
            System.out.println("3. Thay đổi điểm theo sinh viên");
            System.out.println("4. Xóa điểm thi của sinh viên");
            System.out.println("5. Hiển thị danh sách điểm thi theo tên môn học");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn là: ");
            try {
                int choice = Integer.parseInt(IRepository.scanner.nextLine());
                switch (choice) {
                    case 1:
                        markServiceImp.inputData();
                        break;
                    case 2:
                        markServiceImp.display();
                        break;
                    case 3:
                        markServiceImp.update();
                        break;
                    case 4:
                        markServiceImp.delete();
                        break;
                    case 5:
                        markServiceImp.findAllMarkBySubjectName();
                        break;
                    case 6:
                        ConnectionDB.writeDataToFile(FileName.FILE_NAME_MARK, listMarks);
                        return;
                }
                if (choice < 1 || choice > 6) {
                    System.err.println("Vui lòng chọn từ 1-6, nhập lại");
                }
            } catch (Exception e) {
                System.err.println("Dữ liệu đầu vào phải là ký tự chữ số, nhập lại");
            }
        } while (true);
    }
}
