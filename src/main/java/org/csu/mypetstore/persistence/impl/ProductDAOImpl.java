package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String GET_PRODUCT_BY_PRODUCTID = "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID = ?";
    private static final String GET_PRODUCT_BY_CATEGORY = "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY = ?";
    private static final String SEARCH_PRODUCTLIST = "SELECT PRODUCTID,NAME,DESCN AS description,CATEGORY as categoryId FROM PRODUCT WHERE lower(name) like ?";
    @Override
    public List<Product> getProductListByCategory(String categoryId) {
       List<Product> products = new ArrayList<Product>();
       try{
           Connection connection = DBUtil.getConnection();
           PreparedStatement pStatement = connection.prepareStatement(GET_PRODUCT_BY_CATEGORY);
           pStatement.setString(1,categoryId);
           ResultSet resultSet = pStatement.executeQuery();
           while(resultSet.next()){
               Product product = new Product();
               product.setProductId(resultSet.getString(1));
               product.setName(resultSet.getString(2));
               product.setDescription(resultSet.getString(3));
               product.setCategoryId(resultSet.getString(4));

               products.add(product);
           }

           DBUtil.closeResultSet(resultSet);
           DBUtil.closePreparedStatement(pStatement);
           DBUtil.closeConnection(connection);
       } catch (Exception e){
           e.printStackTrace();
       }
       return products;
    }

    @Override
    public Product getProduct(String productId) {
        Product product = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_PRODUCT_BY_PRODUCTID);
            pStatement.setString(1,productId);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> products = new ArrayList<Product>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(SEARCH_PRODUCTLIST);
            pStatement.setString(1,keywords);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                products.add(product);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }
}
