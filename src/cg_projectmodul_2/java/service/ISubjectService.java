package cg_projectmodul_2.java.service;

import cg_projectmodul_2.java.repository.IRepository;

public interface ISubjectService<E,T> extends IRepository {
    E inputData();

    void display();
    boolean update();
    boolean delete();
    E findById(T id);
}
