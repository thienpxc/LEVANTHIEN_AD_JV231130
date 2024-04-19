package Exam_Advance_1.ra.service;

import Exam_Advance_1.ra.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IGenericService<Catalog,Integer> {
    private List<Catalog> catalogs;
    public CatalogService(){
        this.catalogs = new ArrayList<>();
    }

    @Override
    public List<Catalog> getAll() {
        return catalogs;
    }

    @Override
    public void save(Catalog catalog) {
        catalogs.add(catalog);
    }

    @Override
    public Catalog findById(Integer catalogId) {
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogId() == catalogId) {
                return catalog;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer catalogId) {
        catalogs.removeIf(catalog -> catalog.getCatalogId() == catalogId);
    }
}
