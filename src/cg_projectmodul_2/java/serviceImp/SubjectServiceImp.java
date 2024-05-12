package cg_projectmodul_2.java.serviceImp;

import cg_projectmodul_2.java.entity.Subject;
import cg_projectmodul_2.java.repository.IRepository;
import cg_projectmodul_2.java.repository.ISubjectRepository;
import cg_projectmodul_2.java.repositoryImp.SubjectRepositoryImp;
import cg_projectmodul_2.java.service.ISubjectService;

public class SubjectServiceImp implements ISubjectService<Subject, String> {
    private ISubjectRepository subjectRepositoryImp;

    public SubjectServiceImp() {
        subjectRepositoryImp = new SubjectRepositoryImp();
    }

    @Override
    public Subject inputData() {

        return (Subject) subjectRepositoryImp.inputData();
    }

    @Override
    public void display() {
        subjectRepositoryImp.display();
    }

    @Override
    public boolean update() {
        boolean resultUpdate = subjectRepositoryImp.update();
        if (resultUpdate) {
            System.out.println("Cập nhật thành công");
        }
        return true;
    }

    @Override
    public boolean delete() {
        boolean resultDelete = subjectRepositoryImp.delete();
        if (resultDelete) {
            System.out.println("Xoá thành công");
        }
        return false;
    }

    @Override
    public Subject findById(String id) {
        System.out.println("Nhập mã môn học cần tìm:");
        String inputId = IRepository.scanner.nextLine();
        Subject sb = (Subject) subjectRepositoryImp.findById(inputId);
        if (sb == null) {
            System.err.println("Mã không tồn tại");
        } else {
            System.out.printf("Mã môn học: %s - Tên môn học: %s \n", sb.getSubjectId(), sb.getSubjectName());
        }
        return null;
    }
}
