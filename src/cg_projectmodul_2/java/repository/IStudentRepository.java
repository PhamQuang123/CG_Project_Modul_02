package cg_projectmodul_2.java.repository;


public interface IStudentRepository<E, T> extends IRepository{
    E inputData();

    void display();
    boolean update();
    boolean delete();
    E findById(T id);
    void  findStudentById();
}
