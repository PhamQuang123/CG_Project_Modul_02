package cg_projectmodul_2.java.service;

import cg_projectmodul_2.java.repository.IRepository;

public interface IStudentService<E> extends IRepository {
    E inputData();

    void display();

    boolean update();

    boolean delete();

    void findStudentById();
}
