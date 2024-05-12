package cg_projectmodul_2.java.serviceImp;

import cg_projectmodul_2.java.controller.MarkController;
import cg_projectmodul_2.java.entity.Mark;
import cg_projectmodul_2.java.repository.IMarkRepository;
import cg_projectmodul_2.java.repositoryImp.MarkRepositoryImp;
import cg_projectmodul_2.java.service.IMarkService;

public class MarkServiceImp implements IMarkService<Mark> {
    private IMarkRepository markrepositoryImp;

    public MarkServiceImp() {
        markrepositoryImp = new MarkRepositoryImp();
    }

    @Override
    public Mark inputData() {
        Mark mark = (Mark) markrepositoryImp.inputData();
        if (mark != null) {
            MarkController.listMarks.add(mark);
        }
        return null;
    }

    @Override
    public void display() {
        markrepositoryImp.display();
    }

    @Override
    public boolean update() {
        boolean result = markrepositoryImp.update();
        if (result) {
            System.out.println("Cập nhật thành công");
        }
        return result;
    }

    @Override
    public boolean delete() {
        boolean result = markrepositoryImp.delete();
        if (result) {
            System.out.println("Xoá thành công");
        }
        return result;
    }

    @Override
    public void findAllMarkBySubjectName() {
        markrepositoryImp.findAllMarkBySubjectName();

    }
}
