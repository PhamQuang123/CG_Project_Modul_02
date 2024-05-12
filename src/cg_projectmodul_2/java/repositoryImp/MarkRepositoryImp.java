package cg_projectmodul_2.java.repositoryImp;

import cg_projectmodul_2.common.ConvertListMarkToListMap;
import cg_projectmodul_2.common.Table;
import cg_projectmodul_2.java.entity.Mark;
import cg_projectmodul_2.java.controller.MarkController;
import cg_projectmodul_2.java.controller.StudentController;
import cg_projectmodul_2.java.controller.SubjectController;
import cg_projectmodul_2.java.repository.IMarkRepository;
import cg_projectmodul_2.java.repository.IRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MarkRepositoryImp implements IMarkRepository<Mark> {
    @Override
    public Mark inputData() {
        Mark mark = new Mark();
        int indexStudent = inputStudent();
        int indexSubject = inputSubject();
        mark.setMarkId(inputMarkId());
        if (indexStudent >= 0 && indexSubject >= 0) {
            mark.setStudent(StudentController.listStudents.get(indexStudent));
            mark.setSubject(SubjectController.listSubject.get(indexSubject));
            if (checkExist(indexStudent, indexSubject)) {
                System.err.println("Đã nhập điểm môn " + mark.getSubject().getSubjectName()
                        + " cho sinh viên " + mark.getStudent().getStudentName());
                mark = null;
            } else {
                mark.setPoint(inputPoint());
            }
        } else {
            if (indexStudent < 0) {
                System.err.println("Chưa có thông tin sinh viên nào để nhập đểm");
            }
            if (indexSubject < 0) {
                System.err.println("Chưa có môn học để nhập điểm ");
            }
            mark = null;
        }
        return mark;
    }

    @Override
    public void display() {
        if (MarkController.listMarks.size() != 0) {
            Collections.sort(MarkController.listMarks, new Comparator<Mark>() {
                @Override
                public int compare(Mark o1, Mark o2) {

                    return (int) Math.floor(o1.getPoint() - o2.getPoint());
                }
            });

            System.out.printf("%36s%s\n", Table.space, "Danh Sách Điểm Thi");
            Table.row(100);
            Table.thMark("STT", "Mã sinh viên", "Tên sinh viên", "Điểm", "Môn học");
            Table.row(100);
            for (int i = 0; i < MarkController.listMarks.size(); i++) {
                Mark mark = MarkController.listMarks.get(i);
                Table.tdMark(i + 1, mark.getStudent().getStudentId(), mark.getStudent().getStudentName()
                        , mark.getPoint(), mark.getSubject().getSubjectName());
                Table.row(100);
            }
        } else {
            System.err.println("Vui lòng nhập điểm cho sinh viên");
        }
    }

    @Override
    public boolean update() {
        if (MarkController.listMarks.size() != 0) {
            List<Integer> listIndex = new ArrayList<>();
            System.out.println("Nhập mã sinh viên cần cập nhật điểm:");
            String updateId = scanner.nextLine();
            Mark mark = findByStudentId(updateId);
            if (mark != null) {
                do {
                    System.out.println("\nDanh sách môn học của sinh viên " + mark.getStudent().getStudentName());
                    int index = 1;
                    for (int i = 0; i < MarkController.listMarks.size(); i++) {
                        if (updateId.equals(MarkController.listMarks.get(i).getStudent().getStudentId())) {
                            System.out.printf("%d. %s\n", index, MarkController.listMarks.get(i).getSubject().getSubjectName());
                            index++;
                            listIndex.add(i);
                        }
                    }
                    System.out.println(index + ". Thoát");
                    System.out.print("Chọn môn học cần cập nhật điểm: ");

                    try {
                        int choice = Integer.parseInt(scanner.nextLine());
                        if (choice == index) {
                            return false;
                        } else if (choice < 1 || choice > index) {
                            System.err.println("Vui lòng chọn từ 1 - " + index + ", nhập lại");
                        } else {
                            Mark mark1 = MarkController.listMarks.get(listIndex.get(choice - 1));
                            mark1.setPoint(inputPoint());
                            return true;
                        }
                    } catch (Exception ex) {
                        System.err.println("Dữ liều đầu vào phải là ký tự số");
                    }
                } while (true);
            } else {
                System.err.println("Mã sinh viên không tồn tại");
            }
        } else {
            System.err.println("Chưa có thông tin. Vui lòng nhập thông tin điểm cho sinh viên");
        }
        return false;
    }

    @Override
    public boolean delete() {
        boolean result = false;
        if (MarkController.listMarks.size() != 0) {
            System.out.println("Nhập mã sinh viên cần xoá điểm:");
            String inputDeleteId = scanner.nextLine();
            Mark mark = findByStudentId(inputDeleteId);
            if (mark == null) {
                System.err.println("Mã không tồn tại");

            } else {
                System.out.println("Hãy lựa chọn cách xoá:");
                System.out.println("1. Xoá tất cả điểm các môn học của sinh viên "
                        + mark.getStudent().getStudentName());
                System.out.println("2. Xoá theo tuỳ chọn môn học");
                System.out.print("LỰa chọn của bạn là: ");
                do {
                    try {
                        int choice = Integer.parseInt(scanner.nextLine());
                        if (choice == 1) {
                            result = deleteAllByStudentId(inputDeleteId);
                            break;
                        } else if (choice == 2) {
                            result = deleteOptionBySubjectName(inputDeleteId, mark);

                            break;
                        } else {
                            System.err.println("Vui lòng chọn 1 hoặc 2, nhập lại");
                        }
                    } catch (Exception e) {
                        System.err.println("Dữ liệu nhập vào phải là ký tự số nguyên dương");
                    }
                } while (true);
            }
        } else {
            System.err.println("Chưa có thông tin. Vui lòng nhập thông tin điểm cho sinh viên");
        }
        return result;
    }

    public int countOfMarrk(String id) {
        int count = 0;
        for (Mark mark : MarkController.listMarks
        ) {
            if (mark.getStudent().getStudentId().equals(id)) {
                count++;
            }
        }
        return count;
    }

    public boolean deleteAllByStudentId(String id) {
        System.out.println("Bạn có chắc muốn xoá không");
        System.out.println("1. Có \t 2. Không");
        System.out.print("Lựa chọn của bạn là: ");
        do {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    int count = countOfMarrk(id);
                    while (count > 0) {
                        Mark mark = findByStudentId(id);
                        MarkController.listMarks.remove(mark);
                        count--;
                    }
                    return true;
                } else if (choice == 2) {
                    return false;
                } else {
                    System.err.println("Vui lòng chọn 1 hoặc 2, nhập lại");
                }
            } catch (Exception ex) {
                System.err.println("Dữ liệu nhập vào phải là ký tự số nguyên dương, nhập lại");
            }
        } while (true);
    }

    public boolean deleteOptionBySubjectName(String id, Mark mark) {
        List<Integer> listIndex = new ArrayList<>();
        System.out.println("Danh sách môn học của sinh viên " + mark.getStudent().getStudentName());
        int index = 1;
        for (int i = 0; i < MarkController.listMarks.size(); i++) {
            if (id.equals(MarkController.listMarks.get(i).getStudent().getStudentId())) {
                System.out.printf("%d. %s\n", index, MarkController.listMarks.get(i).getSubject().getSubjectName());
                index++;
                listIndex.add(i);
            }
        }
        System.out.print("Chọn môn học cần xoá điểm: ");
        do {
            try {
                int indexDelete = Integer.parseInt(scanner.nextLine());
                if (indexDelete < 1 || indexDelete >= index) {
                    System.err.println("Vui lòng chọn từ 1 - " + listIndex.size() + ", nhập lại");
                } else {
                    System.out.println("Bạn có chắc muốn xoá không");
                    System.out.println("1. Có \t 2. Không");
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice == 1) {
                        MarkController.listMarks.remove(MarkController.listMarks.get(listIndex.get(indexDelete-1)));
                        return true;
                    } else if (choice == 2) {
                        return false;
                    } else {
                        System.err.println("Vui lòng chọn 1 hoặc 2, nhập lại");
                    }
                }
            } catch (Exception ex) {
                System.err.println("Dữ liệu nhập vào phải là ký tự số nguyên dương, nhập lại");
            }

        } while (true);
    }

    public Mark findByStudentId(String id) {
        for (Mark mark : MarkController.listMarks
        ) {
            if (mark.getStudent().getStudentId().equals(id)) {
                return mark;
            }
        }
        return null;
    }

    @Override
    public void findAllMarkBySubjectName() {
        if (MarkController.listMarks.size() != 0) {
            System.out.println("Nhập tên môn học:");
            String inputSubjectName = scanner.nextLine();
            boolean check = false;
            for (Mark mark : MarkController.listMarks) {
                check = false ;
                if (mark.getSubject().getSubjectName().toLowerCase().contains(inputSubjectName.toLowerCase())) {
                    System.out.printf("%30s%s%s\n", Table.space, "Danh Sách Điểm Thi môn ", mark.getSubject().getSubjectName());
                    check = true;
                    break;
                }
            }
            if (!check){
                System.err.println("Môn học không tồn tại\n");
                System.out.printf("%36s%s\n", Table.space, "Danh Sách Điểm Thi");
            }

            Table.row(100);
            Table.thMark("STT", "Mã sinh viên", "Tên sinh viên", "Điểm", "Môn học");
            Table.row(100);
            int count = 1;
            for (Mark mark : MarkController.listMarks) {
                if (mark.getSubject().getSubjectName().toLowerCase().contains(inputSubjectName.toLowerCase())) {
                    Table.tdMark(count, mark.getStudent().getStudentId(), mark.getStudent().getStudentName()
                            , mark.getPoint(), mark.getSubject().getSubjectName());
                    Table.row(100);
                    count++;
                }
            }

        } else {
            System.err.println("Chưa có thông tin. Vui lòng nhập thông tin điểm cho sinh viên");
        }
    }

    public int inputMarkId() {
        int maxId = 0;
        if (MarkController.listMarks.size() == 0) {
            maxId = 0;
        }
        {
            for (Mark mark : MarkController.listMarks
            ) {
                if (mark.getMarkId() > maxId) {
                    maxId = mark.getMarkId();
                }
            }
        }
        maxId++;
        return maxId;
    }

    public int inputStudent() {
        if (StudentController.listStudents.size() != 0) {
            System.out.println("Danh sách tên sinh viên:");
            for (int i = 0; i < StudentController.listStudents.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, StudentController.listStudents.get(i).getStudentName());
            }
            System.out.print("Chọn sinh viên cần nhập điểm: ");
            while (true) {
                try {
                    int choice = Integer.parseInt(IRepository.scanner.nextLine());
                    if (choice < 1 || choice > StudentController.listStudents.size()) {
                        System.err.println("Vui lòng chọn từ 1 - " + StudentController.listStudents.size());
                    } else {
                        choice--;
                        return choice;
                    }

                } catch (Exception ex) {
                    System.err.println("Dữ liệu đầu vào phải là ký tự số");
                }
            }
        } else {
            System.err.println("Vui lòng nhập thông tin học sinh");
        }
        return -1;
    }

    public int inputSubject() {
        if (SubjectController.listSubject.size() != 0) {
            System.out.println("Danh sách tên môn học:");
            for (int i = 0; i < SubjectController.listSubject.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, SubjectController.listSubject.get(i).getSubjectName());
            }
            System.out.println("Chọn môn học cần nhập điểm: ");
            while (true) {
                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > SubjectController.listSubject.size()) {
                        System.err.println("Vui lòng chọn từ 1 - " + SubjectController.listSubject.size());
                    } else {
                        choice--;
                        return choice;
                    }
                } catch (Exception ex) {
                    System.err.println("Dữ liệu vào phải là ký tự số");
                }
            }
        } else {
            System.err.println("Vui lòng nhập thông tin môn học");
        }
        return -1;
    }

    public float inputPoint() {
        System.out.println("Nhập điểm:");
        while (true) {
            try {
                float inpPoint = Float.parseFloat(scanner.nextLine());
                if (inpPoint >= 0 && inpPoint <= 10) {
                    return inpPoint;
                } else {
                    System.err.println("Giá trị điểm nhập vào phải >= 0 && <= 10");
                }
            } catch (Exception ex) {
                System.err.println("Dữ liệu đầu vào phải là ký tự số");
            }
        }

    }

    public boolean checkExist(int indexStudent, int indexSubject) {
        boolean check = false;
        if (MarkController.listMarks.size() != 0) {
            for (Mark mark : MarkController.listMarks
            ) {
                if (mark.getStudent().getStudentId().equals(StudentController.listStudents.get(indexStudent).getStudentId())
                        && mark.getSubject().getSubjectName().equals(SubjectController.listSubject.get(indexSubject).getSubjectName())) {
                    check = true;
                    break;
                }
            }
        } else {
            check = false;
        }
        return check;
    }
}
