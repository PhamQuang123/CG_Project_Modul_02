package cg_projectmodul_2.java.serviceImp;

import cg_projectmodul_2.java.controller.StudentController;
import cg_projectmodul_2.java.entity.Student;
import cg_projectmodul_2.java.repository.IStudentRepository;
import cg_projectmodul_2.java.repositoryImp.StudentRepositoryImp;
import cg_projectmodul_2.java.service.IStudentService;

public class StudentServiceImp implements IStudentService<Student> {
    private IStudentRepository studentRepositoryImp;

    public StudentServiceImp() {
        studentRepositoryImp = new StudentRepositoryImp();
    }

    @Override
    public Student inputData() {
        StudentController.listStudents.add((Student) studentRepositoryImp.inputData());
        return null;
    }

    @Override
    public void display() {
        studentRepositoryImp.display();
    }

    @Override
    public boolean update() {
        boolean resultUpdate = studentRepositoryImp.update();
        if (resultUpdate) {
            System.out.println("Cập nhật thành công");
        }
        return resultUpdate;
    }

    @Override
    public boolean delete() {
        boolean resultDelete = studentRepositoryImp.delete();
        if (resultDelete) {
            System.out.println("Xoá thành công");
        }
        return resultDelete;
    }


    @Override
    public void findStudentById() {
        studentRepositoryImp.findStudentById();
    }
}
