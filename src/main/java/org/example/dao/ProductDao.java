package org.example.dao;


import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;


import javax.transaction.Transactional;
import java.util.List;

@Component
public class ProductDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    //crate
    @Transactional
    public void createProduct(Product product){

        this.hibernateTemplate.saveOrUpdate(product);

    }

    //get all product
    public List<Product> getProducts(){
       List<Product>product=this.hibernateTemplate.loadAll(Product.class);
           return product;
    }
    //delete id
    @Transactional
    public void deleteproduct(int pid){
        Product p=this.hibernateTemplate.load(Product.class,pid);
                this.hibernateTemplate.delete(p);
    }
    //get by id
    public Product getProduct(int pid) {
       return this.hibernateTemplate.get(Product.class,pid);
    }
}
