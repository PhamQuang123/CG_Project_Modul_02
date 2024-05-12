package cg_projectmodul_2.java.controller;

import cg_projectmodul_2.java.repository.IRepository;

public class Menu {
    private StudentController studentController;
    private SubjectController subjectController;
    private MarkController markController;

    public Menu() {
        studentController = new StudentController();
        subjectController = new SubjectController();
        markController = new MarkController();

    }

    public void menu() {
        do {
            System.out.println("\n**** Quản Lý Trường Học ****");
            System.out.println("1. Quản lý sinh viên");
            System.out.println("2. Quản lý môn học");
            System.out.println("3. Quản lý điểm thi");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn của bạn là: ");
            try {
                int choice = Integer.parseInt(IRepository.scanner.nextLine());
                switch (choice) {
                    case 1:
                        studentController.studentManager();
                        break;
                    case 2:
                        subjectController.subjectManager();
                        break;
                    case 3:
                        markController.markManagement();
                        break;
                    case 4:
                        System.exit(0);
                }
                if (choice < 1 || choice > 4) {
                    System.err.println("Vui lòng chọn từ 1-4, nhập lại");
                }
            } catch (Exception e) {
                System.err.println("Dữ liệu nhập vào phải là ký tự số nguyên dương, nhập lại");
            }
        } while (true);
    }
}
