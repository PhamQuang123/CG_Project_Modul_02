package cg_projectmodul_2.java.repositoryImp;

import cg_projectmodul_2.common.Regex;
import cg_projectmodul_2.common.Table;
import cg_projectmodul_2.java.controller.MarkController;
import cg_projectmodul_2.java.entity.Mark;
import cg_projectmodul_2.java.entity.Student;
import cg_projectmodul_2.java.controller.StudentController;
import cg_projectmodul_2.java.repository.IStudentRepository;


import java.util.regex.Pattern;

public class StudentRepositoryImp implements IStudentRepository<Student, String> {
    @Override
    public Student inputData() {
        Student student = new Student();
        student.setStudentId(inputStudentId());
        student.setStudentName(inputStudentName());
        student.setAge(inputAge());
        student.setGender(inputGender());
        student.setPhone(inputPhone());
        student.setEmail(inputEmail());
        student.setAddress(inputAddress());
        return student;
    }

    @Override
    public void display() {
        if (StudentController.listStudents.size() == 0) {
            System.err.println("Vui lòng nhập thông tin sinh viên");
        } else {
            System.out.printf("%60s%s\n", Table.space, "Danh Sách Sinh Viên");
            Table.row(150);
            Table.thStudent("STT", "Mã sinh viên", "Tên sinh viên", "Tuổi",
                    "Giới tính", "Điện thoại", "Email", "Địa chỉ");
            Table.row(150);
            for (int i = 0; i < StudentController.listStudents.size(); i++) {
                Student st = StudentController.listStudents.get(i);
                Table.tdStudent(i + 1, st.getStudentId(), st.getStudentName(), st.getAge()
                        , st.isGender() ? "Nam" : "Nữ", st.getPhone(), st.getEmail(), st.getAddress());
                Table.row(150);
            }
        }
    }

    @Override
    public boolean update() {
        boolean check = false;
        System.out.println("Nhập mã sinh viên cần cập nhật:");
        String inputId = scanner.nextLine();
        Student studentUpdate = findById(inputId);
        if (studentUpdate == null) {
            System.err.println("Mã không tồn tại");
        } else {
            boolean isExit = true;
            do {
                System.out.println("Chọn cách cập nhật:");
                System.out.println("1. Cập nhật tất cả thông tin sinh viên");
                System.out.println("2. Tuỳ chọn thông tin cần cập nhật");
                System.out.println("3. Thoát");
                System.out.print("Lựa chọn của bạn là: ");
                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            updateAll(studentUpdate);
                            check = true;
                            break;
                        case 2:
                            updateOption(studentUpdate);
                            check = true;
                            break;
                        case 3:
                            isExit = false;
                            break;
                    }
                    if (choice < 1 || choice > 3) {
                        System.err.println("Vui lòng chọn từ 1-3, nhập lại");
                    }
                } catch (Exception ex) {
                    System.err.println("Dữ liệu đầu vào phải là ký tự số nguyên dương 1 ,2 hoặc 3, nhập lại ");
                }

            } while (isExit);
        }
        return check;

    }

    public void updateAll(Student studentUpdate) {
        studentUpdate.setStudentName(inputStudentName());
        studentUpdate.setAge(inputAge());
        studentUpdate.setGender(inputGender());
        studentUpdate.setPhone(inputPhoneUpdate(studentUpdate.getStudentId()));
        studentUpdate.setEmail(inputEmailUpdate(studentUpdate.getStudentId()));
        studentUpdate.setAddress(inputAddress());
    }

    public void updateOption(Student studentUpdate) {
        do {
            System.out.println("Chọn thông tin cần cập nhật:");
            System.out.println("1. Tên sinh viên");
            System.out.println("2. Tuổi");
            System.out.println("3. Giới tính");
            System.out.println("4. Số điện thoại");
            System.out.println("5. Email");
            System.out.println("6. Địa chỉ");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        studentUpdate.setStudentName(inputStudentName());
                        break;
                    case 2:
                        studentUpdate.setAge(inputAge());
                        break;
                    case 3:
                        studentUpdate.setGender(inputGender());
                        break;
                    case 4:
                        studentUpdate.setPhone(inputPhoneUpdate(studentUpdate.getStudentId()));
                        break;
                    case 5:
                        studentUpdate.setEmail(inputEmailUpdate(studentUpdate.getStudentId()));
                        break;
                    case 6:
                        studentUpdate.setAddress(inputAddress());
                        break;
                    case 7:
                        return;
                }
                if (choice < 1 || choice > 7) {
                    System.err.println("Vui lòng chọn từ 1-7, nhập lại");
                }
            } catch (Exception e) {
                System.err.println("Dữ liệu nhập vào phải là ký tự số nguyên dương, nhập lại");
            }
        } while (true);

    }

    @Override
    public boolean delete() {
        boolean result = false;
        System.out.println("Nhập mã sinh viên cần xoá: ");
        String deleteId = scanner.nextLine();
        Student deleteStudent = findById(deleteId);
        if (deleteStudent != null) {
            System.out.println("Bạn có muốn xoá thông tin sinh viên không?");
            System.out.println("1. Có \t 2. Không");
            System.out.print("Lựa chọn của bạn là: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    for (Mark mark : MarkController.listMarks
                    ) {
                        if (mark.getStudent().getStudentId().equals(deleteId)) {
                            System.err.println("Đã nhập điểm cho sinh viên, không thể xoá");
                            return false;
                        }
                    }
                    StudentController.listStudents.remove(deleteStudent);
                    result = true;
                } else if (choice == 2) {
                    result = false;
                } else {
                    System.err.println("Vui lòng chọn 1 hoắc 2. Nhập lại");
                }
            } catch (Exception e) {
                System.err.println("Dữ liệu nhập vào phải là ký tự số nguyên dương, nhập lại");
            }
        } else {
            System.err.println("Mã sinh viên không tồn tại");
        }
        return result;
    }

    @Override
    public Student findById(String id) {
        for (Student st : StudentController.listStudents
        ) {
            if (st.getStudentId().equals(id)) {
                return st;
            }
        }
        return null;
    }

    @Override
    public void findStudentById() {
        System.out.println("Nhập mã sinh viên cần tìm:");
        String id = scanner.nextLine();
        Student findStudent = findById(id);
        if (findStudent == null) {
            System.err.println("Mã sinh viên không tồn tại");
        } else {
            System.out.printf("Mã sinh viên: %s - Tên: %s - Tuổi: %d - Giới tính: %s\n",
                    findStudent.getStudentId(), findStudent.getStudentName(), findStudent.getAge(), findStudent.isGender() ? "Nam" : "Nữ");
            System.out.printf("Điện thoại: %s - Email: %s - Địa chỉ: %s\n", findStudent.getPhone(), findStudent.getEmail(), findStudent.getAddress());
        }
    }

    public String inputStudentId() {
        String regex = Regex.REGEX_STUDENT_ID;
        System.out.println("Nhập mã sinh viên:");
        do {
            String inpStudentId = scanner.nextLine();
            if (Pattern.matches(regex, inpStudentId)) {
                if (findById(inpStudentId) == null) {
                    return inpStudentId;

                } else {
                    System.err.println("Mã đã tồn tại, vui lòng nhập lại");
                }
            } else {
                System.err.println("Mã sinh viên bắt đầu bằng SV và 3 số bất kì, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputStudentName() {
        System.out.println("Nhập tên sinh viên:");
        do {
            String inpStudentName = scanner.nextLine().trim();
            if (inpStudentName.length() > 50 || inpStudentName.equals("")) {
                System.err.println("Tên không quá 50 ký tự và không được để trống, vui lòng nhập lại");
            } else {
                return inpStudentName;
            }
        } while (true);
    }


    public int inputAge() {
        System.out.println("Nhập tuổi sinh viên:");
        do {
            try {
                int inpAge = Integer.parseInt(scanner.nextLine());
                if (inpAge < 18 && inpAge > 35) {
                    System.err.println("Tuổi sinh viên phải >= 18 và <= 35, nhập lại");
                } else {
                    return inpAge;
                }
            } catch (Exception e) {
                System.err.println("Dữ liệu nhập vào phải là số nguyên dương, nhập lại");
            }
        } while (true);
    }

    public boolean inputGender() {
        System.out.println("Nhập giới tính sinh viên:");
        do {
            String inpGender = scanner.nextLine();
            if (inpGender.equals("nam")) {
                return Boolean.parseBoolean("true");
            } else if (inpGender.equals("nữ")) {
                return Boolean.parseBoolean("false");
            } else {
                System.err.println("Dữ liệu nhập vào chỉ nhận là nam hoặc nữ, nhập lại");
            }
        } while (true);
    }

    public String inputPhone() {
        System.out.println("Nhập số điện thoại:");
        do {

            String inpPhone = scanner.nextLine();
            String regex = Regex.REGEX_PHONE;
            if (Pattern.matches(regex, inpPhone)) {
                boolean isExist = false;
                for (Student st : StudentController.listStudents
                ) {
                    if (st.getPhone().equals(inpPhone)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Số điện thoại đã tồn tại, nhập lại");
                } else {
                    return inpPhone;
                }
            } else {
                System.err.println("Dữ liệu đầu vào phải là 1 dãy số gồm 11 chữ số, nhập lại");
            }
        } while (true);
    }

    public String inputPhoneUpdate(String id) {
        System.out.println("Nhập số điện thoại:");
        do {
            String inpPhone = scanner.nextLine();
            String regex = Regex.REGEX_PHONE;
            if (Pattern.matches(regex, inpPhone)) {
                boolean isExist = false;
                for (Student st : StudentController.listStudents
                ) {
                    if (st.getPhone().equals(inpPhone) && !st.getStudentId().equals(id)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Số điện thoại đã tồn tại, nhập lại");
                } else {
                    return inpPhone;
                }
            } else {
                System.err.println("Dữ liệu đầu vào phải là 1 dãy số gồm 11 chữ số, nhập lại");
            }
        } while (true);
    }

    public String inputEmail() {
        System.out.println("Nhập email:");
        do {
            String inpEmail = scanner.nextLine();
            String regex = Regex.REGEX_EMAIL;
            if (Pattern.matches(regex, inpEmail)) {
                boolean isExist = false;
                for (Student student : StudentController.listStudents
                ) {
                    if (student.getEmail().equals(inpEmail)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Email đã tồn tại, nhập lại");
                } else {
                    return inpEmail;
                }
            } else {
                System.err.println("Email không đúng định dạng, nhập lại");
            }
        } while (true);
    }

    public String inputEmailUpdate(String id) {
        System.out.println("Nhập email:");
        do {
            String inpEmail = scanner.nextLine();
            String regex = Regex.REGEX_EMAIL;
            if (Pattern.matches(regex, inpEmail)) {
                boolean isExist = false;
                for (Student student : StudentController.listStudents
                ) {
                    if (student.getEmail().equals(inpEmail) && !student.getStudentId().equals(id)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Email đã tồn tại, nhập lại");
                } else {
                    return inpEmail;
                }
            } else {
                System.err.println("Email không đúng định dạng, nhập lại");
            }
        } while (true);
    }

    public String inputAddress() {
        System.out.println("Nhập địa chỉ:");
        while (true) {
            String inputAddress = scanner.nextLine().trim();
            if (inputAddress.equals("")) {
                System.err.println("Không được để trống, nhập lại");
            } else {
                return inputAddress;
            }
        }
    }
}
