package cg_projectmodul_2.java.repositoryImp;

import cg_projectmodul_2.common.Regex;
import cg_projectmodul_2.common.Table;
import cg_projectmodul_2.java.controller.MarkController;
import cg_projectmodul_2.java.entity.Mark;
import cg_projectmodul_2.java.entity.Subject;
import cg_projectmodul_2.java.controller.SubjectController;
import cg_projectmodul_2.java.repository.ISubjectRepository;

import java.util.regex.Pattern;

public class SubjectRepositoryImp implements ISubjectRepository<Subject, String> {

    @Override
    public Subject inputData() {
        Subject subject = new Subject();
        subject.setSubjectId(inputSubjectId());
        subject.setSubjectName(inputSubjectName());
        return subject;
    }

    @Override
    public void display() {
        if (SubjectController.listSubject.size() == 0) {
            System.err.println("Vui lòng nhập thông tin môn học");
        } else {
            System.out.printf("%15s%s\n", Table.space, "Danh Sách Môn Học");
            Table.row(50);
            Table.thSubject("STT", "Mã môn học", "Tên môn học");
            Table.row(50);
            for (int i = 0; i < SubjectController.listSubject.size(); i++) {
                Table.tdSubject(i + 1, SubjectController.listSubject.get(i).getSubjectId()
                        , SubjectController.listSubject.get(i).getSubjectName());
                Table.row(50);
            }
        }

    }


    @Override
    public boolean update() {
        boolean result = false;
        System.out.println("Nhập mã môn học cần cập nhật:");
        String inputId = scanner.nextLine();
        Subject subjecttUpdate = findById(inputId);
        if (subjecttUpdate == null) {
            System.err.println("Mã không tồn tại, nhập lại");
        } else {
            subjecttUpdate.setSubjectName(inputSubjectNameUpdate(inputId));
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete() {
        boolean result = false;
        System.out.println("Nhập mã môn học cần xoá: ");
        String deleteId = scanner.nextLine();
        Subject deleteSubject = findById(deleteId);
        if (deleteSubject == null) {
            System.err.println("Mã môn học không tồn tại");
        } else {
            System.out.println("Bạn có muốn xoá thông tin môn học không?");
            System.out.println("1. Có \t 2. Không");
            System.out.print("Lựa chọn của bạn là: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    for (Mark mark : MarkController.listMarks
                    ) {
                        if (mark.getSubject().getSubjectId().equals(deleteId)) {
                            System.err.println("Đã nhập điểm cho môn học, không thể xoá");
                            return false;
                        }
                    }
                    SubjectController.listSubject.remove(deleteSubject);
                    result = true;
                } else if (choice == 2) {
                    result = false;
                } else {
                    System.err.println("Vui lòng chọn 1 hoặc 2");
                }
            } catch (Exception e) {
                System.err.println("Dữ liệu nhập vào phải là ký tự số nguyên dương");
            }
        }
        return result;
    }

    @Override
    public Subject findById(String id) {
        for (Subject sb : SubjectController.listSubject
        ) {
            if (sb.getSubjectId().equals(id)) {
                return sb;
            }
        }
        return null;
    }

    public String inputSubjectId() {
        System.out.println("Nhập mã môn học:");
        do {
            String inputId = scanner.nextLine();
            String regex = Regex.REGEX_SUBJECT_ID;
            if (Pattern.matches(regex, inputId)) {
                if (findById(inputId) == null) {
                    return inputId;
                } else {
                    System.err.println("Mã đã tồn tại, nhập lại");
                }
            } else {
                System.err.println("Mã bắt đầu bằng MH và 2 ký tự số, nhập lại");
            }
        } while (true);
    }

    public String inputSubjectName() {
        System.out.println("Nhập tên môn học:");
        do {
            String inputName = scanner.nextLine().trim();
            if (inputName.equals("") || inputName.length() > 15) {
                System.err.println("Không được để trống và vượt quá 15 ký tự, nhập lại");
            } else {
                boolean isExists = false;
                for (Subject sb : SubjectController.listSubject
                ) {
                    if (sb.getSubjectName().equals(inputName)) {
                        isExists = true;
                    }
                }
                if (isExists) {
                    System.err.println("Tên môn học đã tồn tại, nhập lại");
                } else {
                    return inputName;
                }
            }
        } while (true);
    }

    public String inputSubjectNameUpdate(String id) {
        System.out.println("Nhập tên môn học:");
        do {
            String inputName = scanner.nextLine();
            if (inputName.equals("") || inputName.trim().length() > 15) {
                System.err.println("Không được để trống và vượt quá 15 ký tự, nhập lại");
            } else {
                boolean isExists = false;
                for (Subject sb : SubjectController.listSubject
                ) {
                    if (sb.getSubjectName().equals(inputName) && !sb.getSubjectId().equals(id)) {
                        isExists = true;
                    }
                }
                if (isExists) {
                    System.err.println("Tên môn học đã tồn tại, nhập lại");
                } else {
                    return inputName;
                }
            }
        } while (true);
    }
}
