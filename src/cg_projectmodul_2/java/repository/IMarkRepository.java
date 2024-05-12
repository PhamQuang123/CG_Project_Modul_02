package cg_projectmodul_2.java.repository;

public interface IMarkRepository<E> extends IRepository {
    E inputData();

    void display();

    boolean update();

    boolean delete();

    void findAllMarkBySubjectName();
}
